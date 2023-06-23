package express.user;

import express.GroupLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//用户注册界面
public class Register extends JFrame {

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JButton submit;

    JPasswordField psf;
    JPasswordField cpsf;
    JComboBox jComboBox;
    JTextField tf1;
    JTextField tf2;
    JTextField tf3;

    String password1 = null;
    String password2 = null;
    String sex = null;
    String address = null;
    String phoneNumber = null;
    String name = null;
    String id = null;

    public static void main(String[] args) {
        new Register();
    }

    public Register(){

        this.setTitle("注册窗口");
        this.setVisible(true);
        this.setSize(450, 330);
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
        submit = new JButton("提交");
        psf = new JPasswordField();
        cpsf = new JPasswordField();
        String [] a = {"男","女"};
        jComboBox = new JComboBox(a);
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();

        try {
            int res;
            String sql1 = "select id from 用户";
            GroupLayout.statement = GroupLayout.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
            ResultSet res1 = GroupLayout.statement.executeQuery(sql1);
            //获取RedultSet对象获取的个数
            res1.last();
            int count = res1.getRow();
            System.out.println(count);
            id = "u" + String.valueOf(++count);
        } catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("错误！错误！");
        }

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                password1 = String.valueOf(psf.getPassword());
                password2 = String.valueOf(cpsf.getPassword());
                sex = (String) jComboBox.getSelectedItem();
                address = tf1.getText();
                phoneNumber = tf2.getText();
                name = tf3.getText();

                try {
                    //判断两次密码是否相同
                    if( !password1.equals(password2)){
                        String s = "两次密码输入不一致，请确认后重新输入";
                        JOptionPane.showMessageDialog(null, s,"警告", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    String sql = "select id from yh";
                    sql = "INSERT INTO 用户(username,usersex,usertel,id,pw,loc) VALUES ('"+name+"','"+sex+"','"+phoneNumber+"','"+id+"','"+password1+"','"+address+"')";
                    GroupLayout.statement = express.GroupLayout.conn.createStatement();
                    GroupLayout.statement.executeUpdate(sql);
                    //弹出提示信息，申请到的账号是什么
                    String s = "注册成功！您的账号是:" + id + "请勿遗忘";
                    JOptionPane.showMessageDialog(null, s,
                            "通知", JOptionPane.PLAIN_MESSAGE);
                    //返回到登录窗口
                    new GroupLayout().groupLayoutTest();
                    //销毁此窗口，减少内存的消耗
                    dispose();
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
        hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(label2)
                .addComponent(label3).addComponent(label4).addComponent(label5).addComponent(label6));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(psf).addComponent(cpsf)
                .addComponent(jComboBox).addComponent(tf1).addComponent(tf2).addComponent(tf3).addComponent(submit));
        hGroup.addGap(5);
        // 设置水平分组
        layout.setHorizontalGroup(hGroup);

        // 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        javax.swing.GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(10);
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

