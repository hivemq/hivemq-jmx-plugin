/*
 * Copyright 2015 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.plugins.metrics.jmx.plugin;

import com.dcsquare.hivemq.spi.PluginEntryPoint;
import com.dcsquare.hivemq.spi.callback.registry.CallbackRegistry;
import com.google.inject.Inject;
import com.hivemq.plugins.metrics.jmx.callbacks.StartJmxReporting;

import javax.annotation.PostConstruct;

/**
 * This is the main class of the plugin, which is instanciated during the HiveMQ start up process.
 */
public class JmxMetricsMainClass extends PluginEntryPoint {

    private final StartJmxReporting startJmxReporting;

    @Inject
    public JmxMetricsMainClass(final StartJmxReporting startJmxReporting) {
        this.startJmxReporting = startJmxReporting;
    }

    @PostConstruct
    public void postConstruct() {

        CallbackRegistry callbackRegistry = getCallbackRegistry();

        callbackRegistry.addCallback(startJmxReporting);
    }

}
