package org.westboy.databridge.common.plugin;

import org.westboy.databridge.common.config.plugin.MultiPluginConfig;
import org.westboy.databridge.common.config.plugin.PluginConfig;

/**
 * 插件接口定义
 *
 * @author mumu
 * @since 2023/4/11 10:21
 */
public interface Plugin {

    /**
     * 此处设置的是插件的配置
     */
    void setPluginConfig(PluginConfig pluginConfig);

    void init();

    void destroy();

}