package org.westboy.databridge.common.until;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class DBUtilTest {

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8";
        String user = "root";
        String password = "@Wpb123456";
        Connection connection = DBUtil.getConnection(DataBaseType.MYSQL8, jdbcUrl, user, password);
        assertNotNull(connection);
    }
    
}
