package hu.dbx.gwt.test.client.component;

import hu.dbx.gwt.test.shared.ProductInfo;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class ProductTable extends Composite {

	private static ProductTableUiBinder uiBinder = GWT
			.create(ProductTableUiBinder.class);

	interface ProductTableUiBinder extends UiBinder<Widget, ProductTable> {
	}

	interface SelectionStyle extends CssResource {
	    String selectedRow();
	}
	
	@UiField
	FlexTable productTable;
	
	@UiField 
	SelectionStyle selectionStyle;

	private int selectedRow = -1;
	
	public ProductTable() {
		initWidget(uiBinder.createAndBindUi(this));
		productTable.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				String style = selectionStyle.selectedRow();
				if (selectedRow > 0) {					
					productTable.getRowFormatter().removeStyleName(selectedRow, style);
					productTable.getRowFormatter().addStyleName(selectedRow, "productTableRow");
				}	
				selectedRow = productTable.getCellForEvent(event).getRowIndex();
				if (selectedRow > 0) {
					productTable.getRowFormatter().removeStyleName(selectedRow, "productTableRow");
					productTable.getRowFormatter().addStyleName(selectedRow, style);
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
