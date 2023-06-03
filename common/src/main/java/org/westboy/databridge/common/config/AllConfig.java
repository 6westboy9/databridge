package org.westboy.databridge.common.config;

import lombok.Data;
import org.westboy.databridge.common.config.common.CommonConfig;
import org.westboy.databridge.common.config.core.CoreConfig;
import org.westboy.databridge.common.config.entry.EntryConfig;
import org.westboy.databridge.common.config.job.JobConfig;
import org.westboy.databridge.common.config.plugin.MultiPluginConfig;

@Data
public class AllConfig {
    private JobConfig job;
    private CommonConfig common;
    private CoreConfig core;
    private EntryConfig entry;
    private MultiPluginConfig plugin;
}
