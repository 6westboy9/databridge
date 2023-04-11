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
                this.preCheck();
            } else {
                log.info("Job任务执行[preHandle]");
                // 加载PreHandler并执行（插件式）
                this.preHandle();
                log.info("Job任务执行[init]");
                // Job#init -- 生成Reader.Job和Writer.Job对象（插件式）
                this.init();
                log.info("Job任务执行[prepare]");
                // Job#prepare
                this.prepare();
                log.info("Job任务执行[split]");
                // Job#split
                this.split();
                log.info("Job任务执行[schedule]");
                // 任务调度核心流程
                // 1.将Job切分成多个小的Task（子任务），以便于并发执行，Task便是DataBridge作业的最小单元，每一个Task都会负责一部分数据的同步工作
                // 2.切分多个Task之后，会根据配置的并发数量将拆分成的Task重新组合，组装成TaskGroup（任务组），每个TaskGroup负责以一定的并发运行完成分配好的Task，默认单个任务组的并发数量是5
                // 3.每一个Task由TaskGroup负责启动，Task启动后，会固定启动Reader->Channel->Writer的线程来完成任务同步工作
                // 4.Job需要监控等待多个TaskGroup任务执行完成后Job退出成功，否则异常退出
                this.schedule();
                log.info("Job任务执行[post]");
                // Job#post
                this.post();
                log.info("Job任务执行[postHandle]");
                // 加载PostHandler并执行（插件式）
                this.postHandle();
                log.info("Job任务执行[invokeHooks]");
                this.invokeHooks();
            }
        } catch (Throwable throwable) {
            log.error("Job任务执行异常", throwable);
            hasException = true;
        } finally {
            // TODO
        }
    }

    private void preCheck() {
    }

    /**
     * 加载PreHandler并执行（插件式）
     */
    private void preHandle() {
    }

    private void init() {
    }

    private void prepare() {
    }

    private void split() {
    }

    private void schedule() {
    }

    private void post() {
    }

    /**
     * 加载PostHandler并执行（插件式）
     */
    private void postHandle() {
    }

    private void invokeHooks() {
    }

}
