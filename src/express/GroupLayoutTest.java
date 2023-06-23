package express;

import express.courier.Courier;
import express.crew.Crew;
import express.manager.Manager;
import express.user.Register;
import express.user.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class GroupLayoutTest extends JFrame {
    private static final long serialVersionUID = -5159330521192113057L;

    public static void main(String[] args) {
        GroupLayoutTest g =  new GroupLayoutTest();
        g.groupLayoutTest();
    }

    //整个程序用Statement类就足够了，不需要PreparedStatement
    public static Connection conn = null;
    //本地数据库连接，jdbc:sqlserver:// 为固定格式；localhost 为数据库地址，本地数据库就用“localhost”
    //DatabaseName=Goods 表示连接的数据库名字；encrypt=true;trustServerCertificate=true 表示让JDBC驱动使用SSL加密并跳过证书链的验证
    public static String dbURL = "jdbc:sqlserver:// localhost;DatabaseName=kuaidi;encrypt=true;trustServerCertificate=true";
    public static Statement statement = null;

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JTextField tf;
    JPasswordField psf;
    JComboBox jcombo;

    JButton bt1;
    JButton bt2;
    //记录输入的账号和密码数据
    //如果有数据需要被共享给所有对象使用时，那么就可以使用static修饰。
    public static String account = null;
    String password = null;
    //记录下拉框中的值
    String id = null;

    public void groupLayoutTest() {
        this.setTitle("登录窗口");
        this.setVisible(true);
        this.setSize(450, 220);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("账号：");
        label2 = new JLabel("密码：");
        label3 = new JLabel("身份：");
        tf = new JTextField();
        psf = new JPasswordField();
        bt1 = new JButton("登陆");
        bt2 = new JButton("注册");
        String [] a={"用户","快递员","工作人员","系统管理员"};
        jcombo= new JComboBox(a);
        //与数据库建立连接
        try {
            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功！");
            //2.连接
            conn = DriverManager.getConnection(dbURL, "my_user", "user");
            System.out.println("连接数据库成功！");}
        catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("连接数据库失败");
        }

        //注册监听
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //新的注册界面
                new Register();
                dispose();
            }
        });
        //登录监听
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取到的账号字符串
                account = tf.getText();
                //获取到的密码字符串
                password = String.valueOf(psf.getPassword());
                //获取到的身份字符串
                id = jcombo.getSelectedItem().toString();

                try {
                    //从输入的文本框里获取输入的数据，然后做对比
                    //'"+account+"'这里这个表示的是变量account
                    String sql = "select * from "+id+" where id = '"+account+"'";
                    statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
                    /*
                    Statement 对象用于执行不带参数的简单 SQL 语句；
                    PreparedStatement 对象用于执行带或不带 IN参数的预编译 SQL 语句；
                    CallableStatement 对象用于执行对数据库已存储过程的调用。
                    方法 executeQuery 用于产生单个结果集的语句，例如 SELECT 语句。
                    方法 executeUpdate 用于执行 INSERT、UPDATE 或 DELETE 语句以及 SQLDDL（数据定义语言）语句，
                    * 例如 CREATE TABLE 和 DROP TABLE。INSERT、UPDATE 或 DELETE
                    * 语句的效果是修改表中零行或多行中的一列或多列executeUpdate
                    * 的返回值是一个整数，指示受影响的行数（即更新计数）。
                    * 对于CREATE TABLE 或 DROP TABLE 等不操作行的语句，executeUpdate 的返回值总为零。
                    方法 execute用于执行返回多个结果集、多个更新计数或二者组合的语句。因为多数程序员不会需要该高级功能，
                    * 所以本概述后面将在单独一节中对其进行介绍。`*/

                    ResultSet res = statement.executeQuery(sql);
                    /*
                    * 获取RedultSet对象获取的个数
                    * 判断查询数据库的ResultSet对象是否为空
                    * 若为空，则表明身份与账户不符*/
                    res.last();
                    int count = res.getRow();
                    if(count == 0){
                        String s = "账户与身份不匹配！";
                        JOptionPane.showMessageDialog(null, s,
                                "警告", JOptionPane.WARNING_MESSAGE);
                        new GroupLayoutTest().groupLayoutTest();
                        dispose();
                    }
                    res = statement.executeQuery(sql);
                    while (res.next()) {
                        String title = res.getString("pw");
                        title.trim();
                        System.out.println(title.equals(password) ? "登录成功" : "登陆失败");
                        if(title.equals(password)){
                            setVisible(false);
                            if (id.equals("用户")){
                                String s = "登录成功！";
                                JOptionPane.showMessageDialog(null, s,
                                        "通知", JOptionPane.PLAIN_MESSAGE);
                                new User();
                                dispose();
                            } else if(id.equals("快递员")){
                                String s = "登录成功！";
                                JOptionPane.showMessageDialog(null, s,
                                        "通知", JOptionPane.PLAIN_MESSAGE);
                                new Courier();
                                dispose();
                            } else if(id.equals("系统管理员")){
                                String s = "登录成功！";
                                JOptionPane.showMessageDialog(null, s,
                                        "通知", JOptionPane.PLAIN_MESSAGE);
                                new Manager();
                                dispose();
                            } else if (id.equals("工作人员")){
                                String s = "登录成功！";
                                JOptionPane.showMessageDialog(null, s,
                                        "通知", JOptionPane.PLAIN_MESSAGE);
                                new Crew();
                                dispose();
                            }
                        }else{
                            String s = "密码输入错误！请重新输入！";
                            JOptionPane.showMessageDialog(null, s,
                                    "警告", JOptionPane.WARNING_MESSAGE);
                            new GroupLayoutTest().groupLayoutTest();
                            dispose();
                        }
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println("错误错误！");
                }
            }
        });

        // 为指定的 Container 创建 GroupLayout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        GroupLayout.SequentialGroup hSeqGp02 = layout.createSequentialGroup();
        hSeqGp02.addGap(50).addComponent(bt1).addGap(130).addComponent(bt2);

        // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        // 添加间隔
        //这个间隔是每个水平连续组之间的间隔
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(label2).addComponent(label3));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(psf)
                .addComponent(tf).addComponent(jcombo).addGroup(hSeqGp02));
        hGroup.addGap(30);
        // 设置水平分组
        layout.setHorizontalGroup(hGroup);

        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(tf));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(psf));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label3).addComponent(jcombo));
        vGroup.addGap(5);
        GroupLayout.ParallelGroup vParalGroup03 = layout.createParallelGroup().addComponent(bt1).addComponent(bt2);
        vGroup.addGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(vParalGroup03));
        vGroup.addGap(20);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}




