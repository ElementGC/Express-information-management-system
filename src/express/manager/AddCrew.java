package express.manager;

import express.crew.PostmanManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//工作人员添加界面
public class AddCrew extends JFrame {

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JButton submit;

    JPasswordField psf;
    JPasswordField cpsf;
    JComboBox jComboBox;
    JTextField tf1;
    JTextField tf2;

    String password1 = null;
    String password2 = null;
    String sex = null;
    String phoneNumber = null;
    String name = null;
    String staffnum = null;
    String id = null;

    public static void main(String[] args) {
        new AddCrew();
    }

    public AddCrew(){
        this.setTitle("工作人员添加窗口");
        this.setVisible(true);
        this.setSize(450, 330);
        this.setVisible(true);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("密码：");
        label2 = new JLabel("确认密码：");
        //下拉框
        label3 = new JLabel("性别：");
        label4 = new JLabel("电话号码：");
        label5 = new JLabel("姓名：");
        submit = new JButton("提交");
        psf = new JPasswordField();
        cpsf = new JPasswordField();
        String [] a = {"男","女"};
        jComboBox = new JComboBox(a);
        tf1 = new JTextField();
        tf2 = new JTextField();

        try {
            String sql = "select staffnum from 工作人员";
            express.GroupLayout.statement = express.GroupLayout.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
            ResultSet res = express.GroupLayout.statement.executeQuery(sql);
            //获取RedultSet对象获取的个数
            res.last();
            int count = res.getRow();
//            System.out.println(count);输出的是当前总个数
            id = "s" + String.valueOf(++count);
            count += 30000;
            staffnum = String.valueOf(count);
//            System.out.println(id);
        } catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("错误！错误！");
        }

        //若快递员编号重复
        try {
            String sql = "select * from 工作人员 where staffnum = '"+staffnum+"'";
            express.GroupLayout.statement = express.GroupLayout.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,        ResultSet.CONCUR_READ_ONLY);
            ResultSet res = express.GroupLayout.statement.executeQuery(sql);
            //获取RedultSet对象获取的个数
            res.last();
            int count = res.getRow();
            System.out.println(count);//出的是当前总个数
            if (count == 1){
                id = id.substring(1);
                int temp = Integer.parseInt(id);
                id = "p" + String.valueOf(++temp);
                temp = Integer.parseInt(staffnum);
                staffnum = String.valueOf(++temp);
            }
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
                phoneNumber = tf1.getText();
                name = tf2.getText();

                try {
                    //从输入的文本框里获取输入的数据，然后做对比
                    //'"+account+"'这里这个表示的是变量account
                    String sql = "select id from yh";
                    sql = "INSERT INTO 工作人员(staffnum,staffname,staffsex,stafftel,id,pw) VALUES ('"+staffnum+"','"+name+"','"+sex+"','"+phoneNumber+"','"+id+"','"+password1+"')";
                    express.GroupLayout.statement = express.GroupLayout.conn.createStatement();
                    express.GroupLayout.statement.executeUpdate(sql);
                    //弹出提示信息，申请到的账号是什么
                    String s = "注册成功！您的账号是:" + id + "；" + "工号是：" + staffnum + "请勿遗忘！";
                    JOptionPane.showMessageDialog(null, s,
                            "通知", JOptionPane.PLAIN_MESSAGE);
                    //返回到快递员管理窗口
                    new PostmanManage();
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
                .addComponent(label3).addComponent(label4).addComponent(label5));
        hGroup.addGap(5);
        hGroup.addGroup(layout.createParallelGroup().addComponent(psf).addComponent(cpsf)
                .addComponent(jComboBox).addComponent(tf1).addComponent(tf2).addComponent(submit));
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
        vGroup.addGroup(layout.createParallelGroup().addComponent(submit));
        vGroup.addGap(10);
        // 设置垂直组
        layout.setVerticalGroup(vGroup);
    }
}
