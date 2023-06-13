package express.user;

import express.GroupLayoutTest;
import express.manager.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

//用户界面
public class User extends JFrame {

    JButton bt1;
    JButton bt2;
    JButton bt3;
    JButton bt4;
    JButton bt5;
    JButton back;
    String acount = GroupLayoutTest.account;

    public static void main(String[] args) {
        new User();
    }

    public User(){
        this.setTitle("用户窗");
        this.setVisible(true);
        this.setSize(450, 210);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bt1 = new JButton("查询快递信息");
        bt2 = new JButton("个人信息管理");
        bt3 = new JButton("寄件");
        bt4 = new JButton("投诉与建议");
        bt5 = new JButton("查看回复");
        back = new JButton("⬅");

        //返回监听，分情况
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GroupLayoutTest.account.startsWith("u")){
                    new GroupLayoutTest().groupLayoutTest();
                    dispose();
                }else if (GroupLayoutTest.account.startsWith("m")){
                    new Manager();
                    dispose();
                }
            }
        });

        //①快件查询监听
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = "select * from expressinformation where uid = '"+acount+"'";
                    GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                    String s = null;
                    ResultSet res = GroupLayoutTest.statement.executeQuery(sql);
                    int i = 1;
                    while (res.next()){
                        s += i +"：编号为" + res.getString("goodnum") + "的快递";
                        s += "由" + res.getString("pid") + "快递员派送，";
                        s += "当前在" + res.getString("expresslocation") + "，" + res.getString("expressstatus");
                        s += "\n";
                        i ++;
                    }
                    s = s.substring(4);
                    JOptionPane.showMessageDialog(null, s,
                            "快件信息", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //②个人信息管理监听
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Personalim();
                dispose();
            }
        });

        //③寄件监听
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Mail();
                dispose();
            }
        });

        //④投诉与建议监听
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Suggest();
                dispose();
            }
        });

        //⑤查看回复监听
        bt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sql = "select * from evaluation where uid = '"+acount+"'";
                    GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                    String s = null;
                    ResultSet res = GroupLayoutTest.statement.executeQuery(sql);
                    int i = 1;
                    while (res.next()){
                        s += i + "：对编号" + res.getString("goodnum") + "快递的回复：";
                        s += res.getString("respond");
                        s += "\n";
                        i ++;
                    }
                    s = s.substring(4);
                    JOptionPane.showMessageDialog(null, s,
                            "快件信息", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 为指定的 Container 创建 GroupLayout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        //添加间隔
        hGroup.addGap(20);
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(bt1).addComponent(bt2).addComponent(bt3));
        hGroup.addGap(20);
        hGroup.addGroup(layout.createParallelGroup().addComponent(bt4).addComponent(bt5));

        // 设置水平分组
        layout.setHorizontalGroup(hGroup);
        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(3);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(15);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt1).addComponent(bt4));
        vGroup.addGap(12);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt2).addComponent(bt5));
        vGroup.addGap(12);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt3));
        vGroup.addGap(20);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}