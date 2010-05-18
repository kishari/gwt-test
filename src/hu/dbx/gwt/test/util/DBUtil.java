package hu.dbx.gwt.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
 // selects
    private static final String GET_CONTACTS = "select Name, Age from myDataBase.templates";
    
    private String databaseInfo;
    
    public DBUtil(String databaseInfo) {
    	this.databaseInfo = databaseInfo;
    }
 
    public List<String> getRows() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> ret = new ArrayList<String>();
        try {
				conn = getConnection(databaseInfo);
			
            if (conn == null) {
            	ret.add("Connection refused!");
            	return ret;
            }
    
            pstmt = conn.prepareStatement(GET_CONTACTS);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	String temp = rs.getString(1) + " " + rs.getInt(2);
            	ret.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException ignore) {
                }
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
        }
        
        return ret;
    }
    
    private Connection getConnection(String databaseInfo) throws SQLException {
    	Connection conn = null;
    	
    	String [] data;
    	data = databaseInfo.split("\n");
    	
    	String user = data[0];
        String pass = data[1];
        String db = data[2];
        String hostport = data[3];
//        hostport = "localhost";
        String url = "jdbc:mysql://" + hostport + "/";
        url = url + db;
        String driver = "com.mysql.jdbc.Driver";
        System.out.println(url);
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) { }
        
        return conn;
    }

}
