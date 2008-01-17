package org.xmlcrm.app.hibernate.beans.shop;

public class products {
	private int artnumber;
	private String category;
	private String title;
	private String description;
	private String price; 
	private String priceold;
	private String currency;
	private String imgurl;	
	private String deeplink1;	
	private String manufacturer;
	public products() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getArtnumber() {
		return artnumber;
	}
	public void setArtnumber(int artnumber) {
		this.artnumber = artnumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDeeplink1() {
		return deeplink1;
	}
	public void setDeeplink1(String deeplink1) {
		this.deeplink1 = deeplink1;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceold() {
		return priceold;
	}
	public void setPriceold(String priceold) {
		this.priceold = priceold;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
