package org.westboy.databridge.common.config.entry;

import lombok.Data;

@Data
public class EntryConfig {
    /**
     * jvm参数配置："-Xms1G -Xmx1G"
     */
    private String jvm;
}
