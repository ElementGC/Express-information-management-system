package express.user;

import express.GroupLayoutTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//投诉与建议界面
public class Suggest extends JFrame {

    JLabel label1;
    JLabel label2;
    JTextField tf1;
    JTextArea tf2;
    JButton submit;
    JButton back;

    String suggest;
    String uid;
    String pid;
    String goodnum;
    String account = GroupLayoutTest.account;
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

        label1 = new JLabel("请输入快递单号：");
        label2 = new JLabel("请输入你想投诉的内容：");
        tf1 = new JTextField();
        tf2 = new JTextArea(300,5);
        submit = new JButton("新建投诉");
        back =new JButton("⬅");

        //返回键监听
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new User();
                dispose();
            }
        });

        //提交监听
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodnum = tf1.getText();
                suggest = tf2.getText();
                try {
                    String sql = "select * from expressinformation where goodnum = '"+goodnum+"'";
                    GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                    ResultSet res = GroupLayoutTest.statement.executeQuery(sql);
                    while(res.next()){
                        uid = res.getString("uid");
                        pid = res.getString("pid");
                        aaccount = res.getString("uid");
                    }
                    sql = "select * from evaluation where goodnum = '"+goodnum+"'";
                    GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
                    res = GroupLayoutTest.statement.executeQuery(sql);
                    res.last();
                    int count = res.getRow();
//                    System.out.println(count);
                    if (account.equals(aaccount)){
                        if (count == 0){
                            sql = "insert into evaluation (pid,uid,goodnum,evaluation,respond) values('"+pid+"','"+uid+"','"+goodnum+"','"+suggest+"','暂无回复')";
                            GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                            GroupLayoutTest.statement.executeUpdate(sql);
                            JOptionPane.showMessageDialog(null, "投诉成功！感谢您的反馈，我们将做出改进！",
                                    "通知", JOptionPane.PLAIN_MESSAGE);
                            new User();
                            dispose();
                        }else if (count != 0){
                            sql = "update evaluation set evaluation = '"+suggest+"' where goodnum = '"+goodnum+"'";
                            GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                            GroupLayoutTest.statement.executeUpdate(sql);
                            JOptionPane.showMessageDialog(null, "修改投诉成功！感谢您的反馈，我们将做出改进！",
                                    "通知", JOptionPane.PLAIN_MESSAGE);
                            new User();
                            dispose();
                        }
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
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        // 添加间隔
        //这个间隔是每个水平连续组之间的间隔
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(label1).addComponent(label2));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf1).addComponent(tf2).addComponent(submit));
        hGroup.addGap(5);
        // 设置水平分组
        layout.setHorizontalGroup(hGroup);

        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(tf1));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(tf2));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(submit));
        vGroup.addGap(10);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}













