package org.westboy.databridge.core.job;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.westboy.databridge.common.config.AllConfig;
import org.westboy.databridge.common.config.Config;
import org.westboy.databridge.common.errorcode.FrameworkErrorCode;
import org.westboy.databridge.common.exception.DataBridgeException;
import org.westboy.databridge.common.plugin.AbstractJobPlugin;
import org.westboy.databridge.common.plugin.JobPluginCollector;
import org.westboy.databridge.common.plugin.PluginLoader;
import org.westboy.databridge.common.plugin.PluginType;
import org.westboy.databridge.common.spi.Reader;
import org.westboy.databridge.common.spi.Writer;
import org.westboy.databridge.core.constant.CoreConstant;
import org.westboy.databridge.core.constant.JobConstant;
import org.westboy.databridge.core.statistics.DefaultJobPluginCollector;

/**
 * Job实例运行在Job容器中，它是所有任务的管理组件，负责初始化、拆分、调度、运行、回收、监控和汇
 *
 * @author mumu
 * @since 2023/4/2 12:03
 */
@Slf4j
public class JobContainer extends AbstractContainer {

    private long jobId;
    private String readPluginName;
    private String writePluginName;
    private Config jobConfig;
    private Reader.Job jobReader;
    private Writer.Job jobWriter;

    public JobContainer(AllConfig allConfig) {
        super(allConfig);
    }

    @Override
    public void start() {
        log.info("start");
        boolean hasException = false;
        boolean isRryRun = false;
        try {
            // isRryRun = jobConfig.getValue(JobConstant.JOB_SETTING_DRY_RUN, Boolean.class);
            if (isRryRun) {
            //     log.info("preCheck");
            //     preCheck();
            } else {
                log.info("preHandle");
                preHandle();
                log.info("init");
                init();
                log.info("prepare");
                prepare();
                log.info("split");
                split();
                log.info("schedule");
                schedule();
                log.info("post");
                post();
                log.info("postHandle");
                postHandle();
                log.info("invokeHooks");
                invokeHooks();
            }
        } catch (Throwable throwable) {
            log.error("error", throwable);
            hasException = true;
        } finally {
            log.info("end");
            // TODO
        }
    }

    private void preCheck() {


    }

    /**
     * 加载PreHandler并执行（插件式）
     */
    private void preHandle() {
        // String pluginTypeStr = allConfig.getValue(JobConstant.JOB_PRE_HANDLER_PLUGIN_TYPE, String.class);
        // if (StrUtil.isEmpty(pluginTypeStr)) {
        //     return;
        // }
        //
        // PluginType preHandlerPluginType;
        // try {
        //     preHandlerPluginType = PluginType.valueOf(pluginTypeStr.toUpperCase());
        // } catch (Exception e) {
        //     String description = "Job的PreHandler插件类型设置错误原因:" + e.getMessage();
        //     throw DataBridgeException.asDataBridgeException(FrameworkErrorCode.CONFIG_ERROR, description);
        // }
        //
        // String preHandlerPluginName = allConfig.getValue(JobConstant.JOB_PRE_HANDLER_PLUGIN_NAME, String.class);
        // PluginLoader pluginLoader = PluginLoader.getInstance();
        // // 将所有Reader、Writer、Handler都认为是Job容器插件
        // AbstractJobPlugin preHandler = pluginLoader.loadJobPlugin(preHandlerPluginType, preHandlerPluginName);
        // // TODO 可是此时并没有设置ContainerCommunicator组件，获取到的一定为null
        // DefaultJobPluginCollector jobPluginCollector = new DefaultJobPluginCollector(getContainerCommunicator());
        // preHandler.setJobPluginCollector(jobPluginCollector);
        // // 总觉得不合适，仔细想想
        // // preHandler.preHandle();
    }

    private void init() {
        this.jobId = allConfig.getCore().getContainer().getJob().getJobId();
        JobPluginCollector jobPluginCollector = new DefaultJobPluginCollector(getContainerCommunicator());
        this.jobReader = initJobReader(jobPluginCollector);
        this.jobWriter = initJobWriter(jobPluginCollector);
    }

    private Reader.Job initJobReader(JobPluginCollector jobPluginCollector) {
        this.readPluginName = allConfig.getJob().getContent().getReader().getName();
        Reader.Job jobReader = (Reader.Job) PluginLoader.getInstance().loadJobPlugin(PluginType.READER, this.readPluginName);

        jobReader.setJobPluginCollector(jobPluginCollector);
        jobReader.init();
        return jobReader;
    }

    private Writer.Job initJobWriter(JobPluginCollector jobPluginCollector) {
        return null;
    }

    private void prepare() {

    }

    private void split() {

    }

    private void schedule() {
        // 任务调度核心流程
        // 1.将Job切分成多个小的Task（子任务），以便于并发执行，Task便是DataBridge作业的最小单元，每一个Task都会负责一部分数据的同步工作
        // 2.切分多个Task之后，会根据配置的并发数量将拆分成的Task重新组合，组装成TaskGroup（任务组），每个TaskGroup负责以一定的并发运行完成分配好的Task，默认单个任务组的并发数量是5
        // 3.每一个Task由TaskGroup负责启动，Task启动后，会固定启动Reader->Channel->Writer的线程来完成任务同步工作
        // 4.Job需要监控等待多个TaskGroup任务执行完成后Job退出成功，否则异常退出

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
