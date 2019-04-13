package base;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class Spring {

    @Autowired
    private BasicDataSource dataSource;

    @Test
    public void getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "select * from user where user_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 1);
        boolean res = ps.execute();
        Assert.assertTrue(res);
    }

    @Test
    public void getMany() throws SQLException{
        List<Connection> connections = new ArrayList<Connection>();
        for (int i = 0; i < 200; i++) {
            Connection connection = dataSource.getConnection();
            connections.add(connection);
            //if(i % 20 == 0){
                System.out.println(i);
            //}
        }
    }
}
