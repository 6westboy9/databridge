package org.westboy.databridge.common.config.job;

import lombok.Data;

@Data
public class JobConfig {
    private JobSettingConfig setting;
    private JobContentConfig content;
}
