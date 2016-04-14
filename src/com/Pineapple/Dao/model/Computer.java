package com.Pineapple.Dao.model;

public class Computer {
	private String id;// 型号
    private String name;// 商品名
    private String type;// 商品类型
    private float price;// 商品价格 
    private String picture;// 商品图片地址
    
    
    
    //获取id方法
    public String getId() {
        return id;
    }
    //设置id方法
    public void setId(String id) {
        this.id = id;
    }
    
  //获取name方法
    public String getName() {
        return name;
    }
    //设置name方法
    public void setName(String name) {
        this.name = name;
    }
    
  //获取type方法
    public String getType() {
        return type;
    }
    //设置type方法
    public void setType(String type) {
        this.type = type;
    }
    
  //获取price方法
    public float getPrice() {
        return price;
    }
    //设置price方法
    public void setPrice(float price) {
        this.price = price;
    }
    
  //获取picture方法
    public String getPicture() {
        return picture;
    }
    //设置picture方法
    public void setPicture(String picture) {
        this.picture = picture;
    }

}
