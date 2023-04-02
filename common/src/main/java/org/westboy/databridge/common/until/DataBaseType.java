package org.westboy.databridge.common.until;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据库类型
 * @author mumu
 * @since 2023/4/2 12:33
 */
@Getter
@AllArgsConstructor
public enum DataBaseType {

    /**
     * 目前支持且使用到的数据库与驱动
     */
    MYSQL("mysql", "com.mysql.jdbc.Driver"),
    ORACLE("oracle", "oracle.jdbc.OracleDriver"),
    SQL_SEVER("sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    ;

    private final String typeName;
    private final String driveClassName;
}
