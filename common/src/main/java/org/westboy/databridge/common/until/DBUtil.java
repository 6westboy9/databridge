package org.westboy.databridge.common.until;

import lombok.extern.slf4j.Slf4j;
import org.westboy.databridge.common.constant.Constant;
import org.westboy.databridge.common.exception.DBErrorCode;
import org.westboy.databridge.common.exception.DataBridgeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 数据库操作工具类
 *
 * @author mumu
 * @since 2023/4/2 12:27
 */
@Slf4j
public final class DBUtil {

    public static Connection getConnection(DataBaseType type, String jdbcUrl, String user, String password) {
        return getConnection(type, jdbcUrl, user, password, String.valueOf(1000));
    }

    public static Connection getConnection(DataBaseType type, String jdbcUrl, String user, String password, String socketTimeout) {
        try {
            return RetryUtil.executeWithRetry(() -> DBUtil.connect(type, jdbcUrl, user, password, socketTimeout),
                    9,
                    1000L,
                    true);
        } catch (Exception e) {
            throw DataBridgeException.asDataBridgeException(DBErrorCode.CONN_DB_ERROR, jdbcUrl);
        }
    }

    private static Connection connect(DataBaseType type, String url, String user, String password, String socketTimeout) {
        Properties properties = new Properties();
        properties.put("user", user);
        properties.put("password", password);
        return connect(type, url, properties);
    }

    private static Connection connect(DataBaseType type, String url, Properties properties) {
        try {
            Class.forName(type.getDriveClassName());
            // TODO 该参数含义
            DriverManager.setLoginTimeout(Constant.TIMEOUT_SECONDS);
            return DriverManager.getConnection(url, properties);
        } catch (Exception e) {
            throw DataBridgeException.asDataBridgeException(type, e, properties.getProperty("user"), null);
        }
    }

}
