package org.westboy.databridge.common.until;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 重试工具类
 *
 * @author mumu
 * @since 2023/4/2 12:39
 */
@Slf4j
public class RetryUtil {

    private static final long MAX_SLEEP_MILLISECOND = 256 * 1000;

    public static <T> T executeWithRetry(Callable<T> callable,
                                         int retryTimes,
                                         long sleepTimeInMilliSecond,
                                         boolean exponential) throws Exception {
        Retry retry = new Retry();
        return retry.doRetry(callable, retryTimes, sleepTimeInMilliSecond, exponential, null);
    }

    public static <T> T executeWithRetry(Callable<T> callable,
                                         int retryTimes,
                                         long sleepTimeInMilliSecond,
                                         boolean exponential,
                                         List<Class<?>> retryExceptionClasses) throws Exception {
        Retry retry = new Retry();
        return retry.doRetry(callable, retryTimes, sleepTimeInMilliSecond, exponential, retryExceptionClasses);
    }

    // TODO 异步

    static class Retry {

        /**
         * 重试次数工具方法
         *
         * @param callable               实际逻辑
         * @param retryTimes             最大重试次数
         * @param sleepTimeInMilliSecond 执行失败后休眠对应的时间再重试
         * @param exponential            休眠时间是否指数递增
         * @param retryExceptionClasses  出现指定的异常类型时才进行重试
         * @param <T>                    执行返回对象泛型
         * @return 执行结果
         * @throws Exception 调用函数执行异常
         */
        public <T> T doRetry(Callable<T> callable, int retryTimes, long sleepTimeInMilliSecond,
                             boolean exponential, List<Class<?>> retryExceptionClasses) throws Exception {

            if (ObjUtil.isNull(callable)) {
                throw new IllegalArgumentException("系统编程错误，入参callable不能为空！");
            }

            if (retryTimes < 1) {
                throw new IllegalArgumentException(String.format("系统编程错误，入参retryTimes[%d]不能小于1！", retryTimes));
            }

            Exception exception = null;
            for (int i = 0; i < retryTimes; i++) {
                try {
                    return call(callable);
                } catch (Exception e) {
                    exception = e;
                    if (i == 0) {
                        log.error(String.format("调用方法异常Msg：%s", exception.getMessage()), exception);
                    }

                    if (CollUtil.isNotEmpty(retryExceptionClasses)) {
                        boolean needRetry = false;
                        for (Class<?> eachExceptionClass : retryExceptionClasses) {
                            if (eachExceptionClass == e.getClass()) {
                                needRetry = true;
                                break;
                            }
                        }
                        if (!needRetry) {
                            throw exception;
                        }
                    }

                    if (i + 1 < retryTimes && sleepTimeInMilliSecond > 0) {
                        long startTime = System.currentTimeMillis();
                        long timeToSleep = exponential ? sleepTimeInMilliSecond * (long) Math.pow(2, i) : sleepTimeInMilliSecond;
                        if (timeToSleep >= MAX_SLEEP_MILLISECOND) {
                            timeToSleep = MAX_SLEEP_MILLISECOND;
                        }

                        try {
                            Thread.sleep(timeToSleep);
                        } catch (InterruptedException ignored) {
                        }

                        long realTimeSleep = System.currentTimeMillis() - startTime;

                        log.error("调用方法异常, 即将尝试执行第{}次重试，本次重试计划等待{}ms，实际等待{}ms",
                                i + 1, timeToSleep, realTimeSleep, e);
                    }
                }
            }
            throw exception;
        }

        protected <T> T call(Callable<T> callable) throws Exception {
            return callable.call();
        }
    }
}
