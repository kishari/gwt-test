package hu.dbx.gwt.test.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>TestService</code>.
 */
public interface TestServiceAsync {
	void callServer(String databaseInfo, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
