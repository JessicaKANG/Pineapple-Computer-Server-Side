package com.Pineapple.Dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
    private String id;// 编号
    private double price;
    private Timestamp datetime;
    private String state;
    private String client;
    private String payment;
    private String delivery;
    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public Timestamp getDatetime() {
        return datetime;
    }
    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public String getDelivery() {
        return delivery;
    }
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

}

