package org.westboy.databridge.core.job;

import org.westboy.databridge.common.config.Config;

/**
 * @author mumu
 * @since 2023/4/8 18:46
 */
public class JobConfig extends Config {

    private static final String DATABRIDGE_JOB_SETTING_DRY_RUN = "job.setting.dry.run";

    public JobConfig(String json) {
        super(json);
    }

    public boolean getDryRun() {
        return get(DATABRIDGE_JOB_SETTING_DRY_RUN, false);
    }

}
