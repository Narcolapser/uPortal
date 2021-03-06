/**
 * Licensed to Apereo under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright ownership. Apereo
 * licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at the
 * following location:
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apereo.portal.logging;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.PrintWriter;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

/** */
public class LoggingWriterTest {
    @Test
    public void testAppendableLogger() {
        final Logger logger = Mockito.mock(Logger.class);

        final PrintWriter loggingWriter = new PrintWriter(new LoggingWriter(logger, LogLevel.INFO));
        loggingWriter.print("test");
        verifyNoMoreInteractions(logger);

        loggingWriter.println("end");
        verify(logger).info("testend");

        loggingWriter.print("hanging");
        verifyNoMoreInteractions(logger);

        loggingWriter.flush();
        verifyNoMoreInteractions(logger);

        loggingWriter.close();
        verify(logger).info("hanging");

        verifyNoMoreInteractions(logger);
    }
}
