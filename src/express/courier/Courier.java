package express.courier;

import express.GroupLayoutTest;
import express.crew.Crew;
import express.manager.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//快递员页面
//快件信息录入、快递查询页面
public class Courier extends JFrame{

    //快件信息录入
    //快件信息查询
    JButton bt1;
    JButton bt2;
    JButton back;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JTextField tf1; //快递位置文本框
    JTextField tf2; //快递状态文本框
    JTextField tf3; //快递单号文本框

    String loc = null;
    String state = null;
    String enquiry = null;

    public static void main(String[] args) {
        new Courier();
    }

    public Courier(){

        this.setTitle("快递员");
        this.setVisible(true);
        this.setSize(700, 270);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bt1 = new JButton("录入");
        bt2 = new JButton("查询");
        back = new JButton("⬅");
        label1 = new JLabel("快递信息录入");
        label6 = new JLabel("(在输入查询处填入单号才可录入)");
        label2 = new JLabel("快递当前位置：");
        label3 = new JLabel("状态：");
        label4 = new JLabel("快件信息查询");
        label5 = new JLabel("输入快递单号：");
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();

        //返回键监听，到登录窗口
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GroupLayoutTest.account.startsWith("p")){
                    new GroupLayoutTest().groupLayoutTest();
                    dispose();
                }else if (GroupLayoutTest.account.startsWith("s")){
                    new Crew();
                    dispose();
                }else if (GroupLayoutTest.account.startsWith("m")){
                    new Manager();
                    dispose();
                }
            }
        });

        //录入监听
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loc = tf1.getText();
                state = tf2.getText();
                enquiry = tf3.getText();
                //判断字符串是否以1开头，若是，则是快递单号，若不是，则是
                try {
                    //从输入的文本框里获取输入的数据，然后做对比
                    //'"+account+"'这里这个表示的是变量account
                    String sql = "update express_info set expressposition = '" + loc + "',expressstatus = '" + state + "' where goodid = '"+enquiry+"'";
                    GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                    int res = GroupLayoutTest.statement.executeUpdate(sql);
                    if (res == 1){
                        JOptionPane.showMessageDialog(null, "录入成功！",
                                "通知", JOptionPane.PLAIN_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null, "录入失败！",
                                "通知", JOptionPane.PLAIN_MESSAGE);
                        new Courier();
                    }
                    //根据不同的身份返回到不同窗口
                    if (GroupLayoutTest.account.startsWith("p")){
                        new Courier();
                    }else if (GroupLayoutTest.account.startsWith("s")){
                        new Crew();
                    }else if (GroupLayoutTest.account.startsWith("m")){
                        new Manager();
                    }
                    dispose();
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println("密码输入错误！请从新输入");
                }
            }
        });

        //查询监听
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enquiry = tf3.getText();
                try {
                    //从输入的文本框里获取输入的数据，然后做对比
                    String sql = "select * from express_info where goodid = '"+enquiry+"'";
                    GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                    ResultSet res = GroupLayoutTest.statement.executeQuery(sql);
                    String s = null;
                    while (res.next()){
                        s +="编号为" + res.getString("goodid") + "的快递";
                        s += "由" + res.getString("pid") + "快递员派送，";
                        s += "当前在" + res.getString("expressposition") + "，" + res.getString("expressstatus");
                    }
                    s = s.substring(4);
                    JOptionPane.showMessageDialog(null, s,
                            "快件信息", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println("密码输入错误！请从新输入");
                }
            }
        });

        // 为指定的 Container 创建 GroupLayout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        //添加间隔
        hGroup.addGap(15);
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(label1).
                addComponent(label2).addComponent(bt1).addComponent(label4).addComponent(label5)
                .addComponent(bt2));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(label6).addComponent(tf1).addComponent(tf3));
        hGroup.addGap(10);
        hGroup.addGroup(layout.createParallelGroup().addComponent(label3));
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf2));
        hGroup.addGap(15);

        // 设置水平分组
        layout.setHorizontalGroup(hGroup);
        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(0);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(15);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(label6));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(tf1).addComponent(label3).addComponent(tf2));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt1));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label4));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label5).addComponent(tf3));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt2));
        vGroup.addGap(20);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }

}












