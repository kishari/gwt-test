package hu.dbx.gwt.test.util;

import hu.dbx.gwt.test.shared.ProductInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
 // selects
    private static final String GET_CONTACTS = "select name, description from flipfop.templates";
    
    private String databaseInfo;
    
    private Connection connection = null;
    
    public DBUtil() {
    	
    }
    
    public boolean connect(String databaseInfo) {
    	try {
			connection = getConnection(databaseInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if (connection != null) {
        	return true;
    	}
    	return false;
    }
 
    public List<ProductInfo> getRows() {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductInfo> ret = new ArrayList<ProductInfo>();
        
        if (connection == null) {    }
        
        try {
        	
            pstmt = connection.prepareStatement(GET_CONTACTS);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	 ProductInfo temp = new ProductInfo();
            	 temp.setProductCode(rs.getString(1));
            	 temp.setDescription(rs.getString(2));
            	 
            	 ret.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException ignore) { }
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (SQLException ignore) {  }
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
