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

package org.apache.jmeter.protocol.http.gui.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;

import org.apache.jmeter.exceptions.IllegalUserActionException;
import org.apache.jmeter.gui.action.AbstractActionWithNoRunningTest;
import org.apache.jmeter.gui.action.ActionNames;
import org.apache.jmeter.gui.util.FileDialoger;
import org.apache.jmeter.protocol.http.correlation.Correlation;
import org.apache.jmeter.protocol.http.gui.CorrelationGui;
import org.apache.jmeter.util.JMeterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Choose the JMX file to correlate with previous JMX file.
 *
 */
public class CorrelateJMXFile extends AbstractActionWithNoRunningTest {

    private static final String[] exts = new String[] { ".jmx" }; //$NON-NLS-1$
    private static final Set<String> commands = new HashSet<>();
    private static final Logger log = LoggerFactory.getLogger(CorrelateJMXFile.class);

    static {
        commands.add(ActionNames.CORRELATION_JMX_FILE);
    }

    public CorrelateJMXFile() {
        super();
    }


    @Override
    protected void doActionAfterCheck(ActionEvent e) throws IllegalUserActionException {
        final JFileChooser chooser = FileDialoger.promptToCorrelationFile(exts);
        if (chooser == null) {
            return;
        }
        int retVal = chooser.showDialog(null, JMeterUtils.getResString("correlation_title")); //$NON-NLS-1$
        if (!(chooser.getSelectedFile().getAbsolutePath().endsWith(".jmx") || chooser.getSelectedFile().getAbsolutePath().endsWith(".xml")) ) {
            throw new IllegalUserActionException("Please select valid jmx/xml file.");
        }
        if (retVal == JFileChooser.APPROVE_OPTION) {
            // extract the candidates variables for
            // correlation by comparing the JMX request objects.
            Correlation.extractParameters(chooser.getSelectedFile());
            // create the JFrame for showing the list
            // of correlation candidates variables.
            log.debug("Creating Correlation table");
            CorrelationGui.createCorrelationGui();
        }
    }

    @Override
    public Set<String> getActionNames() {
        return commands;
    }
}
