package base;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conn {
    private static BasicDataSource dataSource;
    @BeforeClass
    public static void init(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/apache_dbcp");
        ds.setUsername("root");

        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);

        dataSource = ds;
    }

    @Test
    public void getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "select * from user where user_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 1);
        boolean res = ps.execute();
        Assert.assertTrue(res);
    }
}
