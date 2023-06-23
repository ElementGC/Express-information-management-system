package express.crew;

import express.GroupLayout;
import express.manager.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//回复界面
public class Respond extends JFrame{

    JTextField tf1; //快递号
    JTextArea tf2;  //回复内容
    JTextArea tf3;  //投诉内容
    JLabel jLabel1; //输入快递单号
    JLabel jLabel2; //输入回复内容
    JLabel jLabel3; //显示投诉内容
    JButton bt1;    //提交回复信息
    JButton bt2;    //查询快递投诉信息
    JButton back;

    String goodid;
    String respond;
    String suggest;

    public static void main(String[] args) {
        new Respond();
    }

    public Respond() {
        this.setTitle("回复窗");
        this.setVisible(true);
        this.setSize(600, 450);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bt1 = new JButton("回复");
        bt2 = new JButton("查询");
        back = new JButton("⬅");
        jLabel1 = new JLabel("请输入要回复的快递单号：");
        jLabel2 = new JLabel("请输入回复内容：");
        jLabel3 = new JLabel("投诉信息");
        tf1 = new JTextField();
        tf2 = new JTextArea(10,60);
        tf3 = new JTextArea(10,60);
        tf2.setEditable(false);
        tf3.setEditable(false);


        //返回键监听
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (express.GroupLayout.account.equals("m1")){
                    new Manager();
                }else{
                    new Crew();
                }
                dispose();
            }
        });
        //回复提交监听
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodid = tf1.getText();
                respond = tf2.getText();
                try {
                    int res;
                    String sql = "update evaluation set respond = '"+respond+"' where goodid = '"+goodid+"'";
                    GroupLayout.statement = GroupLayout.conn.createStatement();
                    res = GroupLayout.statement.executeUpdate(sql);
                    if (res == 1){
                        String s = "回复成功！";
                        JOptionPane.showMessageDialog(null, s,
                                "通知", JOptionPane.PLAIN_MESSAGE);
                        new Crew();
                        dispose();
                    }else {
                        String s = "错误！错误！";
                        JOptionPane.showMessageDialog(null, s,
                                "通知", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println("错误！错误！");
                }
            }
        });
        //查询监听
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodid = tf1.getText();
                try {
                    String sql = "select * from evaluation where goodid = '"+goodid+"'";
                    GroupLayout.statement = GroupLayout.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
                    ResultSet res = GroupLayout.statement.executeQuery(sql);
                    res.last();
                    int count = res.getRow();
                    if (count == 0){
                        suggest = "暂无投诉";
                        sql = "insert into evaluation (evaluation,goodid) values('"+suggest+"','"+goodid+"')";
                        GroupLayout.statement = GroupLayout.conn.createStatement();
                        GroupLayout.statement.executeUpdate(sql);
                    }
                    sql = "select * from evaluation where goodid = '"+goodid+"'";
                    GroupLayout.statement = express.GroupLayout.conn.createStatement();
                    res = GroupLayout.statement.executeQuery(sql);
                    while (res.next()) {
                        suggest = res.getString("evaluation").trim();
                    }
                    tf3.setText(suggest);
                    tf2.setEditable(true);
                    JOptionPane.showMessageDialog(null, "查询投诉信息成功","通知", JOptionPane.PLAIN_MESSAGE);
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
        hGroup.addGap(15);
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(jLabel1).addComponent(jLabel3).addComponent(jLabel2).addComponent(bt2));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf1).addComponent(tf3).addComponent(tf2).addComponent(bt1));

        // 设置水平分组
        layout.setHorizontalGroup(hGroup);
        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        javax.swing.GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel1).addComponent(tf1));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel3).addComponent(tf3));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel2).addComponent(tf2));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt2).addComponent(bt1));
        vGroup.addGap(20);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}
