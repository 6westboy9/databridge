package org.westboy.databridge.common.until;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;


/**
 * 重试工具测试类
 *
 * @author mumu
 * @since 2023/4/2 20:35
 */
@Slf4j
class RetryUtilTest {

    @BeforeEach
    void before() {
        System.setProperty("datax.home", "../test");
        System.setProperty("log.file.name", "retry");
    }

    @Test
    void executeWithRetry() {
        try {
            ArrayList<Class<?>> exceptions = new ArrayList<>();
            String result = RetryUtil.executeWithRetry(() -> {
                System.out.println(1 / 0);
                return "hello";
            }, 9, 1000L, false, exceptions);
            log.info("结果：{}", result);
        } catch (Exception e) {
            assertEquals("/ by zero", e.getMessage());
        }
    }
}