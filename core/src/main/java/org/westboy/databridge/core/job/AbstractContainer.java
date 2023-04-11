package org.westboy.databridge.core.job;

import org.westboy.databridge.common.config.Config;
import org.westboy.databridge.core.statistics.AbstractContainerCommunicator;

/**
 * 容器抽象类
 *
 * @author mumu
 * @since 2023/4/8 09:52
 */
public abstract class AbstractContainer {

    protected final Config allConfig;
    private AbstractContainerCommunicator containerCommunicator;

    public AbstractContainer(Config allConfig) {
        this.allConfig = allConfig;
    }

    public Config getAllConfig() {
        return allConfig;
    }

    public AbstractContainerCommunicator getContainerCommunicator() {
        return containerCommunicator;
    }

    public void setContainerCommunicator(AbstractContainerCommunicator containerCommunicator) {
        this.containerCommunicator = containerCommunicator;
    }

    public abstract void start();

}
