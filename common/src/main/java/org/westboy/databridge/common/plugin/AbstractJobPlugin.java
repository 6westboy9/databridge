package org.westboy.databridge.common.plugin;

/**
 * @author mumu
 * @since 2023/4/11 20:40
 */
public abstract class AbstractJobPlugin extends AbstractPlugin {

    private JobPluginCollector jobPluginCollector;

    public JobPluginCollector getJobPluginCollector() {
        return jobPluginCollector;
    }

    public void setJobPluginCollector(JobPluginCollector jobPluginCollector) {
        this.jobPluginCollector = jobPluginCollector;
    }
}
