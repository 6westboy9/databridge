package org.westboy.databridge.common.plugin;

import org.westboy.databridge.common.config.Config;

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
    void setConfig(Config config);

    void init();

    void destroy();

}