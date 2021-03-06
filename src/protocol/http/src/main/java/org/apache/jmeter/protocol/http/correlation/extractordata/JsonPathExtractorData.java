/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jmeter.protocol.http.correlation.extractordata;

public class JsonPathExtractorData extends ExtractorData {
    // JSONPath expression
    String expr;

    public JsonPathExtractorData(String refName, String jsonPathExpression, String matchNr, String contentType,
            String testName) {
        super(contentType, matchNr, refName, testName);
        this.expr = jsonPathExpression;
    }

    public String getExpr() {
        return expr;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof JsonPathExtractorData)) {
            return false;
        }
        JsonPathExtractorData jExData = (JsonPathExtractorData) o;
        return jExData.getExpr().equals(expr);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + expr.hashCode();
        return result;
    }

}
