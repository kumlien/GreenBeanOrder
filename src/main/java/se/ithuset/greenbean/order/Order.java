package se.ithuset.greenbean.order;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Stooopid class acting as a model in our mvc.
 * 
 * @author svante
 */

@XmlRootElement(name="order")
public class Order {
	
	private String id;
	private String productId;
	private Integer qty;
	private String customerName;
	private String customerEmail;
	private Double price = 0.0;
	
	
	
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Order [productId=" + productId + ", qty=" + qty
				+ ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", price=" + (price!=0.0?price:"not set yet") + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	
}
