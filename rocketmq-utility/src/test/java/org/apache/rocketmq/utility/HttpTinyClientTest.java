/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rocketmq.utility;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HttpTinyClientTest {
    private static final int PORT = 8080;
    private final HttpTinyServer httpTinyServer = new HttpTinyServer(PORT);

    @BeforeTest
    public void beforeTest() throws Exception {
        httpTinyServer.start();
    }

    @AfterTest
    public void afterTest() {
        httpTinyServer.shutdown();
    }

    @Test
    public void testHttpGet() throws Exception {
        final HttpTinyClient.HttpResult httpResult = HttpTinyClient.httpGet("http://127.0.0.1:" + PORT, 3 * 1000);
        assertTrue(httpResult.isOk());
        assertEquals(HttpTinyServer.content, httpResult.getContent());
    }
}