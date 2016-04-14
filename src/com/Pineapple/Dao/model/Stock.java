package com.Pineapple.Dao.model;

public class Stock {
	private String id;// 型号
    private String idcpr;// 电脑存货
    private String idcpt;// 配件存货
    private int number;// 库存量
  //获取id方法
    public String getId() {
        return id;
    }
    //设置id方法
    public void setId(String id) {
        this.id = id;
    }
    
  
    public String getIdcpr() {
        return idcpr;
    }
   
    public void setIdcpr(String idcpr) {
        this.idcpr = idcpr;
    }
    
 
    public String getIdcpt() {
        return idcpt;
    }
  
    public void setIdcpt(String idcpt) {
        this.idcpt = idcpt;
    }
    
 
    public int getNumber() {
        return number;
    }
   
    public void setNumber(int number) {
        this.number = number;
    }


}
