package org.westboy.databridge.core;

import org.westboy.databridge.common.plugin.PluginLoader;
import org.westboy.databridge.common.config.AllConfig;
import org.westboy.databridge.core.job.AbstractContainer;
import org.westboy.databridge.core.job.JobContainer;

/**
 * 启动引擎
 */
public class Engine {

    public static void main(String[] args) {
        AllConfig allConfig = new AllConfig();
        Engine engine = new Engine();
        engine.start(allConfig);
    }

    private void start(AllConfig allConfig) {
        PluginLoader.init(allConfig);
        AbstractContainer container = new JobContainer(allConfig);
        container.start();
    }
}
