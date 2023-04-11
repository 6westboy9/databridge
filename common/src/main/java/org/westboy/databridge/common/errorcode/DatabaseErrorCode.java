package org.westboy.databridge.common.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.westboy.databridge.common.ErrorCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 数据库错误码
 *
 * @author mumu
 * @since 2023/4/2 12:55
 */
@Getter
@AllArgsConstructor
public enum DatabaseErrorCode implements ErrorCode {

    MYSQL_CONN_USER_PWD_ERROR("MYSQLErrCode-01", "Access denied","数据库用户名或者密码错误,请检查填写的账号密码或者联系DBA确认账号和密码是否正确"),
    MYSQL_CONN_IP_PORT_ERROR("MYSQLErrCode-02", "Communications link failure","数据库服务的IP地址或者端口错误,请检查填写的IP地址和Port或者联系DBA确认IP地址和端口是否正确"),
    MYSQL_CONN_DB_ERROR("MYSQLErrCode-03", "Unknown database","数据库名称错误,请检查数据库实例名称或者联系DBA确认该实例是否存在并且在正常服务"),

    CONN_DB_ERROR("DBUtilErrorCode-10", "", "数据库连接失败，根据您配置的连接信息:%s获取数据库连接失败,请检查您的配置并作出修改");

    private final String code;
    private final String subCode;
    private final String description;

    private static final Map<String, DatabaseErrorCode> MAP = new HashMap<>();

    static {
        for (DatabaseErrorCode errorCode : DatabaseErrorCode.values()) {
            MAP.put(errorCode.getSubCode(), errorCode);
        }
    }

    public static ErrorCode of(String message) {
        Set<Map.Entry<String, DatabaseErrorCode>> entries = MAP.entrySet();
        for (Map.Entry<String, DatabaseErrorCode> entry : entries) {
            if (entry.getKey().contains(message)) {
                return entry.getValue();
            }
        }
        return MYSQL_CONN_DB_ERROR;
    }
}
