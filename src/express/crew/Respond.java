package express.crew;

import express.GroupLayoutTest;
import express.manager.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//回复界面
public class Respond extends JFrame{

    JTextField tf1;//快递号
    JTextArea tf2;//回复内容
    JLabel jLabel1;
    JLabel jLabel2;
    JButton bt1;
    JButton back;

    String goodnum;
    String respond;

    public static void main(String[] args) {
        new Respond();
    }

    public Respond() {
        this.setTitle("回复窗");
        this.setVisible(true);
        this.setSize(450, 300);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bt1 = new JButton("回复");
        back = new JButton("⬅");
        jLabel1 = new JLabel("请输入要回复的快递单号：");
        jLabel2 = new JLabel("请输入回复内容：");
        tf1 = new JTextField();
        tf2 = new JTextArea(10,60);

        //返回键监听
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GroupLayoutTest.account.equals("m1")){
                    new Manager();
                }else{
                    new Crew();
                }
                dispose();
            }
        });

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodnum = tf1.getText();
                respond = tf2.getText();
                try {
                    int res;
                    String sql = "update evaluation set respond = '"+respond+"' where goodnum = '"+goodnum+"'";
                    GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                    res = GroupLayoutTest.statement.executeUpdate(sql);
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

        // 为指定的 Container 创建 GroupLayout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        //添加间隔
        hGroup.addGap(15);
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(jLabel1).
                addComponent(jLabel2).addComponent(bt1));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf1).addComponent(tf2));

        // 设置水平分组
        layout.setHorizontalGroup(hGroup);
        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel1).addComponent(tf1));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel2).addComponent(tf2));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt1));
        vGroup.addGap(20);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}
