package hu.dbx.gwt.test.server;

import java.util.Iterator;
import java.util.List;

import hu.dbx.gwt.test.client.TestService;
import hu.dbx.gwt.test.util.DBUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TestServiceImpl extends RemoteServiceServlet implements
		TestService {

	public String callServer(String databaseInfo) throws IllegalArgumentException {
		DBUtil db = new DBUtil(databaseInfo);
		List<String> info = db.getRows();
		
		String response = new String();
		response = "You call the service with:<br> " + databaseInfo +
		   "<br>There is the response: <br>";
		
		Iterator<String> i = info.iterator();
		while (i.hasNext()) {
			String next = (String) i.next();
			response = response + next + "<br>";
		}
		
		return response;
	}
}
