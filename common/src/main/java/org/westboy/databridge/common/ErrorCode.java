package org.westboy.databridge.common;

/**
 * 错误码定义
 *
 * @author mumu
 * @since 2023/4/2 12:11
 */
public interface ErrorCode {

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 获取错误码描述信息
     *
     * @return 错误码描述信息
     */
    String getDescription();

    /**
     * 必须提供此方法实现
     *
     * @return 错误信息
     */
    String toString();

}
