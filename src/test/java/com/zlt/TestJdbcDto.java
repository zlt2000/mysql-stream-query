package com.zlt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author zlt
 * @date 2020/12/28
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJdbcDto {
    @Resource
    private JdbcDto jdbcDto;

    /**
     * 大数据量普通查询
     */
    @Test
    public void testCommonBigData() throws SQLException {
        String sql = "select * from my_test";
        testExecute(sql, false);
    }
    /**
     * 大数据量流式查询
     */
    @Test
    public void testStreamBigData() throws SQLException {
        String sql = "select * from my_test";
        testExecute(sql, true);
    }
    /**
     * 小数据量普通查询
     */
    @Test
    public void testCommonSmallData() throws SQLException {
        String sql = "select * from my_test limit 100000, 10";
        testExecute(sql, false);
    }
    /**
     * 大数据量流式查询
     */
    @Test
    public void testStreamSmallData() throws SQLException {
        String sql = "select * from my_test limit 100000, 10";
        testExecute(sql, true);
    }

    private void testExecute(String sql, boolean isStreamQuery) throws SQLException {
        long startTime = System.currentTimeMillis();
        //查询并处理逻辑
        int count = jdbcDto.execute(sql, true);

        long endTime = System.currentTimeMillis();
        System.out.println("总记录条数为：" + count);
        System.out.println("消耗时间为：" + (endTime - startTime));
    }
}
