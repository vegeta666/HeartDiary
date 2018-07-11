/*
 * 本类用于写日记界面
 * 作者：段佳伟
 * 时间：2017-06
 */
package jiawei.duan.write;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.text.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.text.StyledDocument;

import jiawei.duan.date.*;
import jiawei.duan.login.newPwd;
import jiawei.duan.sql.MysqlConnecter;

public class Write {
	JFrame wrFrame = new JFrame("写日记");
	private JTextField textField= new JTextField();
	private StyledDocument doc = null;
	//JScrollPane scroll;
	MysqlConnecter mc = new MysqlConnecter();
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
	JTextPane textArea = new JTextPane();
	Document content;
	private JTextField textField_1;
	public Write(){
		wrFrame.setResizable(false);
		wrFrame.getContentPane().setLayout(null);
		
		textField_1 = new JTextField();         //日历
		textField_1.setBounds(431, 85, 72, 21);
		
		textField_1.setColumns(10);
		CalendarPanel p = new CalendarPanel(textField_1, "yyyy-MM-dd");
		p.initCalendarPanel();
		JScrollPane scrollPane = new JScrollPane();
		//设置自动滑动
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(95, 113, 527, 460);
		wrFrame.getContentPane().add(scrollPane,0);
		wrFrame.getContentPane().add(p,1);
		//wrFrame.getLayeredPane().add(p, new Integer(Integer.MIN_VALUE));
		wrFrame.getContentPane().add(textField_1,0);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Image//背景.png"));
		lblNewLabel.setBounds(0, 1, 691, 752);
		wrFrame.getContentPane().add(lblNewLabel,-1);
		
		JButton button = new JButton("保存");
		button.setBounds(95, 608, 127, 32);
		wrFrame.getContentPane().add(button,0);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(textField.getText().trim().equals("")||textField_1.getText().trim().equals("")){
					JOptionPane.showMessageDialog(wrFrame, "标题或者日历不能为空！");
				}else{
					String title = textField.getText();
					String mood = comboBox_1.getSelectedItem().toString();
					String weather = comboBox.getSelectedItem().toString();
					String date = textField_1.getText();
					content = textArea.getDocument();
					File file = new File("user.txt");
					Scanner input = null;
					try {
						input = new Scanner(new FileInputStream(file));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String uname = null;
					while(input.hasNext()){
						uname = input.next();
						break;
					}
					
					mc.addDiaryToDatabase(title, content, date, weather, mood, uname);
				}
				
			}
		});
		JLabel label = new JLabel("正文");
		label.setBounds(97, 88, 36, 15);
		wrFrame.getContentPane().add(label,0);
		
		JButton btnNewButton = new JButton("插入图片");
		btnNewButton.setBounds(300, 608, 127, 32);
		wrFrame.getContentPane().add(btnNewButton,0);
		
		btnNewButton.addActionListener(new ActionListener() { // 插入图片事件
			   public void actionPerformed(ActionEvent arg0) {
			    JFileChooser f = new JFileChooser(); // 查找文件
			    f.showOpenDialog(null);
			    insertIcon(f.getSelectedFile()); // 插入图片
			   
			    
			   }
			  });
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.setBounds(492, 608, 127, 32);
		wrFrame.getContentPane().add(btnNewButton_1,0);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				wrFrame.dispose();
			}
		});
		JLabel label_1 = new JLabel("标题");
		label_1.setBounds(97, 52, 36, 15);
		wrFrame.getContentPane().add(label_1,0);
		
		
		textField.setBounds(132, 49, 222, 21); //标题
		wrFrame.getContentPane().add(textField,0);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("天气");
		label_2.setBounds(386, 52, 31, 15);
		wrFrame.getContentPane().add(label_2,0);
		
		JLabel label_3 = new JLabel("日期");
		label_3.setBounds(386,88,36,15);
		wrFrame.getContentPane().add(label_3,0);
		
		
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"晴", "雨", "阴", "雪", "雾"}));
		comboBox.setBounds(431, 49, 47, 21);
		wrFrame.getContentPane().add(comboBox,0);
		
		JLabel label_4 = new JLabel("心情");
		label_4.setBounds(519, 52, 31, 15);
		wrFrame.getContentPane().add(label_4,0);
		
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"开心", "烦躁", "紧张", "伤心", "得意"}));
		comboBox_1.setBounds(560, 49, 62, 21);
		wrFrame.getContentPane().add(comboBox_1,0);
		
		
		
		
		textArea.setFont(new Font("宋体", Font.ITALIC, 18));
		scrollPane.setViewportView(textArea);
		 doc = textArea.getStyledDocument();
		//textArea.setLineWrap(true);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon("Image//随心记.png"));
		label_5.setBounds(0, 0, 664, 39);
		wrFrame.getContentPane().add(label_5,0);
		wrFrame.setSize(691,752);
		wrFrame.setVisible(true);
	}
	 private void insertIcon(File file) {
		  textArea.setCaretPosition(doc.getLength()); // 设置插入位置
		  Image i = scaleImage(file.getPath());
		  textArea.insertIcon(new ImageIcon(i)); // 插入图片
		  
		 }
	 //压缩图
	 public static Image scaleImage(String fielPath) {

			try {
				BufferedImage bufferImage = ImageIO.read(new File(fielPath));
				if(bufferImage.getWidth() > bufferImage.getHeight())
				{
					float scale = 527 * 1.0f / bufferImage.getWidth();
					return bufferImage.getScaledInstance((int) (bufferImage.getWidth() * scale),
							(int) (bufferImage.getHeight() * scale), BufferedImage.SCALE_DEFAULT);
				}
				else
				{
					int w = 460;
					int height =  bufferImage.getHeight(null)*460/bufferImage.getWidth(null);//按比例，将高度缩减
					return bufferImage.getScaledInstance((int) (w ),
							(int) (height ), BufferedImage.SCALE_SMOOTH);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	 
}
