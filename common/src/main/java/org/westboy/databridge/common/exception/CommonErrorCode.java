package org.westboy.databridge.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.westboy.databridge.common.ErrorCode;

/**
 * 公共错误码
 *
 * @author mumu
 * @since 2023/4/8 18:25
 */
@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    CONFIG_ERROR("Common-00", "您提供的配置信息不是合法的JSON文件格式:{}，请检查您的配置");

    private final String code;
    private final String description;

}
