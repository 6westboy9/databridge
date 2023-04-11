package org.westboy.databridge.common.errorcode;

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
public enum FrameworkErrorCode implements ErrorCode {

    ARGUMENT_ERROR("Framework-01", ""),
    RUNTIME_ERROR("Framework-02", "DataBridge运行过程出错"),

    PLUGIN_LOAD_ERROR("Framework-10", "DataBridge插件加载错误")
    ;

    private final String code;
    private final String description;

}
