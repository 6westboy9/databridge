package org.westboy.databridge.common.config.core;

import lombok.Data;

@Data
public class CoreContainerConfig {

    private JobConfig job;

    @Data
    public class JobConfig {
        private long jobId;
        private long reportInterval;
    }


}
