package org.westboy.databridge.common.plugin;

import org.westboy.databridge.common.config.Config;

/**
 * @author mumu
 * @since 2023/4/11 20:40
 */
public abstract class AbstractJobPlugin extends AbstractPlugin {

    private JobPluginCollector jobPluginCollector;
    private Config jobConfig;

    public JobPluginCollector getJobPluginCollector() {
        return jobPluginCollector;
    }

    public void setJobPluginCollector(JobPluginCollector jobPluginCollector) {
        this.jobPluginCollector = jobPluginCollector;
    }

    
}
