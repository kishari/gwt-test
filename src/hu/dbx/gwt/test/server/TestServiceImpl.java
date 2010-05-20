package hu.dbx.gwt.test.server;

import java.util.Iterator;
import java.util.List;

import hu.dbx.gwt.test.client.TestService;
import hu.dbx.gwt.test.shared.ProductInfo;
import hu.dbx.gwt.test.util.DBUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TestServiceImpl extends RemoteServiceServlet implements
		TestService {

	public String connectToDataBase(String databaseInfo) throws IllegalArgumentException {
		DBUtil.init(databaseInfo);
		boolean result = DBUtil.connect();
		String response = new String();
		response = "You call the service with:<br> " + databaseInfo + "<br>result: " + result;
		
		return response;
	}
	
	public List<ProductInfo> getProducts() throws IllegalArgumentException {
		
		
		List<ProductInfo> info = DBUtil.executeSelect(DBUtil.Select.GET_PRODUCTS);
		if (info == null) {
			System.out.println("lekérdezés null eredmény");
		}
		Iterator i = info.iterator();
		while (i.hasNext()) {
			ProductInfo temp = (ProductInfo) i.next();
			System.out.println(temp.getProductCode() +" " +temp.getDescription());
			
		}
		
		return info;
	}
}
