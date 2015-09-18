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

import com.hivemq.spi.HiveMQPluginModule;
import com.hivemq.spi.PluginEntryPoint;
import com.hivemq.spi.plugin.meta.Information;


/**
 * This plugin exposes every metric which is registered with the central
 * {@link com.codahale.metrics.MetricRegistry} via JMX
 *
 * @author Christoph Schaebel
 */
@Information(name = "HiveMQ JMX Metrics Reporting Plugin", author = "dc-square", version = "3.0.0")
public class JmxMetricsPluginModule extends HiveMQPluginModule {

    @Override
    protected void configurePlugin() {
    }

    /**
     * This method needs to return the main class of the plugin.
     *
     * @return callback priority
     */
    @Override
    protected Class<? extends PluginEntryPoint> entryPointClass() {
        return JmxMetricsMainClass.class;
    }
}
