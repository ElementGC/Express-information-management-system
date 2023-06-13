package express.user;

import express.GroupLayoutTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//个人信息管理
public class Personalim extends JFrame {

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JPasswordField psf;
    JPasswordField cpsf;
    JComboBox jComboBox;
    JTextField tf1;
    JTextField tf2;
    JTextField tf3;
    JButton submit;
    JButton back;

    String sql = null;
    String name = null;
    String password1 = null;
    String password2 = null;
    String sex = null;
    String address = null;
    String phoneNumber = null;
    String account = GroupLayoutTest.account;

    public static void main(String[] args) {
        new Personalim();
    }

    public Personalim(){
        this.setTitle("个人信息管理窗口");
        this.setVisible(true);
        this.setSize(450, 320);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("密码：");
        label2 = new JLabel("确认密码：");
        //下拉框
        label3 = new JLabel("性别：");
        label4 = new JLabel("住址：");
        label5 = new JLabel("电话号码：");
        label6 = new JLabel("姓名：");
        submit = new JButton("确认修改");
        back = new JButton("⬅");
        psf = new JPasswordField();
        cpsf = new JPasswordField();
        String [] a = {"男","女"};
        jComboBox = new JComboBox(a);
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();

        //返回监听
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new User();
                dispose();
            }
        });

        try {

//            System.out.println(account);
            sql = "select * from 用户 where id = '"+account+"'";
            GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
            ResultSet res = GroupLayoutTest.statement.executeQuery(sql);
            while (res.next()) {
                name = res.getString(1);
                sex = res.getString(2);
                phoneNumber = res.getString(3);
                address = res.getString(6);
            }
        }
        catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("错误！错误！");
        }

        tf3.setText(name);
        jComboBox.setSelectedItem(sex);
        tf1.setText(address);
        tf2.setText(phoneNumber);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    address = tf1.getText();
                    phoneNumber = tf2.getText();
                    name = tf3.getText();
                    sex = (String) jComboBox.getSelectedItem();
                    password1 = String.valueOf(psf.getPassword());
                    password2 = String.valueOf(cpsf.getPassword());
                    if(password1.equals(password2)){
                        //从输入的文本框里获取输入的数据，然后做对比
                        //'"+account+"'这里这个表示的是变量account
                        int res;
                        sql = "update 用户 set username = '"+name+"',usersex = '"+sex+"',usertel = '"+phoneNumber+"',pw = '"+password1+"',loc = '"+address+"' where id = '"+account+"'";
                        GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                        res = GroupLayoutTest.statement.executeUpdate(sql);
                        if (res == 1){
                            String s = "修改成功！";
                            JOptionPane.showMessageDialog(null, s,
                                    "通知", JOptionPane.PLAIN_MESSAGE);
                        }
                        //返回到用户窗口
                        new User();
                        //销毁此窗口，减少内存的消耗
                        dispose();
                    }else {
                        String s = "两次密码输入不同，请重新输入！";
                        JOptionPane.showMessageDialog(null, s,
                                "警告", JOptionPane.WARNING_MESSAGE);
                        new Personalim();
                        dispose();
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
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(label1).addComponent(label2)
                .addComponent(label3).addComponent(label4).addComponent(label5).addComponent(label6));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(psf).addComponent(cpsf)
                .addComponent(jComboBox).addComponent(tf1).addComponent(tf2).addComponent(tf3).addComponent(submit));
        hGroup.addGap(5);
        // 设置水平分组
        layout.setHorizontalGroup(hGroup);

        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
        vGroup.addGroup(layout.createParallelGroup().addComponent(back));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(psf));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(cpsf));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label3).addComponent(jComboBox));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label4).addComponent(tf1));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label5).addComponent(tf2));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label6).addComponent(tf3));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(submit));
        vGroup.addGap(10);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}





