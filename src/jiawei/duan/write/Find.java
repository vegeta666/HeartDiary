/*
 * 本类用于查找日记
 * 作者:段佳伟
 * 时间：2017-06
 */
package jiawei.duan.write;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.MutableComboBoxModel;

import java.awt.CardLayout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.text.Document;

import jiawei.duan.date.CalendarPanel;
import jiawei.duan.login.newPwd;
import jiawei.duan.sql.MysqlConnecter;
import java.awt.Font;

public class Find {
	JFrame findFrame = new JFrame("查询日记");
	JComboBox comboBox = new JComboBox();

	JComboBox comboBox_2 = new JComboBox();
	DefaultListModel listModel = new DefaultListModel();
	DefaultListModel listModel_1 = new DefaultListModel();
	DefaultListModel listModel_2 = new DefaultListModel();
	String mood = null;
	String weatherString = null;
	String dateString = null;
	JList list_1 ;
	JList list ;
	JList list_2 ;
	MysqlConnecter mc = new MysqlConnecter();
	String nameString = null;
	private JTextField textField;
	public Find(){
		//listModel.addElement("dasad");
		list_1 = new JList(listModel);
		list_1.setVisibleRowCount(10);
		list = new JList(listModel_1);
		list.setVisibleRowCount(10);
		
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_2 = new JList(listModel_2);
		list_2.setVisibleRowCount(10);
		File file = new File("user.txt");
		Scanner input = null;
		try {
			input = new Scanner(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(input.hasNext()){
			nameString = input.next();
			break;
			
		}
		findFrame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(0, 53, 681, 390);
		findFrame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("按心情查找", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("标题");
		lblNewLabel.setBounds(106, 0, 44, 28);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("天气");
		label.setBounds(222, 7, 54, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("心情");
		label_1.setBounds(356, 7, 54, 15);
		panel.add(label_1);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"开心", "烦躁", "紧张", "伤心", "得意"}));
		comboBox.setBounds(10, 4, 54, 21);
		panel.add(comboBox);
		
		JLabel label_2 = new JLabel("日期");
		label_2.setBounds(492, 7, 54, 15);
		panel.add(label_2);
		
		
		list_1.setBounds(85, 38, 505, 288);
		panel.add(list_1);
		
		JButton button = new JButton("查询");
		button.setBounds(90, 347, 107, 28);
		panel.add(button);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listModel.removeAllElements();
				mood = comboBox.getSelectedItem().toString();
				ArrayList<Map<String, String>> result2 = mc.select("select * from diary where uname =   \""+nameString+"\"  and mood = \""+mood+"\"   ", "diary");
		        
		        	   for (Map<String, String> map : result2) {
		        		   
		        		   String ntitle = null;
		        		   
			            	String nweather = null;
			            	Document ncontent  = null;
			            	String ndate = null;
			            	Document content = null;
				            for (Map.Entry<String, String> entry : map.entrySet()) {
				            	
				            	if(entry.getKey().equals("title")){
				            		ntitle = entry.getValue();
				            	}
				            	
				            	if(entry.getKey().equals("weather")){
				            		nweather = entry.getValue();
				            	}
				            	if(entry.getKey().equals("date")){
				            		ndate = entry.getValue();
				            	}
				            	if(entry.getKey().equals("content")){
				            		
				            	
				            		content = null;
				            		
				            		
				            	}
				            	
				            	
				            	
				            	
				            }
				            Diary newDiary = new Diary(ndate, nweather, mood, ntitle, ncontent);
				          
			            	listModel.addElement(newDiary.toString());
				        }
		        	   
		        
			}
		});
	
		
		
		

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("按天气查找", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(299, 5, 1, 1);
		panel_3.setLayout(null);
		panel_1.add(panel_3);
		
		JLabel label_3 = new JLabel("标题");
		label_3.setBounds(106, 0, 44, 28);
		panel_3.add(label_3);
		
		JLabel label_4 = new JLabel("天气");
		label_4.setBounds(222, 7, 54, 15);
		panel_3.add(label_4);
		
		JLabel label_5 = new JLabel("心情");
		label_5.setBounds(356, 7, 54, 15);
		panel_3.add(label_5);
		
		
		
		
		JLabel label_6 = new JLabel("日期");
		label_6.setBounds(492, 7, 54, 15);
		panel_3.add(label_6);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(0, 0, 600, 385);
		panel_1.add(panel_4);
		
		JLabel label_7 = new JLabel("标题");
		label_7.setBounds(106, 0, 44, 28);
		panel_4.add(label_7);
		
		JLabel label_8 = new JLabel("天气");
		label_8.setBounds(222, 7, 54, 15);
		panel_4.add(label_8);
		
		JLabel label_9 = new JLabel("心情");
		label_9.setBounds(356, 7, 54, 15);
		panel_4.add(label_9);
		
		JLabel label_10 = new JLabel("日期");
		label_10.setBounds(492, 7, 54, 15);
		panel_4.add(label_10);
		
		
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"晴", "雨", "阴", "雪", "雾"}));
		comboBox_2.setBounds(10, 4, 54, 21);
		panel_4.add(comboBox_2);
		
		
		list.setBounds(93, 44, 497, 294);
		panel_4.add(list);
		
		JButton button_1 = new JButton("查询");
		button_1.setBounds(93, 348, 93, 27);
		panel_4.add(button_1);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listModel_1.removeAllElements();
				// TODO Auto-generated method stub
				weatherString = comboBox_2.getSelectedItem().toString();
				ArrayList<Map<String, String>> result2 = mc.select("select * from diary where uname =   \""+nameString+"\"  and weather = \""+weatherString+"\"   ", "diary");
		        
		        	   for (Map<String, String> map : result2) {
		        		  
		        		   String ntitle = null;
		        		  
			            	String nmood = null;
			            	Document ncontent  = null;
			            	String ndate = null;
			            	Document content = null;
				            for (Map.Entry<String, String> entry : map.entrySet()) {
				            	
				            	if(entry.getKey().equals("title")){
				            		ntitle = entry.getValue();
				            	}
				            	
				            	if(entry.getKey().equals("mood")){
				            		nmood = entry.getValue();
				            	}
				            	if(entry.getKey().equals("date")){
				            		ndate = entry.getValue();
				            	}
				            	if(entry.getKey().equals("content")){
				            	
				            		content = null;
				            		
				            		
				            	}
				            	
				            	
				            	
				            	
				            }
				            Diary newDiary = new Diary(ndate, weatherString, nmood, ntitle, ncontent);
				          
			            	listModel_1.addElement(newDiary.toString());
				        }
		        	   
			}
		});
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("按日期查找", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(0, 0, 600, 397);
		panel_2.add(panel_5);
		
		JLabel label_11 = new JLabel("标题");
		label_11.setBounds(106, 0, 44, 28);
		panel_5.add(label_11);
		
		JLabel label_12 = new JLabel("天气");
		label_12.setBounds(222, 7, 54, 15);
		panel_5.add(label_12);
		
		JLabel label_13 = new JLabel("心情");
		label_13.setBounds(356, 7, 54, 15);
		panel_5.add(label_13);
		
		JLabel label_14 = new JLabel("日期");
		label_14.setBounds(492, 7, 54, 15);
		panel_5.add(label_14);
		
		textField = new JTextField();
		textField.setBounds(10, 4, 75, 21);
		panel_5.add(textField);
		textField.setColumns(10);
		//CalendarPanel p = new CalendarPanel(textField, "yyyy-MM-dd");
		//p.initCalendarPanel();
		//findFrame.getContentPane().add(p);
		
		
		list_2.setBounds(89, 44, 501, 299);
		panel_5.add(list_2);
		
		JButton button_2 = new JButton("查询");
		button_2.setBounds(106, 352, 93, 28);
		panel_5.add(button_2);
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dateString = textField.getText().trim();
				ArrayList<Map<String, String>> result2 = mc.select("select * from diary where uname =   \""+nameString+"\"  and date = \""+dateString+"\"   ", "diary");
		        
		        	   for (Map<String, String> map : result2) {
		        		  
		        		   String ntitle = null;
		        		  
			            	String nmood = null;
			            	Document ncontent  = null;
			            	String nweather = null;
			            	Document content = null;
				            for (Map.Entry<String, String> entry : map.entrySet()) {
				            	
				            	if(entry.getKey().equals("title")){
				            		ntitle = entry.getValue();
				            	}
				            	
				            	if(entry.getKey().equals("mood")){
				            		nmood = entry.getValue();
				            	}
				            	if(entry.getKey().equals("weather")){
				            		nweather = entry.getValue();
				            	}
				            	if(entry.getKey().equals("content")){
				            		
				            		
				            		content = null;
				            		
				            		
				            	}
				            	
				            	
				            	
				            	
				            }
				            Diary newDiary = new Diary(dateString, nweather, nmood, ntitle, ncontent);
				           
			            	listModel_2.addElement(newDiary.toString());
				        }
		        	   
			}
		});
		JButton btnNewButton_2 = new JButton("< 返回");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_2.setBounds(0, 10, 84, 23);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		findFrame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				findFrame.dispose();
			}
		});
		
		
		
		findFrame.setResizable(false);
		
		findFrame.setSize(760,528);
		findFrame.setVisible(true);
	}
}
