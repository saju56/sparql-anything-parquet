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

package io.github.sparqlanything.engine.functions;

import io.github.sparqlanything.engine.FacadeX;
import org.apache.commons.io.FilenameUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsFacadeXExtension extends FunctionBase1 {
	
	private static final Logger logger = LoggerFactory.getLogger(IsFacadeXExtension.class);

	public static boolean isFacadeXExtension(String v) {
		logger.trace("isFacadeXExtension({})",v);
		return FilenameUtils.isExtension(v, FacadeX.Registry.getRegisteredExtensions());
	}

	@Override
	public NodeValue exec(NodeValue v) {
		return NodeValue.makeBoolean(isFacadeXExtension(v.asNode().getLiteralValue().toString()));
	}

}
