package hu.dbx.gwt.test.client;

import hu.dbx.gwt.test.shared.ProductInfo;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("test")
public interface TestService extends RemoteService {
	String connectToDataBase(String databaseInfo) throws IllegalArgumentException;
	List<ProductInfo> getProducts() throws IllegalArgumentException;
}
