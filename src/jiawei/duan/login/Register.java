/*
 * 本类用于实现用户注册
 * 作者：段佳伟
 * 时间：2017-06
 * 
 */
package jiawei.duan.login;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import jiawei.duan.sql.MysqlConnecter;

public class Register {
	MysqlConnecter mc = new MysqlConnecter();   //数据库操作
	JFrame regFrame = new JFrame("用户注册");
	private JTextField textField;
	private final JLabel lblNewLabel = new JLabel("密码");
	private final JLabel lblNewLabel_1 = new JLabel("确认密码");
	private final JLabel lblNewLabel_2 = new JLabel("性别");
	private final JPasswordField textField_1 = new JPasswordField();
	private final JPasswordField  textField_2 = new JPasswordField();
	private final JRadioButton radioButton_1 = new JRadioButton("女");
	private final JRadioButton radioButton = new JRadioButton("男",true);
	private final JComboBox comboBox = new JComboBox();
	private final JLabel label_1 = new JLabel("设置密保问题");
	private final JTextField textField_3 = new JTextField();
	private final JButton button = new JButton("创建用户");
	private final JButton btnNewButton = new JButton("取  消");
	private final JLabel lblNewLabel_3 = new JLabel("邮箱");
	private final JTextField textField_4 = new JTextField();
	private final JLabel label_2 = new JLabel("联系方式");
	private final JTextField textField_5 = new JTextField();
	private final JLabel lblNewLabel_4 = new JLabel("");
	private final JLabel lblNewLabel_5 = new JLabel("答案");
	private final ButtonGroup bg = new ButtonGroup();
	boolean emailcorrect = false;              //判断邮箱格式
	boolean usercorrect = false;               //判断用户名格式
	boolean pwdcorrect  = false;               //判断确认密码是否正确
	boolean pwdlcorrect = false;               //判断密码长度是否合格
	
	public Register(){
		textField_5.setBounds(179, 346, 197, 21);
		textField_5.setColumns(10);
		textField_4.setBounds(179, 309, 197, 21);
		textField_4.setColumns(10);
		textField_3.setBounds(179, 265, 197, 21);
		textField_3.setColumns(10);
		textField_2.setBounds(179, 149, 197, 21);
		textField_2.setColumns(10);
		textField_1.setBounds(179, 107, 197, 21);
		textField_1.setColumns(10);
		regFrame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(179, 68, 197, 21);
		regFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//取消
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//System.exit(0);
				regFrame.dispose();
			}
		});
		//创建用户
	    
		button.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(textField.getText().trim().equals("")||textField_1.getText().trim().equals("")
						||textField_2.getText().trim().equals("")||textField_1.getText().trim().equals("")
						||textField_4.getText().trim().equals("")||textField_5.getText().trim().equals("")
						){
					
					JOptionPane.showMessageDialog(regFrame, "存在数据尚未填写，请重新输入！");
				}else{
					int emailnamelength = textField_4.getText().length();
					for(int i = 0;i < emailnamelength;i ++){
						if(textField_4.getText().charAt(i) == '@'){
							emailcorrect = true;
						}
					}
					if(!emailcorrect){
						JOptionPane.showMessageDialog(regFrame, "邮箱格式输入错误！");
					}
					if((textField.getText().charAt(0) <= '9' && textField.getText().charAt(0) >= '0') || textField.getText().charAt(0) == ' '){
						JOptionPane.showMessageDialog(regFrame, "用户名不能以数字和空格开头！");
					}else{
						usercorrect = true;
					}
					if(textField_1.getText().equals(textField_2.getText())){
						pwdcorrect = true;
					}else{
						JOptionPane.showMessageDialog(regFrame, "两次密码输入不同！");
					}
					if(textField_1.getText().length() >= 6){
						pwdlcorrect = true;
					}else{
						JOptionPane.showMessageDialog(regFrame, "密码长度过短，请重新设置！");
					}
					
					if(emailcorrect && usercorrect && pwdcorrect && pwdlcorrect){
						String name = textField.getText();
						String pwd = textField_1.getText();
						String sex = "男";
						if(radioButton_1.isSelected()){
							sex = "女";
						}else{
							sex = "男";
						}
						String ans = textField_3.getText();
						String email = textField_4.getText();
						String num  = textField_5.getText();
						JOptionPane.showMessageDialog(regFrame, "恭喜你，用户成功创建！");
						mc.insert("insert into user(name,pwd,sex,ans,email,tel) values(\""+name+"\",\""+pwd+"\",\""+sex+"\",\""+ans+"\",\""+email+"\",\""+num+"\")");
						regFrame.dispose();
					}
				
					
				}
			}
		});
		JLabel label = new JLabel("用户名");
		label.setBounds(113, 68, 47, 21);
		regFrame.getContentPane().add(label);
		lblNewLabel.setBounds(123, 107, 34, 21);
		
		regFrame.getContentPane().add(lblNewLabel);
		lblNewLabel_1.setBounds(103, 149, 57, 21);
		
		regFrame.getContentPane().add(lblNewLabel_1);
		
		
		radioButton.setBounds(179, 187, 66, 21);
		regFrame.getContentPane().add(radioButton);
		lblNewLabel_2.setBounds(126, 187, 34, 21);
		bg.add(radioButton);
		bg.add(radioButton_1);
		regFrame.getContentPane().add(lblNewLabel_2);
		
		regFrame.getContentPane().add(textField_1);
		
		regFrame.getContentPane().add(textField_2);
		radioButton_1.setBounds(268, 187, 66, 21);
		
		regFrame.getContentPane().add(radioButton_1);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"你的学号或工号", "你父亲的姓名", "你母亲的姓名", "你最喜欢的课程是", "你最喜欢的体育明星是"}));
		comboBox.setBounds(179, 225, 197, 21);
		
		regFrame.getContentPane().add(comboBox);
		label_1.setBounds(80, 225, 80, 21);
		
		regFrame.getContentPane().add(label_1);
		
		regFrame.getContentPane().add(textField_3);
		button.setBackground(new Color(0, 191, 255));
		button.setForeground(Color.WHITE);
		button.setBounds(78, 400, 133, 40);
		button.setBorderPainted(false);
		regFrame.getContentPane().add(button);
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(291, 400, 133, 40);
		btnNewButton.setBorderPainted(false);
		regFrame.getContentPane().add(btnNewButton);
		lblNewLabel_3.setBounds(123, 309, 34, 21);
		
		regFrame.getContentPane().add(lblNewLabel_3);
		
		regFrame.getContentPane().add(textField_4);
		label_2.setBounds(103, 346, 57, 21);
		
		regFrame.getContentPane().add(label_2);
		
		regFrame.getContentPane().add(textField_5);
		lblNewLabel_4.setIcon(new ImageIcon("Image/随心记.png"));
		lblNewLabel_4.setBounds(0, 0, 524, 50);
		
		regFrame.getContentPane().add(lblNewLabel_4);
		lblNewLabel_5.setBounds(126, 265, 34, 21);
		regFrame.setResizable(false);
		regFrame.getContentPane().add(lblNewLabel_5);
		regFrame.setSize(540, 500);
		regFrame.setVisible(true);
	}
}
