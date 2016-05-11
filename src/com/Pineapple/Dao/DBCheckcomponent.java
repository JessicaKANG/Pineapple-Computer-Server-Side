package com.Pineapple.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.Pineapple.Dao.model.Component;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBCheckcomponent implements DBConfig{
	/**
     * 使用MySQL数据源获得数据库连接对象
     *
     * @return：MySQL连接对象，如果获得失败返回null
     */
    public static Connection getConnection() {
        MysqlDataSource mds = new MysqlDataSource();// 创建MySQL数据源（此方法来自于mysql的插件）
        //与DBConfig接口对接
        mds.setDatabaseName(databaseName);// 设置数据库名称
        mds.setUser(username);// 设置数据库用户名
        mds.setPassword(password);// 设置数据库密码
        try {
            return mds.getConnection();// 获得连接（返回值是一个数据库连接对象）
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;// 如果获取失败就返回null
    }
    
    /**
     * 查询全部配件信息
     * SELECT * FROM tb_component;
     * @return："list<JavaBean>"
     */
    public static List<Component> select() {
    	//////////////////////////////////////////解决映射问题////////////////////////////////////////////
    	// BeanProcessor 有两个构造方法，可以传入一个HashMap集合
    	 // HashMap 规定了表字段映射到Javabean的哪个属性，即key为字段名称，value为对应的javabean属性
    	 // map.put(表字段名称, Javabean属性名称)
    	 Map<String, String> map = new HashMap<String, String>();
    	 map.put("id_component", "id");
    	 map.put("name_component", "name");
    	 map.put("type_component", "type");
    	 map.put("price_component", "price");
    	 // 用构建好的HashMap建立一个BeanProcessor对象
    	 BeanProcessor bean = new BeanProcessor(map);
    	 RowProcessor processor = new BasicRowProcessor(bean);
    	 //Users rs = runner.query(sql, new BeanHandler<Users>(Users.class, processor));
    ///////////////////////////////////////////////////////////////////////////////////////////////////////	
    	
    	
        QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
        String sql = "select * from tb_component;";// 定义查询语句
        Connection conn = getConnection();// 获得连接
        ResultSetHandler<List<Component>> rsh = new BeanListHandler<Component>(Component.class,processor);// 创建结果集处理类
        try {
            List<Component> result = (List<Component>)runner.query(conn, sql, rsh);// 获得查询结果
          
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// 关闭连接
        }
        return null;// 如果发生异常返回null
    }

	public static String getComponentID(String componentName) {
		 QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
	     String sql = "select id_component from tb_component where name_component = '"+ componentName+"';";// 定义查询语句
	     Connection conn = getConnection();// 获得连接
	     //ResultSetHandler<String> rsh = new ColumnListHandler();// 创建结果集处理类
	     String componentID = null;
		try {
			componentID = runner.query(conn, sql, new ScalarHandler<String>());
			return componentID;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	public static String getComponentType(String componentName) {
		 QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
	     String sql = "select type_component from tb_component where name_component = '"+ componentName+"';";// 定义查询语句
	     Connection conn = getConnection();// 获得连接
	     //ResultSetHandler<String> rsh = new ColumnListHandler();// 创建结果集处理类
	     String componentType = null;
		try {
			componentType = runner.query(conn, sql, new ScalarHandler<String>());
			return componentType;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	public static double getComponentPrice(String componentName) {
		 QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
	     String sql = "select price_component from tb_component where name_component = '"+ componentName+"';";// 定义查询语句
	     Connection conn = getConnection();// 获得连接
	     //ResultSetHandler<String> rsh = new ColumnListHandler();// 创建结果集处理类
	     double componentPrice = 0;
		try {
			componentPrice = runner.query(conn, sql, new ScalarHandler<Double>());
			return componentPrice;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return 0;
		}
	}
	public static List<String> getComponentNameList(String type){
		 QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
	     String sql = "select name_component from tb_component where type_component = '"+type+"';";// 定义查询语句
	     Connection conn = getConnection();// 获得连接
	     ResultSetHandler<List<String>> rsh = new ColumnListHandler<String>();// 创建结果集处理类
	     
		try {
			List<String> result = (List<String>)runner.query(conn, sql, rsh);// 获得查询结果
			return result;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}
	
	public static double getnewPrice(String itemname,String precomponentname,double price){
		String type_component = DBCheckcomponent.getComponentType(itemname);
		double price_component = DBCheckcomponent.getComponentPrice(itemname);
		
		double pre_price = DBCheckcomponent.getComponentPrice(precomponentname);
		double newPrice = 0;				
			 newPrice = price + (price_component - pre_price);
		
		
		return newPrice;
	}

}
