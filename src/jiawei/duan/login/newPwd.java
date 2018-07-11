/*
 * 本类用于设置新密码
 * 作者:段佳伟
 * 时间：2017-06
 */
package jiawei.duan.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import jiawei.duan.sql.MysqlConnecter;
import jiawei.duan.write.Init;

public class newPwd {
	JFrame pwdFrame = new JFrame("设置新密码");
	private JTextField textField;
	private JTextField textField_1;
	public newPwd(final String nameString){
		pwdFrame.getContentPane().setLayout(null);
		
		textField = new JPasswordField();
		textField.setBounds(173, 106, 178, 21);
		pwdFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(173, 153, 178, 21);
		pwdFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("新密码");
		label.setBounds(108, 109, 55, 15);
		pwdFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("确认密码");
		label_1.setBounds(96, 156, 67, 15);
		pwdFrame.getContentPane().add(label_1);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.setBounds(108, 212, 93, 23);
		pwdFrame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MysqlConnecter mc = new MysqlConnecter();
				if(textField.getText().trim().equals("") || textField_1.getText().trim().equals("")){
					JOptionPane.showConfirmDialog(pwdFrame, "存在数据为空!");
				}else{
					if(textField.getText().equals(textField_1.getText())){
						mc.update("update user set pwd = \""+textField.getText()+"\" where name = \""+nameString+"\"  ");
						pwdFrame.dispose();
						
					}else{
						JOptionPane.showMessageDialog(pwdFrame, "确认密码不符合！");
					}
				}
			}
		});
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.setBounds(295, 212, 93, 23);
		pwdFrame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				pwdFrame.dispose();
			}
		});
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Image//初始.png"));
		lblNewLabel.setBounds(0, 0, 484, 73);
		pwdFrame.getContentPane().add(lblNewLabel);
		pwdFrame.setResizable(false);
		pwdFrame.setSize(500, 300);
		
		pwdFrame.setVisible(true);
	}
}
