package org.westboy.databridge.common.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

import org.westboy.databridge.common.errorcode.CommonErrorCode;
import org.westboy.databridge.common.exception.DataBridgeException;

/**
 * 配置抽象类
 *
 * @author mumu
 * @since 2023/4/8 18:21
 */
public final class Config {

    private final JSON root;

    private Config(String json) {
        try {
            root = JSONUtil.parse(json);
        } catch (Exception e) {
            throw DataBridgeException.asDataBridgeException(CommonErrorCode.CONFIG_ERROR, json);
        }
    }

    /**
     * 初始化空配置
     *
     * @return 空配置
     */
    public static Config newDefault() {
        return Config.from("{}");
    }

    /**
     * 从JSON字符串中加载配置
     *
     * @param json JSON字符串
     * @return 配置
     */
    public static Config from(String json) {
        return new Config(json);
    }

    /**
     * 根据提供表达式解析获取对应的值，不存在时提供默认值
     *
     * @param expression   解析表达式
     * @param defaultValue 默认值
     * @param <T>          值类型泛型
     * @return 值
     */
    public <T> T getValue(String expression, T defaultValue) {
        return JSONUtil.getByPath(root, expression, defaultValue);
    }

    /**
     * 根据提供表达式解析获取对应的值
     *
     * @param expression 解析表达式
     * @param clazz      值类型
     * @param <T>        值类型泛型
     * @return 值
     */
    public <T> T getValue(String expression, Class<T> clazz) {
        return root.getByPath(expression, clazz);
    }

    /**
     * 根据提供表达式解析获取子配置
     *
     * @param expression 解析表达式
     * @return 子配置
     */
    public Config getConfig(String expression) {
        JSON json = root.getByPath(expression, JSON.class);
        if (ObjectUtil.isNull(json)) {
            return null;
        }
        return new Config(json.toJSONString(0));
    }
}
