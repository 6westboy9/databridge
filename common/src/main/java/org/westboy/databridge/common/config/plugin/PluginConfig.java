package org.westboy.databridge.common.config.plugin;

import lombok.Data;

@Data
public class PluginConfig {
    private String name;
    private String path;
    private String description;
    private String developer;
    private String className;
}
