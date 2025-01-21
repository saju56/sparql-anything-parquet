import pandas as pd
import sys

def csv_to_parquet(input_csv, output_parquet):
    try:
        # Read the CSV file
        df = pd.read_csv(input_csv)
        
        # Convert to Parquet and save
        df.to_parquet(output_parquet, index=False)
        print(f"Successfully converted {input_csv} to {output_parquet}.")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python csv_to_parquet.py <input_csv> <output_parquet>")
        sys.exit(1)
    
    input_csv = sys.argv[1]
    output_parquet = sys.argv[2]
    csv_to_parquet(input_csv, output_parquet)
