import pyarrow.parquet as pq

def check_row_group_sizes(parquet_file):
    parquet_reader = pq.ParquetFile(parquet_file)

    print(f"File: {parquet_file}")
    print(f"Number of Row Groups: {parquet_reader.num_row_groups}\n")

    for i in range(parquet_reader.num_row_groups):
        row_group = parquet_reader.metadata.row_group(i)
        num_rows = row_group.num_rows
        total_byte_size = row_group.total_byte_size
        print(f"Row Group {i + 1}:")
        print(f"  Number of Rows: {num_rows}")
        print(f"  Total Byte Size: {total_byte_size} bytes\n")

if __name__ == "__main__":
    parquet_file = "sparql.anything/out.parquet"
    check_row_group_sizes(parquet_file)
