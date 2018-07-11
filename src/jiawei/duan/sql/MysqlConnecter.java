/*
 * 本类用于数据库各项操作
 * 作者：段佳伟
 * 时间：2017-06
 */
package jiawei.duan.sql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.Document;



public class MysqlConnecter {
	private Connection connection = null;
	 
    private boolean connected = false;

    public MysqlConnecter() {
        try {
            Class.forName(Configure.DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR AT MysqlConnecter");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(Configure.URL, Configure.USERNAME, Configure.PASSWORD);
            connected = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insert(String sql)  
    {  
        int lineNum = 0;
        if (!connected) return 0;
        try{  
            PreparedStatement preStmt = connection.prepareStatement(sql);    
            lineNum = preStmt.executeUpdate();  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
        return lineNum; 
    }
    /**
	 * 添加日记到数据库
	 * @return 返回是否添加成功 true|false
	 */
	public boolean addDiaryToDatabase(String title,Document content, String date,String weather,String mood,String uname) {
		
		PreparedStatement preState = null;
		String sql = "insert into diary (title,content,date,weather,mood,uname) values (?,?,?,?,?,?)";
		int changeRows = 0;

		
		
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		ByteArrayInputStream bais = null;
		try {
			preState = connection.prepareStatement(sql); 
			
			baos = new ByteArrayOutputStream();
			try
			{
				oos = new ObjectOutputStream(baos);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			try
			{
				oos.writeObject(content);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			byte[] byteArray = baos.toByteArray();
			bais = new ByteArrayInputStream(byteArray);
			preState.setString(1, title);
			preState.setBinaryStream(2, bais, byteArray.length);
			preState.setString(3, date);
			preState.setString(4, weather);
			preState.setString(5, mood);
			preState.setString(6, uname);

			changeRows = preState.executeUpdate(); 
			if (changeRows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return false;
	}
    public int update(String sql)
    {  
        int lineNum = 0;
        if (!connected) return 0;
        try{  
            PreparedStatement preStmt = connection.prepareStatement(sql);   
            lineNum = preStmt.executeUpdate();  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
        return lineNum;
    }  
    public ArrayList<Map<String, String>> select(String sql, String tableName)
    {   
        ArrayList<Map<String, String>> result = new ArrayList<>();

        try  
        {  
            Statement stmt = connection.createStatement();  
            ResultSet rs = stmt.executeQuery(sql);
            String[] frame = getFrame(tableName);
            while (rs.next())  
            {  
                Map<String, String> tmp = new HashMap<>();
                for (String key : frame) {
                    if (key == "#") break;
                    tmp.put(key, rs.getString(key));
                }
                result.add(tmp);
            }   
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
        return result;  
    }
    public int delete(String sql)  
    {   
        int lineNum = 0;    
        try  
        {  
            Statement stmt = connection.createStatement();  
            lineNum = stmt.executeUpdate(sql);  
        }  
        catch (SQLException e)  
        {  
            e.printStackTrace();  
        }  
        return lineNum;  
    }  
    // 获取当前表的关键字，并以字符串数组的形式返回：如“username”，“id“等
    private String[] getFrame(String tableName) {
        String[] result = new String[Configure.TABLELEN];
         try  
            {  
                Statement stmt = connection.createStatement();  
                ResultSet rs = stmt.executeQuery("show columns from " + tableName);
                int i = 0;
                while (rs.next())  
                {  
                    result[i++] = rs.getString(1);
                }
                result[i] = "#";
            }  
            catch (SQLException e)  
            {  
                e.printStackTrace();  
            }  
        return result;
    }
    public void close(){
    	
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
   
}
