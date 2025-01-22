package io.github.sparqlanything.parquet;

import io.github.sparqlanything.model.BaseFacadeXGraphBuilder;
import io.github.sparqlanything.model.FacadeXGraphBuilder;
import io.github.sparqlanything.model.IRIArgument;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.TxnType;
import org.apache.jena.sparql.core.DatasetGraph;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class ParquetOnDiskTest {

	private static final Logger logger = LoggerFactory.getLogger(ParquetOnDiskTest.class);
	private final ParquetTriplifier triplifier = new ParquetTriplifier();

	@Test
	public void testWithOnDiskGraph() throws IOException {
		Properties properties = new Properties();
		properties.setProperty("namespace", "http://www.example.org#");

		File tmp = Files.createTempDirectory(null).toFile();
		boolean isTmpFactoryCreated = tmp.mkdirs();
		logger.trace("tmp directory created? {}", isTmpFactoryCreated);
		properties.setProperty("ondisk", tmp.getAbsolutePath());

		String location = Objects.requireNonNull(getClass().getClassLoader().getResource(
			"1.parquet")).toString();
		properties.setProperty(IRIArgument.LOCATION.toString(), location);

		FacadeXGraphBuilder b = new BaseFacadeXGraphBuilder(properties);
		triplifier.triplify(properties, b);
		DatasetGraph graph = b.getDatasetGraph();

		graph.commit();
		graph.end();

		graph.begin(TxnType.READ);
		AtomicInteger ai = new AtomicInteger();
		graph.find(null, null, null, null).forEachRemaining(q -> ai.incrementAndGet());
		Assert.assertEquals(7, ai.get());
		graph.end();

		FileUtils.deleteQuietly(tmp);
	}
}
