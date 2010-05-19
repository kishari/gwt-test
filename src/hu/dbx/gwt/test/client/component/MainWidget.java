package hu.dbx.gwt.test.client.component;

import hu.dbx.gwt.test.shared.ProductInfo;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

public class MainWidget extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT
			.create(MainWidgetUiBinder.class);
	
	interface MainWidgetUiBinder extends UiBinder<Widget, MainWidget> { }

	private int selected = -1;
	@UiField 
	DeckPanel parentPanel;
	
	@UiField
	DockLayoutPanel mainPanel;
	
	//@UiField
	DockLayoutPanel secondPanel;
	
	@UiField
	DockLayoutPanel thirdPanel;
	
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

//	@UiField 
	Tree productList;
	
	@UiField
	FlexTable productTable;
	
	public MainWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();	
	}
	
	private void init() {
		setButtonClickHandler();
		
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
		
		saveButton2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
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
		
		productTable.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (selected > 0) {
					productTable.getRowFormatter().removeStyleName(selected,"productTableSelected");
					productTable.getRowFormatter().addStyleName(selected,"productTableRow");
				}
				selected = productTable.getCellForEvent(event).getRowIndex();
				if (selected != 0) {
					productTable.getRowFormatter().addStyleName(selected,"productTableSelected");
					productTable.getRowFormatter().removeStyleName(selected,"productTableRow");
				}
			}
			
		});
	}
	
	public void populateProductTable(List<ProductInfo> products) {
		if (products != null) {
			Iterator<ProductInfo> i = products.iterator();
			int row = 0;
			productTable.setStyleName("productTable");
			productTable.getRowFormatter().addStyleName(0, "productTableHeader");
			productTable.setText(row, 0, "ProductCode");
			productTable.setText(row, 1, "Description");

			
			row++;
			while(i.hasNext()) {
				ProductInfo p = i.next();
				int col = 0;
				productTable.getRowFormatter().addStyleName(row, "productTableRow");
				productTable.setText(row, col++, p.getProductCode());
				productTable.setText(row, col++, p.getDescription());
				row++;
			}
		}
	}

}
