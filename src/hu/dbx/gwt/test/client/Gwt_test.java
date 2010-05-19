package hu.dbx.gwt.test.client;

import java.util.ArrayList;
import java.util.List;

import hu.dbx.gwt.test.client.component.MainWidget;
import hu.dbx.gwt.test.shared.ProductInfo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt_test implements EntryPoint {

	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final TestServiceAsync testService = GWT
			.create(TestService.class);

	private Button closeButton;
	private HTML serverResponseLabel;
	private VerticalPanel dialogVPanel;
	private MainWidget mainWindow;
	
	private DialogBox dialogBox;
	
	private List<ProductInfo> products;
	
	public void onModuleLoad() {
		
		init();
		connectToServer();
	}
	
	private void init() {
		createDialogBox();
		
		mainWindow = new MainWidget();
		RootPanel.get("container").add(mainWindow);

	}
	
	private void createDialogBox() {
		dialogBox = new DialogBox();
		
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		closeButton = new Button("Close");
		
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		
		serverResponseLabel = new HTML();
		dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				
			}
		});
	}
	
	private void connectToServer() {
		String databaseInfo = "root\nroot\nflipfop\nlocalhost:3306";
		
		System.out.println(databaseInfo);
		
		serverResponseLabel.setText("");
		testService.connectToDataBase(databaseInfo,
					  new AsyncCallback<String>() {
						public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
			/*			dialogBox.setText("Remote Procedure Call");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
				*/		getRows();
					}
				});
	}
	
	private void getRows() {
		
		testService.getProducts(new AsyncCallback<List<ProductInfo>>() {
						public void onFailure(Throwable caught) {
							System.out.println("HIBA");
					}

					public void onSuccess(List<ProductInfo> result) {
						if (result == null) {
							System.out.println("result null");
						}
						else {
							setProductList(result);
							mainWindow.populateTable(result);
						}
					}

				});
	}
	
	public void setProductList(List<ProductInfo> productList) {
		this.products = new ArrayList<ProductInfo>(productList);
	}
	
	public List<ProductInfo> getProductList() {
		return this.products;
	}
}
