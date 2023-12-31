package express.user;

import express.GroupLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//投诉与建议界面
public class Suggest extends JFrame {

    JLabel label1;  //快递单号标签
    JLabel label2;  //快递投诉标签
    JTextField tf1; //快递单号文本
    JTextArea tf2;  //快递投诉文本
    JButton submit; //提交按键
    JButton query;
    JButton back;

    String suggest;
    String pid;
    String goodid;
    String account = express.GroupLayout.account;
    String aaccount;

    public static void main(String[] args) {
        new Suggest();
    }

    public Suggest(){
        this.setTitle("投诉与建议窗口");
        this.setVisible(true);
        this.setSize(450, 290);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("快递单号：");
        label2 = new JLabel("投诉内容：");
        tf1 = new JTextField();
        tf2 = new JTextArea(300,5);
        tf2.setEditable(false);
        tf2.setText("请输入快递单号进行并投诉查询");
        submit = new JButton("提交投诉");
        query = new JButton("查询投诉");
        back =new JButton("⬅");

        //返回键监听
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new User();
                dispose();
            }
        });

        //查询投诉监听
        query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodid = tf1.getText();
                try {
                    String sql = "select * from express_info where goodid = '"+goodid+"'";
                    express.GroupLayout.statement = express.GroupLayout.conn.createStatement();
                    ResultSet res = express.GroupLayout.statement.executeQuery(sql);
                    while(res.next()){
                        pid = res.getString("pid");
                        aaccount = res.getString("uid").trim();
                    }
                    sql = "select * from evaluation where goodid = '"+goodid+"'";
                    express.GroupLayout.statement = express.GroupLayout.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
                    res = express.GroupLayout.statement.executeQuery(sql);
                    res.last();
                    int count = res.getRow();
//                    System.out.println(count);
                    if (account.equals(aaccount)){
                        if (count == 0){
                            suggest = "暂无投诉";
                            sql = "insert into evaluation (pid,uid,goodid,evaluation,respond) values('"+pid+"','"+account+"','"+goodid+"','"+suggest+"','暂无回复')";
                            express.GroupLayout.statement = express.GroupLayout.conn.createStatement();
                            express.GroupLayout.statement.executeUpdate(sql);
                        }
                        sql = "select * from evaluation where goodid = '"+goodid+"'";
                        express.GroupLayout.statement = express.GroupLayout.conn.createStatement();
                        res = express.GroupLayout.statement.executeQuery(sql);
                        while (res.next()) {
                            suggest = res.getString("evaluation").trim();
                        }
                        tf2.setText(suggest);
                        tf2.setEditable(true);
                        JOptionPane.showMessageDialog(null, "查询投诉信息成功","通知", JOptionPane.PLAIN_MESSAGE);

                    }else {
                        JOptionPane.showMessageDialog(null, "非法投诉！当前投诉快递不属于你",
                                "警告", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println("错误！错误！");
                }

            }
        });

        //提交投诉监听
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodid = tf1.getText();
                suggest = tf2.getText();
                try {
                    String sql = "select * from express_info where goodid = '"+goodid+"'";
                    express.GroupLayout.statement = express.GroupLayout.conn.createStatement();
                    ResultSet res = express.GroupLayout.statement.executeQuery(sql);
                    while(res.next()){
                        pid = res.getString("pid");
                        aaccount = res.getString("uid").trim();
                    }
                    sql = "select * from evaluation where goodid = '"+goodid+"'";
                    GroupLayout.statement = express.GroupLayout.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
                    res = express.GroupLayout.statement.executeQuery(sql);
                    res.last();
                    int count = res.getRow();
//                    System.out.println(count);
                    if (account.equals(aaccount)){
                        sql = "update evaluation set evaluation = '"+suggest+"' where goodid = '"+goodid+"'";
                        express.GroupLayout.statement = express.GroupLayout.conn.createStatement();
                        express.GroupLayout.statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null, "投诉成功！感谢您的反馈，我们将做出改进！",
                                "通知", JOptionPane.PLAIN_MESSAGE);
                        new User();
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "非法投诉！当前投诉快递不属于你",
                                "警告", JOptionPane.WARNING_MESSAGE);
                    }
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
        // 添加间隔
        //这个间隔是每个水平连续组之间的间隔
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(label1).addComponent(label2).addComponent(query));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf1).addComponent(tf2).addComponent(submit));
        hGroup.addGap(5);
        // 设置水平分组
        layout.setHorizontalGroup(hGroup);

        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        javax.swing.GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(tf1));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(tf2));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(query).addComponent(submit));
        vGroup.addGap(10);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}













