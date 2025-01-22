package io.github.sparqlanything.parquet;

import io.github.sparqlanything.model.*;
import io.github.sparqlanything.model.annotations.Example;
import io.github.sparqlanything.model.annotations.Option;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

@io.github.sparqlanything.model.annotations.Triplifier
public class ParquetTriplifier implements Triplifier {

	private static final Logger log = LoggerFactory.getLogger(ParquetTriplifier.class);

	@Example(resource = "https://sparql-anything.cc/examples/simple.parquet", query = "CONSTRUCT { ?s ?p ?o . } WHERE { SERVICE <x-sparql-anything:location=https://sparql-anything.cc/examples/simple.parquet> { ?s ?p ?o } }", description = "Constructing a Facade-X RDF graph out of the Parquet file available at https://sparql-anything.cc/examples/simple.parquet")
	@Option(description = "The format of the input Parquet file.", validValues = "parquet")
	public final static IRIArgument PROPERTY_FORMAT = new IRIArgument("parquet.format", "parquet");

	public static final String LOCATION_PROPERTY = "location";

	@Override
	public void triplify(Properties properties, FacadeXGraphBuilder builder) {
		String dataSourceId = SPARQLAnythingConstants.DATA_SOURCE_ID; // there is always 1 data source id
		builder.addRoot(dataSourceId);

		String location = properties.getProperty(LOCATION_PROPERTY);
		if (location == null) {
			throw new IllegalArgumentException("Location property is required");
		}

		Path path = new Path(location);


		try (ParquetReader<GenericRecord> reader = AvroParquetReader.<GenericRecord>builder(path).build()) {
			GenericRecord record;
			int rown = 1;
			while ((record = reader.read()) != null) {
				System.out.println(rown);
				processRecord(rown, dataSourceId, dataSourceId, record, builder);
				rown++;
			}
		} catch (IOException e) {
			System.err.println("Error reading Parquet file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void processRecord(int rown, String dataSourceId, String rootId, GenericRecord record, FacadeXGraphBuilder builder) {
		String rowContainerId = rootId + "#row" + rown;
		builder.addContainer(dataSourceId, rootId, rown, rowContainerId);

		// Iterate over all fields in the GenericRecord
		for (Schema.Field field : record.getSchema().getFields()) {
			String colName = field.name();
			Object colValue = record.get(colName);
			if (colValue != null) {
				builder.addValue(dataSourceId, rowContainerId, colName, colValue.toString());
			}
		}
	}

	@Override
	public Set<String> getMimeTypes() {
		return Set.of("application/parquet");
	}

	@Override
	public Set<String> getExtensions() {
		return Set.of("parquet");
	}
}
