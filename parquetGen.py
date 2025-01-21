import pandas as pd

# Create a sample DataFrame
data = {
    "ID": [1, 2, 3, 4, 5],
    "Name": ["Alice", "Bob", "Charlie", "David", "Eve"],
    "Age": [25, 30, 35, 40, 28],
    "City": ["New York", "Los Angeles", "Chicago", "Houston", "Phoenix"],
    "Salary": [70000, 80000, 90000, 85000, 73000],
}

df = pd.DataFrame(data)

# Save the DataFrame as a Parquet file
df.to_parquet("sample.parquet", engine="pyarrow")  # Or use engine="fastparquet"
