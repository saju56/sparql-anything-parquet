@Format(
	name = "Parquet",
	description = "Apache Parquet is a columnar storage format that provides efficient data compression and encoding schemes. "
		+ "Parquet files store nested data structures in a flat columnar format, making them highly efficient for processing large datasets.\n"
		+ "\n"
		+ "Parquet files organize data by column rather than by row, which allows for better compression ratios and more efficient "
		+ "querying of specific columns. Each column is stored with its own metadata, including data type information and encoding details.\n"
		+ "\n"
		+ "The format supports complex nested data structures and is schema-aware, maintaining the full data type information "
		+ "of the original data model.",
	resourceExample = "https://sparql-anything.cc/examples/example.parquet"
)
package io.github.sparqlanything.parquet;

import io.github.sparqlanything.model.annotations.Format;
