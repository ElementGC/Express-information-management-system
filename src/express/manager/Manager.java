package express.manager;

import express.GroupLayout;
import express.courier.Courier;
import express.crew.Crew;
import express.crew.Respond;
import express.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//营业厅系统管理员界面
public class Manager extends JFrame {

    JButton bt1;
    JButton bt2;
    JButton bt3;
    JButton bt4;
    JButton bt5;
    JButton bt6;
    JButton back;

    public static void main(String[] args) {
        new Manager();
    }

    public Manager(){
        this.setTitle("营业厅系统管理员窗");
        this.setVisible(true);
        this.setSize(310, 200);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bt1 = new JButton("工作人员分功能");
        bt2 = new JButton("快递员分功能");
        bt3 = new JButton("普通用户分功能");
        bt4 = new JButton("投诉信息回复");
        bt5 = new JButton("工作人员管理");
        bt6 = new JButton("快件总数查询");
        back = new JButton("⬅");

        //返回键监听
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GroupLayout().groupLayoutTest();
                dispose();
            }
        });

        //工作人员分功能
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Crew();
                dispose();
            }
        });

        //快递员分功能
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Courier();
                dispose();
            }
        });

        //用户分功能
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new User();
                dispose();
            }
        });

        //投诉与建议监听
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Respond();
                dispose();
            }
        });

        //工作人员管理
        bt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CrewManage();
                dispose();
            }
        });

        //快件总数查询
        bt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //从输入的文本框里获取输入的数据，然后做对比
                    //'"+account+"'这里这个表示的是变量account
                    String  sql = "select goodid from express_info";
                    GroupLayout.statement = GroupLayout.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
                    ResultSet res = GroupLayout.statement.executeQuery(sql);
                    res.last();
                    int num = res.getRow();
                    String s ="快递总件数为：" + String.valueOf(num);
                    JOptionPane.showMessageDialog(null, s,
                            "通知", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println("错误！错误！");
                }
            }
        });

        // 为指定的 Container 创建 GroupLayout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        javax.swing.GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        //添加间隔
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(bt1).
                addComponent(bt2).addComponent(bt3));
        hGroup.addGap(15);
        hGroup.addGroup(layout.createParallelGroup().addComponent(bt4).
                addComponent(bt5).addComponent(bt6));
        hGroup.addGap(15);

        // 设置水平分组
        layout.setHorizontalGroup(hGroup);
        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        javax.swing.GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(15);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt1).addComponent(bt4));
        vGroup.addGap(9);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt2).addComponent(bt5));
        vGroup.addGap(9);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt3).addComponent(bt6));
        vGroup.addGap(20);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}