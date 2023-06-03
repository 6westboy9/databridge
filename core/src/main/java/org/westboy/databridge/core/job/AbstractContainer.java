package org.westboy.databridge.core.job;

import org.westboy.databridge.common.config.AllConfig;
import org.westboy.databridge.core.statistics.AbstractContainerCommunicator;

/**
 * 容器抽象类
 *
 * @author mumu
 * @since 2023/4/8 09:52
 */
public abstract class AbstractContainer {

    protected final AllConfig allConfig;
    private AbstractContainerCommunicator containerCommunicator;

    public AbstractContainer(AllConfig allConfig) {
        this.allConfig = allConfig;
    }

    public AllConfig getAllConfig() {
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
