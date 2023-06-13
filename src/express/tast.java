package express;

import java.sql.*;
import java.sql.SQLException;


public class tast {
    private static final long serialVersionUID = -5159330521192113057L;

    //整个程序用Statement类就足够了，不需要PreparedStatement
    public static Connection conn = null;
    //本地数据库连接，jdbc:sqlserver:// 为固定格式；localhost 为数据库地址，本地数据库就用“localhost”
    //DatabaseName=Goods 表示连接的数据库名字；encrypt=true;trustServerCertificate=true 表示让JDBC驱动使用SSL加密并跳过证书链的验证
    public static String dbURL = "jdbc:sqlserver:// localhost;DatabaseName=Goods;encrypt=true;trustServerCertificate=true";
    public static Statement statement = null;

    public static void main(String[] args) throws SQLException {
        //与数据库建立连接
        try {
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
            //2.连接
            conn = DriverManager.getConnection(dbURL, "my_user", "user");
            System.out.println("连接数据库成功！");
        } catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("连接数据库失败");
        }
        Statement stmt = conn.createStatement();

        // 执行插入操作
        String sql1 = "insert into 厂家表 values ('C4','南京邮电大学出版社')";
        int count1 = stmt.executeUpdate(sql1);
        System.out.println("插入了" + count1 + "条数据");
        // 执行查询操作
        String sql4 = "select * from 厂家表";
        ResultSet rs = stmt.executeQuery(sql4);
        // 遍历结果集
        while (rs.next()) {
            String id = rs.getString("厂家编号");
            String name = rs.getString("厂家名称");
            System.out.println(id + "\t" + name + "\t" );
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}


