package org.westboy.databridge.common.config.job;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JobSettingConfig {

    private Speed speed;
    private ErrorLimit error;

    @Data
    public static class Speed {
        private Integer channel;
        private Long bytes;
    }

    @Data
    public static class ErrorLimit {
        private Long record;
        private BigDecimal percentage;
    }
}
