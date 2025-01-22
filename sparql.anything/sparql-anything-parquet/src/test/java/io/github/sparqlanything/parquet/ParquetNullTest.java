package io.github.sparqlanything.parquet;

import io.github.sparqlanything.model.BaseFacadeXGraphBuilder;
import io.github.sparqlanything.model.FacadeXGraphBuilder;
import io.github.sparqlanything.model.IRIArgument;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.sparql.core.DatasetGraph;
import org.junit.Test;

import java.net.URL;
import java.util.Properties;

import static org.junit.Assert.fail;

public class ParquetNullTest {

	private ParquetTriplifier triplifier = new ParquetTriplifier();

	@Test
	public void testParquetNullValues() {
		Properties properties = new Properties();
		properties.setProperty("namespace", "http://www.example.org#");

		URL parquetFile = getClass().getClassLoader().getResource("1.parquet");
		assert parquetFile != null;
		properties.setProperty(IRIArgument.LOCATION.toString(), parquetFile.toString());

		FacadeXGraphBuilder b = new BaseFacadeXGraphBuilder(properties);
		triplifier.triplify(properties, b);
		DatasetGraph graph = b.getDatasetGraph();

		if (graph.find(Node.ANY, Node.ANY, Node.ANY, NodeFactory.createLiteralString("null")).hasNext()) {
			fail("Null values not properly handled in Parquet file");
		}
	}
}
