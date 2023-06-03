package org.westboy.databridge.common.plugin;

import org.westboy.databridge.common.config.plugin.MultiPluginConfig;
import org.westboy.databridge.common.config.plugin.PluginConfig;


/**
 * 插件抽象类实现
 *
 * @author mumu
 * @since 2023/4/11 10:21
 */
public abstract class AbstractPlugin implements Plugin {

    private PluginConfig pluginConfig;

    @Override
    public void setPluginConfig(PluginConfig pluginConfig) {
        this.pluginConfig = pluginConfig;
    }

}