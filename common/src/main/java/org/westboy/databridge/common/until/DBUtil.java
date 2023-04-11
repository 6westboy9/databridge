package org.westboy.databridge.common.until;

import org.westboy.databridge.common.constant.Constant;
import org.westboy.databridge.common.errorcode.DatabaseErrorCode;
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
public final class DBUtil {

    /**
     * 获取数据库连接（带重试机制）
     *
     * @param type     数据库类型
     * @param jdbcUrl  连接地址
     * @param user     用户名
     * @param password 密码
     * @return 数据库连接
     */
    public static Connection getConnection(DataBaseType type, String jdbcUrl, String user, String password) {
        return getConnection(type, jdbcUrl, user, password, String.valueOf(1000));
    }

    /**
     * 获取数据库连接（带重试机制）
     *
     * @param type          数据库类型
     * @param jdbcUrl       连接地址
     * @param user          用户名
     * @param password      密码
     * @param socketTimeout 获取连接超时时间
     * @return 数据库连接
     */
    public static Connection getConnection(DataBaseType type, String jdbcUrl, String user, String password, String socketTimeout) {
        try {
            return RetryUtil.executeWithRetry(() -> DBUtil.connect(type, jdbcUrl, user, password, socketTimeout),
                    9,
                    1000L,
                    true);
        } catch (Exception e) {
            throw DataBridgeException.asDataBridgeException(DatabaseErrorCode.CONN_DB_ERROR, jdbcUrl);
        }
    }

    /**
     * 获取数据库连接（不带重试机制）
     *
     * @param type     数据库类型
     * @param jdbcUrl  连接地址
     * @param username 用户名
     * @param password 密码
     * @return 数据库连接
     */
    public static Connection getConnectionWithoutRetry(DataBaseType type, String jdbcUrl, String username, String password) {
        return getConnectionWithoutRetry(type, jdbcUrl, username, password, String.valueOf(Constant.SOCKET_TIMEOUT_SECONDS * 1000));
    }

    /**
     * 获取数据库连接（不带重试机制）
     *
     * @param type          数据库类型
     * @param jdbcUrl       连接地址
     * @param username      用户名
     * @param password      密码
     * @param socketTimeout 获取连接超时时间
     * @return 数据库连接
     */
    public static Connection getConnectionWithoutRetry(DataBaseType type, String jdbcUrl, String username, String password, String socketTimeout) {
        return connect(type, jdbcUrl, username, password, socketTimeout);
    }

    private static synchronized Connection connect(DataBaseType type, String url, String user, String pass) {
        return connect(type, url, user, pass, String.valueOf(Constant.SOCKET_TIMEOUT_SECONDS * 1000));
    }

    private static synchronized Connection connect(DataBaseType type, String url, String user, String password, String socketTimeout) {
        Properties properties = new Properties();
        properties.put("user", user);
        properties.put("password", password);
        return connect(type, url, properties);
    }

    private static synchronized Connection connect(DataBaseType type, String url, Properties properties) {
        try {
            Class.forName(type.getDriveClassName());
            // 该参数含义：该方法只适用于建立JDBC数据库连接时的超时时间，而不适用于执行查询或更新操作时的超时时间
            DriverManager.setLoginTimeout(Constant.TIMEOUT_SECONDS);
            return DriverManager.getConnection(url, properties);
        } catch (Exception e) {
            String user = properties.getProperty("user");
            throw DataBridgeException.asDataBridgeException(type, e, user, null);
        }
    }

}
