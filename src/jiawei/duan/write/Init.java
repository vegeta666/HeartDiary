/*
 * 本类用于实现登录后的初始界面
 * 作者：段佳伟
 * 时间：2017-06
 */
package jiawei.duan.write;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.management.Query;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import jiawei.duan.login.Diary_log;
import jiawei.duan.login.newPwd;

public class Init {
	JFrame initFrame = new JFrame("初始界面");
	// 全局的位置变量，用于表示鼠标在窗口上的位置
	static Point origin = new Point();
	
	public Init(){
		initFrame.setSize(860,528);
		initFrame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("写日记");
		btnNewButton.setIcon(new ImageIcon("Image//写.png"));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 153));
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.setBounds(306, 149, 252, 54);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Write();
			}
		});
		//btnNewButton.setBorderPainted(false);
		//btnNewButton.setContentAreaFilled(false);
		initFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("查询日记");
		btnNewButton_3.setBackground(new Color(255, 255, 153));
		btnNewButton_3.setIcon(new ImageIcon("Image//查.png"));
		btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton_3.setBounds(306, 232, 252, 54);
		initFrame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Find();
			}
		});
		JButton btnNewButton_4 = new JButton("编者的话");
		btnNewButton_4.setBackground(new Color(255, 255, 153));
		btnNewButton_4.setIcon(new ImageIcon("Image//信息.png"));
		btnNewButton_4.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton_4.setBounds(306, 313, 252, 54);
		initFrame.getContentPane().add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(initFrame, "软件名：随心记" + '\n' + "作者：段佳伟" + '\n' + "学号：2015110410" + '\n'
						+ "学校： 四川师范大学" + '\n' + "学院：计算机科学学院" + '\n' + "开发时间： 2017-06" );
			}
		});
		JButton btnNewButton_5 = new JButton("注销");
		btnNewButton_5.setBackground(new Color(255, 255, 153));
		btnNewButton_5.setIcon(new ImageIcon("Image//注销.png"));
		btnNewButton_5.setFont(new Font("宋体", Font.BOLD, 16));
		//btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setBounds(306, 397, 252, 54);
		initFrame.getContentPane().add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("Welcome to mind, My Dear!");
		lblNewLabel.setForeground(new Color(0, 0, 153));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(275, 45, 364, 45);
		initFrame.getContentPane().add(lblNewLabel);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("Image\\初始3.png"));
		label.setBounds(0, 0, 860, 528);
		initFrame.getContentPane().add(label,-1);
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBackground(new Color(0, 191, 255));
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 23));
		btnNewButton_1.setBounds(45, 0, 55, 29);
		btnNewButton_1.setLocation(760, 5);
		initFrame.getContentPane().add(btnNewButton_1,0);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				initFrame.setState(JFrame.ICONIFIED);
			}
		});
		JButton btnNewButton_2 = new JButton("×");
		
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBounds(45, 0, 55, 29);
		btnNewButton_2.setBackground(new Color(0, 191, 255));
		btnNewButton_2.setLocation(810, 5);
		initFrame.getContentPane().add(btnNewButton_2,0);
		//关闭
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int n1 = JOptionPane.showConfirmDialog(initFrame, "确认退出吗?", "确认退出框", JOptionPane.YES_NO_OPTION); 
				if (n1 == JOptionPane.YES_OPTION) {  
		            // ......  
					System.exit(0);
		        } 
				
			}
		});
		//注销
		btnNewButton_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				initFrame.dispose();
				try {
					new Diary_log();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});		
		drog();
		initFrame.setUndecorated(true);
		Toolkit kit = Toolkit.getDefaultToolkit();    // 定义工具包
	     Dimension screenSize = kit.getScreenSize();   // 获取屏幕的尺寸
	     int screenWidth = screenSize.width/2;         // 获取屏幕的宽
	     int screenHeight = screenSize.height/2;       // 获取屏幕的高

	     initFrame.setLocation(screenWidth/2-50 , screenHeight/2 - 50);
		initFrame.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
private void drog() {
		
		initFrame.addMouseListener(new MouseAdapter() {
			// 按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		initFrame.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = initFrame.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				initFrame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		});
	}
}
