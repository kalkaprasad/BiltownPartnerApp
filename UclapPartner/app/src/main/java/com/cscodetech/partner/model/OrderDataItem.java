package com.cscodetech.partner.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrderDataItem implements Parcelable {

	@SerializedName("p_method_name")
	private String pMethodName;

	@SerializedName("customer_address")
	private String customerAddress;

	@SerializedName("Order_Status")
	private String orderStatus;

	@SerializedName("customer_mobile")
	private String customerMobile;

	@SerializedName("add_total")
	private String addTotal;

	@SerializedName("Order_Total")
	private String orderTotal;

	@SerializedName("sign")
	private String sign;

	@SerializedName("order_time")
	private String orderTime;

	@SerializedName("Order_Product_Data")
	private ArrayList<OrderProductDataItem> orderProductData;

	@SerializedName("order_date")
	private String orderDate;

	@SerializedName("customer_email")
	private String customerEmail;

	@SerializedName("order_dateslot")
	private String orderDateslot;

	@SerializedName("Order_Transaction_id")
	private String orderTransactionId;

	@SerializedName("add_on_data")
	private ArrayList<AddOnDataItem> addOnData;

	@SerializedName("Order_SubTotal")
	private String orderSubTotal;

	@SerializedName("customer_name")
	private String customerName;

	@SerializedName("order_id")
	private String orderId;

	@SerializedName("r_credit")
	private String rCredit;

	@SerializedName("lats")
	private double  lats;

	@SerializedName("longs")
	private double  longs;

	protected OrderDataItem(Parcel in) {
		pMethodName = in.readString();
		customerAddress = in.readString();
		orderStatus = in.readString();
		customerMobile = in.readString();
		addTotal = in.readString();
		orderTotal = in.readString();
		sign = in.readString();
		orderTime = in.readString();
		orderDate = in.readString();
		customerEmail = in.readString();
		orderDateslot = in.readString();
		orderTransactionId = in.readString();
		orderSubTotal = in.readString();
		customerName = in.readString();
		orderId = in.readString();
		rCredit = in.readString();
		lats = in.readDouble();
		longs = in.readDouble();
	}

	public static final Creator<OrderDataItem> CREATOR = new Creator<OrderDataItem>() {
		@Override
		public OrderDataItem createFromParcel(Parcel in) {
			return new OrderDataItem(in);
		}

		@Override
		public OrderDataItem[] newArray(int size) {
			return new OrderDataItem[size];
		}
	};

	public String getPMethodName(){
		return pMethodName;
	}

	public String getCustomerAddress(){
		return customerAddress;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public String getCustomerMobile(){
		return customerMobile;
	}

	public String getAddTotal(){
		return addTotal;
	}

	public String getOrderTotal(){
		return orderTotal;
	}

	public String getSign(){
		return sign;
	}

	public String getOrderTime(){
		return orderTime;
	}

	public ArrayList<OrderProductDataItem> getOrderProductData(){
		return orderProductData;
	}

	public String getOrderDate(){
		return orderDate;
	}

	public String getCustomerEmail(){
		return customerEmail;
	}

	public String getOrderDateslot(){
		return orderDateslot;
	}

	public String getOrderTransactionId(){
		return orderTransactionId;
	}

	public ArrayList<AddOnDataItem> getAddOnData(){
		return addOnData;
	}

	public String getOrderSubTotal(){
		return orderSubTotal;
	}

	public String getCustomerName(){
		return customerName;
	}

	public String getOrderId(){
		return orderId;
	}

	public String getRCredit(){
		return rCredit;
	}

	public double  getLats() {
		return lats;
	}

	public void setLats(long lats) {
		this.lats = lats;
	}

	public double  getLongs() {
		return longs;
	}

	public void setLongs(long longs) {
		this.longs = longs;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(pMethodName);
		dest.writeString(customerAddress);
		dest.writeString(orderStatus);
		dest.writeString(customerMobile);
		dest.writeString(addTotal);
		dest.writeString(orderTotal);
		dest.writeString(sign);
		dest.writeString(orderTime);
		dest.writeString(orderDate);
		dest.writeString(customerEmail);
		dest.writeString(orderDateslot);
		dest.writeString(orderTransactionId);
		dest.writeString(orderSubTotal);
		dest.writeString(customerName);
		dest.writeString(orderId);
		dest.writeString(rCredit);
		dest.writeDouble(lats);
		dest.writeDouble(longs);
	}
}