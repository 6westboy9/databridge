package org.westboy.databridge.core.job;

import lombok.extern.slf4j.Slf4j;

/**
 * Job实例运行在Job容器中，它是所有任务的管理组件，负责初始化、拆分、调度、运行、回收、监控和汇
 *
 * @author mumu
 * @since 2023/4/2 12:03
 */
@Slf4j
public class JobContainer extends AbstractJobContainer {

    private long jobId;
    private String readPluginName;
    private String writePluginName;
    private JobConfig jobConfig;

    @Override
    protected void start() {
        log.info("Job任务执行[start]");
        boolean hasException = false;
        try {
            boolean dryRun = jobConfig.getDryRun();
            if (dryRun) {
                log.info("Job任务执行[preCheck]");
                // preCheck();
            } else {
                log.info("Job任务执行[preHandle]");
                // preHandle();
                log.info("Job任务执行[init]");
                // init();
                log.info("Job任务执行[prepare]");
                // prepare();
                log.info("Job任务执行[split]");
                // split();
                log.info("Job任务执行[schedule]");
                // schedule();
                log.info("Job任务执行[post]");
                // post();
                log.info("Job任务执行[postHandle]");
                // postHandle();
                log.info("Job任务执行[invokeHooks]");
                // invokeHooks();
            }
        } catch (Throwable throwable) {
            log.error("Job任务执行异常", throwable);
            hasException = true;
        } finally {

        }
    }
}
