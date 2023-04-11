package org.westboy.databridge.common.plugin;

/**
 * 插件接口定义
 *
 * @author mumu
 * @since 2023/4/11 10:21
 */
public interface Plugin {

    String getDeveloper();

    String getDescription();

    void setPluginConfig();

    void init();

    void destroy();

}