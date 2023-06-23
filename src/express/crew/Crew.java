package express.crew;

import express.courier.Courier;
import express.manager.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//营业厅普通工作人员界面
public class Crew extends JFrame {

    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JTextField tf1;
    JTextField tf2;
    JButton bt1;    //快递员管理
    JButton bt2;    //确认指派快递员
    JButton bt3;
    JButton bt4;
    JButton back;

    String goodid;
    String pid;

    public static void main(String[] args) {
        new Crew();
    }

    public Crew(){
        this.setTitle("营业厅普通工作人员窗");
        this.setVisible(true);
        this.setSize(470, 300);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel1 = new JLabel("快递单号：");
        jLabel2 = new JLabel("快递员编号：");
        jLabel3 = new JLabel("快递员指派");
        tf1 = new JTextField();
        tf2 = new JTextField();
        bt1 = new JButton("快递员管理");
        bt2 = new JButton("确定指派");
        bt3 = new JButton("快件信息录入、查询");
        bt4 = new JButton("投诉信息回复");
        back = new JButton("⬅");

        //返回键监听，分情况
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (express.GroupLayout.account.startsWith("s")){
                    new express.GroupLayout().groupLayoutTest();
                    dispose();
                }else if (express.GroupLayout.account.startsWith("m")){
                    new Manager();
                    dispose();
                }
            }
        });

        //快递员管理监听
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PostmanManage();
                dispose();
            }
        });

        //快件指派监听
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goodid = tf1.getText();
                pid = tf2.getText();
                try {
                    //从输入的文本框里获取输入的数据，然后做对比
                    //'"+account+"'这里这个表示的是变量account

                    String  sql = "update express_info set pid = '"+pid+"' where goodid = '"+goodid+"'";
                    express.GroupLayout.statement = express.GroupLayout.conn.createStatement();
                    express.GroupLayout.statement.executeUpdate(sql);
                    String s = "指派成功！";
                    JOptionPane.showMessageDialog(null, s,
                            "通知", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println("错误！错误！");
                }
            }
        });

        //快件信息录入、查询监听(快递员界面)
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Courier();
                dispose();
            }
        });

        //投诉信息回复监听
        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Respond();
                dispose();
            }
        });

        // 为指定的 Container 创建 GroupLayout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        javax.swing.GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        //添加间隔
        hGroup.addGap(15);
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(bt1).addComponent(jLabel3)
                .addComponent(jLabel1).addComponent(bt2).addComponent(bt3).addComponent(bt4));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf1));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(jLabel2));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf2));
        hGroup.addGap(15);

        // 设置水平分组
        layout.setHorizontalGroup(hGroup);
        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        javax.swing.GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(15);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt1));
        vGroup.addGap(15);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel3));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel1).addComponent(tf1).addComponent(jLabel2).addComponent(tf2));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt2));
        vGroup.addGap(15);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt3));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt4));
        vGroup.addGap(20);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}