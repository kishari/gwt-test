package hu.dbx.gwt.test.client.component;

import hu.dbx.gwt.test.shared.ProductInfo;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;

public class MainWidget extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT.create(MainWidgetUiBinder.class);
	
	interface MainWidgetUiBinder extends UiBinder<Widget, MainWidget> { }

	@UiField 
	DeckPanel parentPanel;
	
	@UiField
	FormPanel xslUploadForm;
	
	@UiField
	FileUpload xslFileUpload;
	
	@UiField
	TextBox xslName;
	
	@UiField
	TextArea xslDescription;
	
	@UiField
	Button newButton;
	
	@UiField
	Button cancelButton;
	
	@UiField
	Button cancelButton2;
	
	@UiField
	Button editButton;
	
	@UiField
	Button saveButton;
	
	@UiField
	Button saveButton2;
	
	@UiField
	Button testButton;
	
	@UiField
	Button addNewVersionButton;
	
	@UiField
	Button removeVersionButton;

	@UiField
	ProductTable productTable;
	
	public MainWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		xslUploadForm.setAction(GWT.getModuleBaseURL() + "FileUploadServlet");
		xslUploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		xslUploadForm.setMethod(FormPanel.METHOD_POST);
		
		init();	
	}
	
	private void init() {
		setButtonClickHandler();
		setUploadFormHandler();
		
		parentPanel.showWidget(0);
	}
	private void setButtonClickHandler() {
		
		newButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentPanel.showWidget(parentPanel.getVisibleWidget() + 1);
			}
		});
		
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentPanel.showWidget(parentPanel.getVisibleWidget() - 1);
			}
		});
		
		cancelButton2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentPanel.showWidget(parentPanel.getVisibleWidget() - 1);
			}
		});
		
		editButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentPanel.showWidget(parentPanel.getVisibleWidget() + 1);
			}
		});
		
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			}
		});
		
		saveButton2.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				xslUploadForm.submit();
			}
		});
		
		testButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			}
		});
		
		addNewVersionButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			}
		});
		
		removeVersionButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			}
		});
		
	}
	
	private void setUploadFormHandler() {
		xslUploadForm.addSubmitHandler(new SubmitHandler() {
			@Override
			public void onSubmit(SubmitEvent event) {
				if ("".equals(xslName.getText()) ||
					"".equals(xslDescription.getText()) ||
					"".equals(xslFileUpload.getFilename())) {
						Window.alert("Minden mező kitöltése kötelező!");
						event.cancel();
				}
			}
	    });
	    
		xslUploadForm.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				String temp = event.getResults();
				temp = temp.substring("<pre>".length(), temp.length() - "</pre>".length());
		        Window.alert(temp);
		        
			}
		});
	}
	
	public void populateTable(List<ProductInfo> r) {
		productTable.populateProductTable(r);
	}

}
