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

package com.hivemq.plugins.metrics.jmx.callbacks;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.hivemq.spi.callback.CallbackPriority;
import com.hivemq.spi.callback.events.broker.OnBrokerStart;
import com.hivemq.spi.callback.events.broker.OnBrokerStop;
import com.hivemq.spi.callback.exception.BrokerUnableToStartException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This callback starts/stops the JMX Metrics Reporting when HiveMQ starts/stops
 *
 * @author Christoph Schaebel
 * @since 3.0
 */
@Singleton
public class StartJmxReporting implements OnBrokerStart, OnBrokerStop {

    private static final Logger log = LoggerFactory.getLogger(StartJmxReporting.class);

    private final MetricRegistry metricRegistry;
    private JmxReporter jmxReporter;

    @Inject
    public StartJmxReporting(final MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Override
    public void onBrokerStart() throws BrokerUnableToStartException {

        if (metricRegistry.getNames().isEmpty()) {
            log.warn("Metric Registry is empty, JMX Metrics Reporting disabled");
            return;
        }

        for (String name : metricRegistry.getNames()) {
            log.trace("Reporting metric {} via JMX", name);
        }

        jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
        jmxReporter.start();
        log.info("JMX Metrics Reporting started.");
    }

    @Override
    public void onBrokerStop() {
        if (jmxReporter != null) {
            jmxReporter.stop();
        }
    }

    @Override
    public int priority() {
        return CallbackPriority.MEDIUM;
    }
}
