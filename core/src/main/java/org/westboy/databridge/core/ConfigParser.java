package org.westboy.databridge.core;

import org.westboy.databridge.common.config.Config;

/**
 * 配置解析组件
 *
 * @author mumu
 * @since 2023/4/11 22:24
 */
public class ConfigParser {

    public static Config parse() {
        return Config.newDefault();
    }

}
