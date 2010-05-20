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
	
	public static enum Select {
		GET_PRODUCTS
	}
 // selects
    private static final String GET_PRODUCTS = "select name, description from flipfop.templates";
    
    private static String databaseInfo;
    
    private static Connection connection = null;
    
    public static void init(String dbInfo) {
    	databaseInfo = dbInfo;
    }
    
    public static boolean connect() {
    	try {
			connection = createConnection(databaseInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if (connection != null) {
        	return true;
    	}
    	return false;
    }
 
    public static List<ProductInfo> executeSelect(Select value) {
    	String query = new String();
    	switch(value) {
    		case GET_PRODUCTS:
    					query = GET_PRODUCTS;
    					break;
    		default:
    					break;
    	
    	}

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductInfo> ret = new ArrayList<ProductInfo>();
        
        if (connection == null) {    }
        
        try {
            pstmt = connection.prepareStatement(query);
            
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
    
    private static Connection createConnection(String databaseInfo) throws SQLException {
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
    
    public static Connection getConnection() {
    	return connection;
    }

}
