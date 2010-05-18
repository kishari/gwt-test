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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWidget extends Composite {

	private static MainWidgetUiBinder uiBinder = GWT
			.create(MainWidgetUiBinder.class);

	interface MainWidgetUiBinder extends UiBinder<Widget, MainWidget> { }

	@UiField 
	DeckPanel parentPanel;
	
	@UiField
	DockLayoutPanel firstPanel;
	
	@UiField
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

	@UiField 
	Tree productList;
	
	public MainWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
		Tree t = new Tree();
		
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
	}
	
	public void populateProductList(List<ProductInfo> products) {
		if (products != null) {
			Iterator<ProductInfo> i = products.iterator();
			TreeItem t = new TreeItem("Termékek");
			while(i.hasNext()) {
				ProductInfo temp = (ProductInfo) i.next();
				t.addItem(temp.getProductCode());
				productList.addItem(t);
				System.out.println(temp.getProductCode());
			}
		}
	}

}
