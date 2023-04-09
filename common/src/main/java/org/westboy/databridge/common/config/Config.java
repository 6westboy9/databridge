package org.westboy.databridge.common.config;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.westboy.databridge.common.exception.CommonErrorCode;
import org.westboy.databridge.common.exception.DataBridgeException;

/**
 * 配置抽象类
 *
 * @author mumu
 * @since 2023/4/8 18:21
 */
public abstract class Config {

    private final JSON root;

    public Config(String json) {
        try {
            root = JSONUtil.parse(json);
        } catch (Exception e) {
            throw DataBridgeException.asDataBridgeExceptionWithArgs(CommonErrorCode.CONFIG_ERROR, json);
        }
    }

    public <T> T get(String expression, T defaultValue) {
        return JSONUtil.getByPath(root, expression, defaultValue);
    }
}
