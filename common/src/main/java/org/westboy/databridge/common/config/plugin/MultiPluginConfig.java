package org.westboy.databridge.common.config.plugin;

import lombok.Data;

@Data
public class MultiPluginConfig {

    private PluginConfig reader;
    private PluginConfig writer;

    public PluginConfig getBy(String pluginKey) {
        return null;
    }
}
