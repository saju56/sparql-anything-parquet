import pyarrow.parquet as pq
import pyarrow as pa

def rewrite_parquet_with_small_row_groups(input_file, output_file, row_group_size=100_000):
    # Read the input Parquet file
    reader = pq.ParquetFile(input_file)

    # Convert the schema to a pyarrow.lib.Schema
    schema = reader.schema_arrow

    # Create a writer for the output file
    with pq.ParquetWriter(output_file, schema, use_dictionary=True) as writer:
        for i, batch in enumerate(reader.iter_batches(batch_size=row_group_size)):
            if i % 10 == 0:
                print(i)
            writer.write_table(pa.Table.from_batches([batch]))

    print(f"Rewritten file saved to: {output_file}")

# Example usage
if __name__ == "__main__":
    input_file = "sparql.anything/out.parquet"
    output_file = "small_row_groups_output.parquet"
    rewrite_parquet_with_small_row_groups(input_file, output_file, row_group_size=3)
