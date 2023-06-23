package express.manager;

import express.GroupLayoutTest;
import express.crew.PostmanManage;
import express.crew.Postmanim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//工作人员信息管理
public class Crewim extends JFrame {

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
    String staffnum = CrewManage.staffnum;
    String phoneNumber = null;
    String id;

    public static void main(String[] args) {
        new Crewim();
    }

    public Crewim(){
        this.setTitle("工作人员信息管理窗口");
        this.setVisible(true);
        this.setSize(450, 320);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("密码：");
        label2 = new JLabel("确认密码：");
        //下拉框
        label3 = new JLabel("性别：");
        label4 = new JLabel("工作人员编号：");
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
                new CrewManage();
                dispose();
            }
        });

        //非法输入
        int count  = 0;
        try {
            String sql = "select staffnum from 工作人员";
            GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
            ResultSet res = GroupLayoutTest.statement.executeQuery(sql);
            //获取RedultSet对象获取的个数
            res.last();
            count = res.getRow();
//            System.out.println(count);输出的是当前总个数
            count += 30000;
        } catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("错误！错误！");
        }

        //没有输入时
        if ("".compareTo(staffnum) == 0){
            JOptionPane.showMessageDialog(null, "工作人员工号为空！请重新输入！",
                    "警告", JOptionPane.WARNING_MESSAGE);
            new CrewManage();
            dispose();
        }else {
            int temp = Integer.parseInt(staffnum);
            System.out.println(temp);
            //输入的工号不存在时
            if (temp < 1 || temp > count || temp == 0){
                JOptionPane.showMessageDialog(null, "非法工作人工号！请重新输入！",
                        "警告", JOptionPane.WARNING_MESSAGE);
                new CrewManage();
                dispose();
            }
        }

        try {

//            System.out.println(account);
            sql = "select * from 工作人员 where staffnum = '"+staffnum+"'";
            GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
            ResultSet res = GroupLayoutTest.statement.executeQuery(sql);
            while (res.next()) {
                name = res.getString("staffname");
                sex = res.getString("staffsex");
                phoneNumber = res.getString("stafftel");
                staffnum = res.getString("staffnum");
            }
        }
        catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("错误！错误！");
        }

        tf3.setText(name);
        jComboBox.setSelectedItem(sex);
        tf1.setText(staffnum);
        tf2.setText(phoneNumber);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    staffnum = tf1.getText();
                    phoneNumber = tf2.getText();
                    name = tf3.getText();
                    sex = (String) jComboBox.getSelectedItem();
                    password1 = String.valueOf(psf.getPassword());
                    password2 = String.valueOf(cpsf.getPassword());
                    if(password1.equals(password2)){
                        //从输入的文本框里获取输入的数据，然后做对比
                        //'"+account+"'这里这个表示的是变量account
                        int res;
                        sql = "update 工作人员 set staffname = '"+name+"',staffsex = '"+sex+"',stafftel = '"+phoneNumber+"',pw = '"+password1+"',staffnum = '"+staffnum+"' where staffnum = '"+staffnum+"'";
                        GroupLayoutTest.statement = GroupLayoutTest.conn.createStatement();
                        res = GroupLayoutTest.statement.executeUpdate(sql);
                        if (res == 1){
                            String s = "修改成功！";
                            JOptionPane.showMessageDialog(null, s,
                                    "通知", JOptionPane.PLAIN_MESSAGE);
                        }
                        //返回到快递员管理窗口
                        new PostmanManage();
                        //销毁此窗口，减少内存的消耗
                        dispose();
                    }else {
                        String s = "两次密码输入不同，请重新输入！";
                        JOptionPane.showMessageDialog(null, s,
                                "警告", JOptionPane.WARNING_MESSAGE);
                        new Postmanim();
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
