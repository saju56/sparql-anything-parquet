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

import io.github.sparqlanything.html.org.apache.any23.extractor.ExtractionContext;
import io.github.sparqlanything.html.org.apache.any23.extractor.ExtractionResult;
import io.github.sparqlanything.html.org.apache.any23.extractor.ExtractorDescription;
import io.github.sparqlanything.html.org.apache.any23.extractor.rdf.BaseRDFExtractor;
import io.github.sparqlanything.html.org.apache.any23.extractor.rdf.ManchesterSyntaxExtractorFactory;
import io.github.sparqlanything.html.org.apache.any23.extractor.rdf.RDFParserFactory;
import org.eclipse.rdf4j.rio.RDFParser;

/**
 * Concrete implementation of {@link ContentExtractor} handling
 * <a href="http://www.w3.org/TR/owl2-manchester-syntax/">Manchester Syntax</a> format.
 *
 * @author Peter Ansell
 */
public class ManchesterSyntaxExtractor extends BaseRDFExtractor {

    public ManchesterSyntaxExtractor(boolean verifyDataType, boolean stopAtFirstError) {
        super(verifyDataType, stopAtFirstError);
    }

    public ManchesterSyntaxExtractor() {
        this(false, false);
    }

    @Override
    public ExtractorDescription getDescription() {
        return ManchesterSyntaxExtractorFactory.getDescriptionInstance();
    }

    @Override
    protected RDFParser getParser(ExtractionContext extractionContext, ExtractionResult extractionResult) {
        return RDFParserFactory.getInstance().getManchesterSyntaxParser(isVerifyDataType(), isStopAtFirstError(),
                extractionContext, extractionResult);
    }

}
