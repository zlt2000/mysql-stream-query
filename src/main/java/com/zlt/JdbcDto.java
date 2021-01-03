package com.zlt;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zlt
 * @date 2020/12/28
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Repository
public class JdbcDto {
    private final JdbcTemplate jdbcTemplate;

    public JdbcDto(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int execute(String sql, boolean isStreamQuery) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            //获取数据库连接
            conn = getConnection();
            if (isStreamQuery) {
                //设置流式查询参数
                stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                stmt.setFetchSize(Integer.MIN_VALUE);
            } else {
                //普通查询
                stmt = conn.prepareStatement(sql);
            }

            //执行查询获取结果
            rs = stmt.executeQuery();
            //遍历结果
            while(rs.next()){
                System.out.println(rs.getString(1));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt, rs, conn);
        }
        return count;
    }

    private void close(PreparedStatement stmt, ResultSet rs, Connection conn) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    private Connection getConnection() throws SQLException {
        return jdbcTemplate.getDataSource().getConnection();
    }
}
