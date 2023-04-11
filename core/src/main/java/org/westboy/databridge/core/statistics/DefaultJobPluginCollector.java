package org.westboy.databridge.core.statistics;

import org.westboy.databridge.common.plugin.JobPluginCollector;

/**
 * 默认Job插件收集组件
 *
 * @author mumu
 * @since 2023/4/11 22:55
 */
public class DefaultJobPluginCollector implements JobPluginCollector {

    private AbstractContainerCommunicator communicator;

    public DefaultJobPluginCollector(AbstractContainerCommunicator communicator) {
        this.communicator = communicator;
    }
}
