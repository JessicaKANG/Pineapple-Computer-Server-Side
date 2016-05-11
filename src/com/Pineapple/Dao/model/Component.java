package com.Pineapple.Dao.model;

public class Component {
	private String id;// 型号
    private String name;// 名称
    private String type;// 类型
    private double price;// 价格 
    
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
    public double getPrice() {
        return price;
    }
    //设置price方法
    public void setPrice(double price) {
        this.price = price;
    }

}
