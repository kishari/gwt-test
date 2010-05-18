package hu.dbx.gwt.test.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("test")
public interface TestService extends RemoteService {
	String callServer(String databaseInfo) throws IllegalArgumentException;
}
