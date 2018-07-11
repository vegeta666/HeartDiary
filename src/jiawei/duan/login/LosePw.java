/*
 * 本类用于实现忘记密码
 * 作者：段佳伟
 * 时间：2017-06
 */
package jiawei.duan.login;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import jiawei.duan.sql.MysqlConnecter;

public class LosePw {
	JFrame lpFrame = new JFrame("找回密码");
	private JTextField textField;
	private JTextField textField_1;
	private ValidCode vcode;  
	private JTextField textField_2;
	//查询是否存在该用户
	public String  nameString = null; //暂时存放用户名
	public LosePw(){
		lpFrame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setBounds(132, 86, 54, 15);
		lpFrame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(132, 111, 197, 21);
		lpFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("请选择密保问题：");
		label_1.setBounds(132, 154, 110, 15);
		lpFrame.getContentPane().add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"你的学号或工号", "你父亲的姓名", "你母亲的姓名", "你最喜欢的课程是", "你最喜欢的体育明星是"}));
		comboBox.setBounds(132, 179, 197, 21);
		lpFrame.getContentPane().add(comboBox);
		
		JLabel label_2 = new JLabel("你的答案：");
		label_2.setBounds(133, 221, 71, 15);
		lpFrame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(132, 246, 197, 21);
		lpFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("确认信息");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(76, 389, 110, 41);
		lpFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取  消");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 191, 255));
		btnNewButton_1.setBounds(269, 389, 110, 41);
		lpFrame.getContentPane().add(btnNewButton_1);
		
		
        //System.out.println(result2);
        //确认信息
        
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				boolean userflag = false;
				boolean ansflag = false;
				MysqlConnecter mc = new MysqlConnecter();
				
				ArrayList<Map<String, String>> result1 = mc.select("select * from user", "user");
				
		        // map的遍历方法
				
		        for (Map<String, String> map : result1) {
		            //System.out.println("______________________");
		            for (Map.Entry<String, String> entry : map.entrySet()) {
		            	if(entry.getKey().equals("name")){
		            		if(entry.getValue().equals(textField.getText())){
		                		userflag = true;
		                		nameString = entry.getValue();
		                		break;
		                	}
		            	}
		            }
		        }
		       
		        ArrayList<Map<String, String>> result2 = mc.select("select * from user where name =   \""+nameString+"\"      ", "user");
		        if(userflag){
		        	   for (Map<String, String> map : result2) {
				            //System.out.println("______________________");
				            for (Map.Entry<String, String> entry : map.entrySet()) {
				            	if(entry.getKey().equals("ans")){
				            		if(entry.getValue().equals(textField_1.getText())){
				                		ansflag = true;
				                		
				                		break;
				                	}
				            	}
				            }
				        }
		        }
				
				// TODO Auto-generated method stub
				if(textField.getText().trim().equals("")||textField_1.getText().trim().equals("")||textField_2.getText().trim().equals("")){
					JOptionPane.showMessageDialog(lpFrame,"存在数据为空，请重新输入！");
				}else{
					
					if(!isValidCodeRight()){
						JOptionPane.showMessageDialog(lpFrame, "验证码输入错误！");
					}
					//用户名判断
					if(!userflag){
						JOptionPane.showMessageDialog(lpFrame, "查无此人!");
					}
					if(!ansflag){
						JOptionPane.showMessageDialog(lpFrame, "密保问题回答错误!");
					}
					
					if(isValidCodeRight()&&userflag&&ansflag){
						new newPwd(nameString);
					}
					
				}
			}
		});
		//取消
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			 lpFrame.dispose();
			}
		});
		JLabel label_3 = new JLabel("验证码：");
		label_3.setBounds(133, 283, 54, 15);
		lpFrame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(133, 312, 109, 38);
		lpFrame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Image/随心记.png"));
		lblNewLabel.setBounds(0, 0, 494, 76);
		lpFrame.getContentPane().add(lblNewLabel);
		vcode = new ValidCode(); 
		
		vcode.setBounds(252, 310, 60, 40);
		lpFrame.getContentPane().add(vcode);
		lpFrame.setResizable(false);
		lpFrame.setSize(500, 500);
		lpFrame.setVisible(true);
	}
	 public boolean isValidCodeRight() {  
   	  
	        if (textField_2 == null) {  
	            return false;  
	        }  
	        if (vcode == null) {  
	            return true;  
	        }  
	        if (vcode.getCode().equals(textField_2.getText())) {  
	            return true;  
	        }  
	        return false;  
	    }  
}
