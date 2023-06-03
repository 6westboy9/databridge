package org.westboy.databridge.common.config.job;

import lombok.Data;

import java.util.List;

@Data
public class JobContentConfig {

    private Details reader;
    private Details writer;

    @Data
    public static class Details {
        private String name;
        private Parameter parameter;
    }

    @Data
    public static class Parameter {
        private String jdbcUrl;
        private String username;
        private String password;
        private String table;
        private List<String> column;
        private String splitPk;
    }
}
