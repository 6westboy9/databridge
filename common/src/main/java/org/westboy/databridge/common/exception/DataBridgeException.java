package org.westboy.databridge.common.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import org.westboy.databridge.common.ErrorCode;
import org.westboy.databridge.common.errorcode.DatabaseErrorCode;
import org.westboy.databridge.common.until.DataBaseType;

/**
 * 异常类
 *
 * @author mumu
 * @since 2023/4/2 12:49
 */
public class DataBridgeException extends RuntimeException {

    /**
     * 错误码
     */
    @Getter
    private final ErrorCode errorCode;

    private DataBridgeException(ErrorCode errorCode, String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.errorCode = errorCode;
    }

    /**
     * 创建异常对象
     * 
     * @param errorCode 错误码
     * @param args      错误码对应错误描述中的参数列表
     * @return 异常对象
     */
    public static DataBridgeException asDataBridgeException(ErrorCode errorCode, Object... args) {
        return new DataBridgeException(errorCode, StrUtil.format(errorCode.getDescription(), args), null);
    }

    /**
     * 创建异常对象
     * 
     * @param errorCode 错误码
     * @param throwable 异常对象
     * @param args      错误码对应错误描述中的参数列表
     * @return 异常对象
     */
    public static DataBridgeException asDataBridgeException(ErrorCode errorCode, Throwable throwable, Object... args) {
        return new DataBridgeException(errorCode, StrUtil.format(errorCode.getDescription(), args), throwable);
    }

    /**
     * TODO 需要迁移
     * 
     * @param type   数据库类型
     * @param e      异常
     * @param user   数据库用户
     * @param dbName 数据库名称
     * @return
     */
    public static DataBridgeException asDataBridgeException(DataBaseType type, Throwable throwable, String user, String dbName) {
        switch (type) {
            case MYSQL:
            case MYSQL8:
                ErrorCode code = DatabaseErrorCode.of(throwable.getMessage());
                return DataBridgeException.asDataBridgeException(code, throwable, user, dbName);
            case ORACLE:
                return null;
               
        }
        return null;
    }
}
