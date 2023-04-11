package org.westboy.databridge.core;

import org.westboy.databridge.common.config.Config;
import org.westboy.databridge.common.plugin.PluginLoader;
import org.westboy.databridge.core.job.AbstractContainer;
import org.westboy.databridge.core.job.JobContainer;

/**
 * 启动引擎
 */
public class Engine {

    public static void main(String[] args) {
        Config config = ConfigParser.parse();
        Engine engine = new Engine();
        engine.start(config);
    }

    private void start(Config allConfig) {
        PluginLoader.init(allConfig);
        AbstractContainer container = new JobContainer(allConfig);
        container.start();
    }
}
