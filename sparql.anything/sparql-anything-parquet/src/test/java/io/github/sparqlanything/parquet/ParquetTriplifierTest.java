package io.github.sparqlanything.parquet;

import io.github.sparqlanything.testutils.AbstractTriplifierTester;
import java.io.StringWriter;
import org.apache.jena.graph.Triple;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.junit.Test;

import java.util.Properties;

public class ParquetTriplifierTest extends AbstractTriplifierTester {

	public ParquetTriplifierTest() {
		super(new ParquetTriplifier(), new Properties(), "parquet", "nq");
	}

	@Test
	public void test1() {
		System.out.println("\nExpected graph triples:");
		expectedDatasetGraph.getDefaultGraph().find().forEachRemaining(triple ->
			System.out.println(tripleToString(triple)));

		System.out.println("\nActual result triples:");
		resultDatasetGraph.getDefaultGraph().find().forEachRemaining(triple ->
			System.out.println(tripleToString(triple)));

		this.assertResultIsIsomorphicWithExpected();
		this.assertNotBlankNode();
	}

	private String tripleToString(Triple triple) {
		return String.format("%s %s %s .",
			triple.getSubject().toString(),
			triple.getPredicate().toString(),
			triple.getObject().toString());
	}
}
