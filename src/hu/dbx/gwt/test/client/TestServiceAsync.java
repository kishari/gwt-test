package hu.dbx.gwt.test.client;

import hu.dbx.gwt.test.shared.ProductInfo;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>TestService</code>.
 */
public interface TestServiceAsync {
	void connectToDataBase(String databaseInfo, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void getProducts(AsyncCallback<List<ProductInfo>> callback)
		throws IllegalArgumentException;
	
}
