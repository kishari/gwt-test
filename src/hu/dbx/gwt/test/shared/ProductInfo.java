package hu.dbx.gwt.test.shared;

import java.io.Serializable;

public class ProductInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String productCode;
	private String coverCode;
	private int version;
	private String description;
	
	public ProductInfo() {}
	
	public ProductInfo(String productCode, String coverCode, int version, String description) {
		this.productCode = productCode;
		this.coverCode = coverCode;
		this.version = version;
		this.description = description;
		
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCoverCode() {
		return coverCode;
	}
	public void setCoverCode(String coverCode) {
		this.coverCode = coverCode;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
