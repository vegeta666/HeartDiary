/*
 * 本类用于实现登录界面
 * 作者：段佳伟
 * 时间：2017-06
 */
package jiawei.duan.login;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import jiawei.duan.sql.MysqlConnecter;
import jiawei.duan.write.Init;
import jiawei.duan.write.Write;

public class Diary_log {
	public JFrame logFrame =  new JFrame("随心记");
	JLabel lblNewLabel_1 = new JLabel("密码");
	private JTextField textField= new RoundJTextField(10);
	private JTextField textField_1= new RoundJPasswordField(10);
	JButton btnNewButton_2 = new JButton("×");
	JButton btnNewButton = new JButton("登  录");
	JButton btnNewButton_1 = new JButton("-");
	// 全局的位置变量，用于表示鼠标在窗口上的位置
	JRadioButton rdbtnNewRadioButton = new JRadioButton("记住密码",true);
	public JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("自动登录",false);
	String nameString = null;
	static Point origin = new Point();
	String s3 = null;
	public Diary_log() throws IOException {
		//读取文件
		File file = new File("user.txt");
		Scanner input = new Scanner(new FileInputStream(file));
		
		int flag = 0;
		
		while(input.hasNext()){
			if(flag == 0){
				String s1 = input.next();
				textField.setText(s1);
				flag = 1;
			}else if(flag == 1){
				String s2 = input.next();
				textField_1.setText(s2);
				flag = 3;
			}else{
				s3 = input.next();
				if(s3.equals("true")){
					rdbtnNewRadioButton_1.setSelected(true);
				}else{
					rdbtnNewRadioButton_1.setSelected(false);
				}
			}
			
		}
		
		
		logFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setBounds(111, 164, 48, 22);
		logFrame.getContentPane().add(lblNewLabel);
	
		
		
		lblNewLabel_1.setBounds(122, 211, 37, 15);
		logFrame.getContentPane().add(lblNewLabel_1);
		
		
		textField.setBounds(159, 161, 184, 29);
		logFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		textField_1.setBounds(159, 204, 184, 29);
		logFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(159, 306, 184, 34);
		logFrame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("注册用户");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 12));
		lblNewLabel_2.setForeground(new Color(0, 206, 209));
		lblNewLabel_2.setBounds(353, 164, 80, 22);
		logFrame.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				/**鼠标点击事件(包括按下和弹起两个动作)处理方法.**/
				new Register();
				        }
		});
		
		JLabel lblNewLabel_3 = new JLabel("忘记密码");
		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 12));
		lblNewLabel_3.setForeground(new Color(0, 191, 255));
		lblNewLabel_3.setBounds(353, 207, 80, 22);
		logFrame.getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				/**鼠标点击事件(包括按下和弹起两个动作)处理方法.**/
				new LosePw();
				        }
		});
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBackground(new Color(0, 0, 255));
		lblNewLabel_4.setForeground(Color.CYAN);
		lblNewLabel_4.setIcon(new ImageIcon("Image/蓝1.png"));
		
		
		lblNewLabel_4.setBounds(0, 0, 520, 138);
		logFrame.getContentPane().add(lblNewLabel_4,-1);
		
		
		rdbtnNewRadioButton.setContentAreaFilled(false);
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setBounds(151, 259, 121, 23);
		logFrame.getContentPane().add(rdbtnNewRadioButton);
		
		
		rdbtnNewRadioButton_1.setContentAreaFilled(false);
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		rdbtnNewRadioButton_1.setBounds(274, 259, 121, 23);
		logFrame.getContentPane().add(rdbtnNewRadioButton_1);
		logFrame.getContentPane().setFont(new Font("宋体", Font.BOLD, 12));
		logFrame.getContentPane().setBackground(new Color(245, 245, 245));
		
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(0, 135, 121, 88);
		logFrame.getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setIcon(new ImageIcon("Image/随心记.png"));
		
		
		
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBackground(new Color(0, 191, 255));
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 23));
		btnNewButton_1.setBounds(415, 0, 55, 29);
		logFrame.getContentPane().add(btnNewButton_1,0);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				logFrame.setState(JFrame.ICONIFIED);
			}
		});
		
		
		
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBounds(465, 0, 55, 29);
		btnNewButton_2.setBackground(new Color(0, 191, 255));
		logFrame.getContentPane().add(btnNewButton_2,0);
		//关闭
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int n1 = JOptionPane.showConfirmDialog(logFrame, "确认退出吗?", "确认退出框", JOptionPane.YES_NO_OPTION); 
				if (n1 == JOptionPane.YES_OPTION) {  
		            // ......  
					System.exit(0);
		        } 
				
			}
		});
		
		
		
		
		
		
		logFrame.setUndecorated(true);
	
		drog();
		Toolkit kit = Toolkit.getDefaultToolkit();    // 定义工具包
	     Dimension screenSize = kit.getScreenSize();   // 获取屏幕的尺寸
	     int screenWidth = screenSize.width/2;         // 获取屏幕的宽
	     int screenHeight = screenSize.height/2;       // 获取屏幕的高

	     logFrame.setLocation(screenWidth/2 + 100, screenHeight/2);

		logFrame.setBackground(Color.GREEN);
		logFrame.setSize(520, 350);
		
		logFrame.setVisible(true);
		//登录
		init1();
		
	}
	public void init1(){
		
		if(s3.equals("true")){
			//logFrame.setVisible(true);
			boolean userflag = false;
			boolean pwdflag = false;
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
			            	if(entry.getKey().equals("pwd")){
			            		if(entry.getValue().equals(textField_1.getText())){
			                		pwdflag = true;
			                		
			                		break;
			                	}
			            	}
			            }
			        }
	        	   
	        }
			
	        PrintStream out = null;
			if(userflag&&pwdflag){
				File file1 = new File("user.txt");
				try {
					 out = new PrintStream(new FileOutputStream(file1));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(rdbtnNewRadioButton.isSelected()){
					out.println(textField.getText().trim());
					out.println(textField_1.getText().trim());
					//out.print("true");
					
					
				} else{
					out.print("");
				}
				if(rdbtnNewRadioButton_1.isSelected()){
					out.print("true");
				}else{
					out.print("false");
				}
				out.close();
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				new Init();
				logFrame.dispose();
				
			}else{
				JOptionPane.showMessageDialog(logFrame, "用户名或者密码错误！");
			}
			
		}else{
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					boolean userflag = false;
					boolean pwdflag = false;
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
					            	if(entry.getKey().equals("pwd")){
					            		if(entry.getValue().equals(textField_1.getText())){
					                		pwdflag = true;
					                		
					                		break;
					                	}
					            	}
					            }
					        }
			        	   
			        }
			        PrintStream out = null;
					if(userflag&&pwdflag){
						File file = new File("user.txt");
						try {
							 out = new PrintStream(new FileOutputStream(file));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(rdbtnNewRadioButton.isSelected()){
							out.println(textField.getText().trim());
							out.println(textField_1.getText().trim());
							//out.print("false");
							
						} else{
							out.print("");
						}
						if(rdbtnNewRadioButton_1.isSelected()){
							out.print("true");
						}else{
							out.print("false");
						}
						out.close();
						
						
						logFrame.dispose();
						new Init();
					}else{
						JOptionPane.showMessageDialog(logFrame, "用户名或者密码错误！");
					}
				}
			});
		}
	}
private void drog() {
		
		logFrame.addMouseListener(new MouseAdapter() {
			// 按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		logFrame.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = logFrame.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				logFrame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		});
	}
	public static void main(String[] args) throws IOException {
		new Diary_log();
	}

}
