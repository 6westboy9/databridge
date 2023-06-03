package org.westboy.databridge.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfigPath {

    /* --- core --- */
    CORE_CONTAINER_TASK_GROUP_CHANNEL("core.container.task-group.channel"),
    CORE_CONTAINER_MODEL("core.container.mode"),
    CORE_CONTAINER_JOB_ID("core.container.job.id"),

    /* --- job --- */
    JOB_CONTENT_READER_NAME("job.content.reader.name"),
    JOB_CONTENT_READER_PARAMETER("job.content.reader.parameter");

    private final String path;
}
