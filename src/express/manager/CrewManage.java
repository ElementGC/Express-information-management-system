package express.manager;

import express.GroupLayoutTest;
import express.crew.Addpostman;
import express.crew.Postmanim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//工作人员管理界面
public class CrewManage extends JFrame {

    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;
    JTextField tf1;
    JTextField tf2;
    JButton bt1;
    JButton bt2;
    JButton bt3;
    JButton back;

    static String staffnum;

    public static void main(String[] args) {
        new CrewManage();
    }

    public CrewManage(){
        this.setTitle("工作人员管理窗");
        this.setVisible(true);
        this.setSize(550, 320);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel1 = new JLabel("删除工作人员");
        jLabel2 = new JLabel("编号：");
        jLabel3 = new JLabel("修改工作人员信息");
        jLabel4 = new JLabel("编号：");
        tf1 = new JTextField();
        tf2 = new JTextField();
        bt1 = new JButton("删除");
        bt2 = new JButton("修改");
        bt3 = new JButton("添加工作人员");
        back = new JButton("⬅");

        //返回键监听
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Manager();
                dispose();
            }
        });

        //删除按钮监听
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                staffnum = tf1.getText();
                try {
                    int res;
                    String sql = "delete from 工作人员 where staffnum = '"+ staffnum +"'";
                    GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                    res = GroupLayoutTest.statement.executeUpdate(sql);
                    if (res == 1){
                        String s = "工作人员删除成功！";
                        JOptionPane.showMessageDialog(null, s,
                                "通知", JOptionPane.PLAIN_MESSAGE);
                    }else {
                        String s = "请输入正确的工作人员工作号！";
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
                staffnum = tf2.getText();
                new Crewim();
                dispose();
            }
        });

        //添加按钮监听
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCrew();
                dispose();
            }
        });

        // 为指定的 Container 创建 GroupLayout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);

        // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

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
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
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
