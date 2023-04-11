package org.westboy.databridge.common.plugin;

/**
 * @author mumu
 * @since 2023/4/11 20:39
 */
public abstract class AbstractTaskPlugin extends AbstractPlugin {

    private TaskPluginCollector taskPluginCollector;

    public TaskPluginCollector getTaskPluginCollector() {
        return taskPluginCollector;
    }

    public void setTaskPluginCollector(TaskPluginCollector taskPluginCollector) {
        this.taskPluginCollector = taskPluginCollector;
    }
}
