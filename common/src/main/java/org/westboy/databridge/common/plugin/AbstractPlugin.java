package org.westboy.databridge.common.plugin;

import org.westboy.databridge.common.config.Config;

/**
 * 插件接口定义
 *
 * @author mumu
 * @since 2023/4/11 10:21
 */
public abstract class AbstractPlugin implements Plugin {

    private Config pluginConfig;
    private Config jobConfig;

}