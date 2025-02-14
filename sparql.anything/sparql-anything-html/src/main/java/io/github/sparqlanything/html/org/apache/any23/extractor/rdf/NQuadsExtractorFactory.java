/*
 * Copyright (c) 2024 SPARQL Anything Contributors @ http://github.com/sparql-anything
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.sparqlanything.html.org.apache.any23.extractor.rdf;

import java.util.Arrays;

import io.github.sparqlanything.html.org.apache.any23.extractor.ExtractorDescription;
import io.github.sparqlanything.html.org.apache.any23.extractor.ExtractorFactory;
import io.github.sparqlanything.html.org.apache.any23.extractor.SimpleExtractorFactory;
import io.github.sparqlanything.html.org.apache.any23.extractor.rdf.NQuadsExtractor;
import io.github.sparqlanything.html.org.apache.any23.rdf.Prefixes;
import org.eclipse.rdf4j.rio.RDFFormat;

/**
 * @author Peter Ansell p_ansell@yahoo.com
 *
 */
public class NQuadsExtractorFactory extends SimpleExtractorFactory<NQuadsExtractor>
        implements ExtractorFactory<NQuadsExtractor> {

    public static final String NAME = "rdf-nq";

    public static final Prefixes PREFIXES = null;

    private static final ExtractorDescription descriptionInstance = new NQuadsExtractorFactory();

    public NQuadsExtractorFactory() {
        super(NQuadsExtractorFactory.NAME, NQuadsExtractorFactory.PREFIXES,
                Arrays.asList(RDFFormat.NQUADS.getDefaultMIMEType(), "text/x-nquads;q=0.1", "text/rdf+nq;q=0.1",
                        "text/nq;q=0.1", "text/nquads;q=0.1", "text/n-quads;q=0.1"),
                "example-nquads.nq");
    }

    @Override
    public io.github.sparqlanything.html.org.apache.any23.extractor.rdf.NQuadsExtractor createExtractor() {
        return new NQuadsExtractor();
    }

    public static ExtractorDescription getDescriptionInstance() {
        return descriptionInstance;
    }
}
