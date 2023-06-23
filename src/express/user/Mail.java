package express.user;

import express.GroupLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//寄件
public class Mail extends JFrame{

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JTextField tf1;
    JTextField tf2;
    JTextField tf3;
    JTextField tf4;
    JTextField tf5;
    JTextField tf6;
    JButton submit;
    JButton back;

    String account = GroupLayout.account;
    String names = null;
    String namer = null;
    String adds = null;
    String addr = null;
    String phoneNumber = null;
    String phoneNumbes = null;
    //快递编号
    String goodid;
    //快递员编号
    String pid;

    public static void main(String[] args) {
        new Mail();
    }

    public Mail(){
        this.setTitle("寄件界面");
        this.setVisible(true);
        this.setSize(500, 330);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("收件人姓名：");
        label2 = new JLabel("寄件人姓名：");
        label3 = new JLabel("收件人住址：");
        label4 = new JLabel("寄件人住址：");
        label5 = new JLabel("收件人电话号码：");
        label6 = new JLabel("寄件人电话号码：");
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        submit = new JButton("提交");
        back = new JButton("⬅");

        //自动生成快递单号
        try {
            String sql1 = "select goodid from express_info";
            express.GroupLayout.statement = GroupLayout.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet res1 = express.GroupLayout.statement.executeQuery(sql1);
            //获取RedultSet对象获取的个数
            res1.last();
            int count = res1.getRow();
            count += 10000;
            goodid = String.valueOf(++count);
        } catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("错误！错误！");
        }

        /*//随机分配快递员
        try {
            String sql2 = "select postemp from 快递员";
            GroupLayout.statement = GroupLayout.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
            ResultSet res2 = GroupLayout.statement.executeQuery(sql2);
            //获取RedultSet对象获取的个数
            res2.last();
            int num = res2.getRow();
//            System.out.println(num);实际快递员个数
            Random r = new Random();
            int j = r.nextInt(num) + 1;
            pid = "p" + String.valueOf(j);
        } catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("错误！错误！");
        }*/

        //返回监听
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

                namer = tf1.getText();
                names = tf2.getText();
                addr = tf3.getText();
                adds = tf4.getText();
                phoneNumber = tf5.getText();
                phoneNumbes = tf6.getText();

                try {
                    //从输入的文本框里获取输入的数据，然后做对比
                    //'"+account+"'这里这个表示的是变量account
                    String sql = "insert into express_info(goodid,expressstatus,expressposition,receiver,receivertel,receiverloc,sender,sendertel,senderloc,pid,uid)values('"+ goodid +"','待发货','"+adds+"','"+namer+"','"+phoneNumber+"','"+addr+"','"+names+"','"+phoneNumbes+"','"+adds+"','null','"+account+"')";
                    GroupLayout.statement = express.GroupLayout.conn.createStatement();
                    int res = express.GroupLayout.statement.executeUpdate(sql);
//                    System.out.println(res);
                    String s = "寄件成功！快递单号为：" + goodid;
                    JOptionPane.showMessageDialog(null, s,
                            "通知", JOptionPane.PLAIN_MESSAGE);
                    //返回到用户窗口
                    new User();
                    //销毁此窗口，减少内存的消耗
                    dispose();
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println("信息错误");
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
        hGroup.addGroup(layout.createParallelGroup().addComponent(back).addComponent(label1).addComponent(label2)
                .addComponent(label3).addComponent(label4).addComponent(label5).addComponent(label6));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(tf1).addComponent(tf2)
                .addComponent(tf3).addComponent(tf4).addComponent(tf5).addComponent(tf6).addComponent(submit));
        hGroup.addGap(15);
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
        vGroup.addGroup(layout.createParallelGroup().addComponent(label3).addComponent(tf3));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label4).addComponent(tf4));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label5).addComponent(tf5));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(label6).addComponent(tf6));
        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup().addComponent(submit));
        vGroup.addGap(10);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}



