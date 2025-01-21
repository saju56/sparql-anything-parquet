import pyarrow.parquet as pq
import pyarrow as pa

def save_first_n_rows_with_same_row_groups(input_parquet, output_parquet, n):
    reader = pq.ParquetFile(input_parquet)

    # Get the schema and row groups
    schema = reader.schema
    row_groups = reader.metadata.num_row_groups  # Corrected here to use num_row_groups

    # Calculate how many rows are needed
    rows_needed = n
    selected_row_groups = []
    rows_processed = 0

    for rg_idx in range(row_groups):
        # Get the metadata for the current row group
        row_group_meta = reader.metadata.row_group(rg_idx)
        row_count = row_group_meta.num_rows
        
        # If this row group contains more rows than we need, slice it
        if rows_processed + row_count <= n:
            selected_row_groups.append(reader.read_row_group(rg_idx))
            rows_processed += row_count
        else:
            # If we are at the row group that exceeds the required rows, slice it
            remaining_rows = rows_needed - rows_processed
            selected_row_groups.append(reader.read_row_group(rg_idx).slice(0, remaining_rows))
            break
    
    # Combine selected row groups into a single Table
    combined_table = pa.concat_tables(selected_row_groups)

    pq.write_table(combined_table, output_parquet, row_group_size=row_group_meta.num_rows)
    print(f"Successfully wrote the first {n} rows to {output_parquet} with the same row group sizes.")


def main():
    input_parquet = 'sparql.anything/small_row_groups_output.parquet' 
    output_parquet = 'sparql.anything/sliced_huge.parquet' 
    n = 50000  # Number of rows to keep

    save_first_n_rows_with_same_row_groups(input_parquet, output_parquet, n)

if __name__ == "__main__":
    main()
