package express.crew;

import express.GroupLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//快递员管理界面
public class PostmanManage extends JFrame {

    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;
    JTextField tf1; //要删除的快递员编号框
    JTextField tf2; //要修改的快递员编号框
    JButton bt1;
    JButton bt2;
    JButton bt3;
    JButton back;
    //快递员编号
    static String postemp;

    public static void main(String[] args) {
        new PostmanManage();
    }

    public PostmanManage(){
        this.setTitle("快递员管理窗");
        this.setVisible(true);
        this.setSize(550, 320);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel1 = new JLabel("删除快递员");
        jLabel2 = new JLabel("编号：");
        jLabel3 = new JLabel("修改快递员信息");
        jLabel4 = new JLabel("编号：");
        tf1 = new JTextField();
        tf2 = new JTextField();
        bt1 = new JButton("删除");
        bt2 = new JButton("修改");
        bt3 = new JButton("添加快递员");
        back = new JButton("⬅");

        //返回键监听
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Crew();
                dispose();
            }
        });

        //删除按钮监听
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postemp = tf1.getText();
                try {
                    int res;
                    String sql = "delete from 快递员 where postemp = '"+postemp+"'";
                    express.GroupLayout.statement = GroupLayout.conn.createStatement();
                    res = express.GroupLayout.statement.executeUpdate(sql);
                    if (res == 1){
                        String s = "快递员删除成功！";
                        JOptionPane.showMessageDialog(null, s,
                                "通知", JOptionPane.PLAIN_MESSAGE);
                    }else {
                        String s = "请输入正确的快递员工作号！";
                        JOptionPane.showMessageDialog(null, s,
                                "通知", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println("错误！错误！");
                }
            }
        });

        //修改按钮监听
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postemp = tf2.getText();
                new Postmanim();
                dispose();
            }
        });

        //添加按钮监听
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Addpostman();
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
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(jLabel1)
                .addComponent(jLabel2).addComponent(bt1).addComponent(jLabel3).addComponent(jLabel4)
                .addComponent(bt2).addComponent(bt3));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf1).addComponent(tf2));
        hGroup.addGap(15);

        // 设置水平分组
        layout.setHorizontalGroup(hGroup);
        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        javax.swing.GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel1));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel2).addComponent(tf1));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt1));
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel3));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(jLabel4).addComponent(tf2));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt2));
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(bt3));
        vGroup.addGap(20);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}
