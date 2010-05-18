package hu.dbx.gwt.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
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

	private Button sendButton;
	private Button connectButton;
	private Button closeButton;
	private TextBox userNameField;
	private TextBox databaseNameField;
	private PasswordTextBox passwdField;
	private ListBox hostList;
	private Label textToServerLabel;
	private HTML serverResponseLabel;
	private VerticalPanel dialogVPanel;
	private HorizontalSplitPanel mainPanel;
	
	private DialogBox dialogBox;
	
	public void onModuleLoad() {
		
		init();
		createDialogBox();
		
	}
	
	private void init() {
		sendButton = new Button("Send");
		// We can add style names to widgets
		sendButton.addStyleName("button");
		sendButton.setEnabled(false);
		sendButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendRecord();
			}
		});
		
		connectButton = new Button("Connect");
		connectButton.addStyleName("button");
		connectButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				connectToServer();
			}
		});
		
		userNameField = new TextBox();
		userNameField.setText("root");
		
		databaseNameField = new TextBox();
		databaseNameField.setText("myDataBase");
		
		passwdField = new PasswordTextBox();
		passwdField.setText("root");
		
		hostList = new ListBox();
		hostList.addItem("localhost", "localhost:3306");
		hostList.addItem("egyéb(nem használt)", "");
		
		Label userNameLabel = new Label();
		userNameLabel.setText("user: ");
		Label passwdLabel = new Label();
		passwdLabel.setText("pass: ");
		Label dbNameLabel = new Label();
		dbNameLabel.setText("database name: ");
		
		FlowPanel connectPanel = new FlowPanel();
		connectPanel.add(userNameLabel);
		connectPanel.add(userNameField);
		connectPanel.add(passwdLabel);
		connectPanel.add(passwdField);
		connectPanel.add(dbNameLabel);
		connectPanel.add(databaseNameField);
		connectPanel.add(hostList);
		connectPanel.add(connectButton);
		
		TextBox name = new TextBox();
		TextBox age = new TextBox();
		Label nameLabel = new Label();
		nameLabel.setText("name:");
		Label ageLabel = new Label();
		ageLabel.setText("age:");
		FlowPanel dataPanel = new FlowPanel();
		dataPanel.add(nameLabel);
		dataPanel.add(name);
		dataPanel.add(ageLabel);
		dataPanel.add(age);
		dataPanel.add(sendButton);
		
		mainPanel = new HorizontalSplitPanel();
		mainPanel.setSize("500px", "200px");
		mainPanel.setSplitPosition("250px");
		mainPanel.setLeftWidget(connectPanel);
		mainPanel.setRightWidget(dataPanel);
		
		RootPanel.get("container").add(mainPanel);

	}
	
	private void createDialogBox() {
		dialogBox = new DialogBox();
		
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		closeButton = new Button("Close");
		
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		textToServerLabel = new Label();
		
		serverResponseLabel = new HTML();
		dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				connectButton.setEnabled(true);
				connectButton.setFocus(true);
			}
		});
	}
	
	private void connectToServer() {
		String textToServer = userNameField.getText() + "\n";
		textToServer = textToServer + passwdField.getText() + "\n";
		textToServer = textToServer + databaseNameField.getText() + "\n";
		textToServer = textToServer + hostList.getValue(hostList.getSelectedIndex());

		// Then, we send the input to the server.
		connectButton.setEnabled(false);
		textToServerLabel.setText(textToServer);
		serverResponseLabel.setText("");
		testService.callServer(textToServer,
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox
								.setText("Remote Procedure Call - Failure");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
	}
	
	private void sendRecord() {
		//Itt fogja küldeni az adatokat az adatbázisba!
	}
}
