package org.westboy.databridge.common.exception;

import lombok.Getter;
import org.westboy.databridge.common.ErrorCode;
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

    private DataBridgeException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.toString() + " - " + errorMessage);
        this.errorCode = errorCode;
    }

    public static DataBridgeException asDataBridgeException(ErrorCode errorCode, String errorMessage) {
        return new DataBridgeException(errorCode, errorMessage);
    }

    public static Exception asDataBridgeException(DBErrorCode errorCode, Object... args) {
        String errorMessage = String.format(errorCode.getDescription(), args);
        return new DataBridgeException(errorCode, errorMessage);
    }

    public static DataBridgeException asDataBridgeException(DataBaseType type, Exception e, String user, String dbName) {
        switch (type) {
            case MYSQL:
                ErrorCode code = DBErrorCode.of(e.getMessage());
                return DataBridgeException.asDataBridgeException(code, "该数据库名为：" + dbName + "，具体错误信息为：" + e);
        }
        return null;
    }


}
