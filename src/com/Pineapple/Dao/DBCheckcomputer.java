package com.Pineapple.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.Pineapple.Dao.model.Component;
import com.Pineapple.Dao.model.Computer;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBCheckcomputer implements DBConfig{
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
     * 查询全部商品信息
     * SELECT * FROM tb_computer;
     * @return："list<JavaBean>"
     */
    public static List<Computer> select() {
    	//////////////////////////////////////////解决映射问题////////////////////////////////////////////
    	// BeanProcessor 有两个构造方法，可以传入一个HashMap集合
    	 // HashMap 规定了表字段映射到Javabean的哪个属性，即key为字段名称，value为对应的javabean属性
    	 // map.put(表字段名称, Javabean属性名称)
    	 Map<String, String> map = new HashMap<String, String>();
    	 map.put("id_computer", "id");
    	 map.put("name_computer", "name");
    	 map.put("type_computer", "type");
    	 map.put("price_computer", "price");
    	 map.put("picture_computer", "picture");
    	 // 用构建好的HashMap建立一个BeanProcessor对象
    	 BeanProcessor bean = new BeanProcessor(map);
    	 RowProcessor processor = new BasicRowProcessor(bean);
    	 //Users rs = runner.query(sql, new BeanHandler<Users>(Users.class, processor));
    	 
    	 Map<String, String> map2 = new HashMap<String, String>();
    	 map2.put("id_component", "id");
    	 map2.put("name_component", "name");
    	 map2.put("type_component", "type");
    	 map2.put("price_component", "price");
    	 // 用构建好的HashMap建立一个BeanProcessor对象
    	 BeanProcessor bean2 = new BeanProcessor(map2);
    	 RowProcessor processor2 = new BasicRowProcessor(bean2);
    	 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////	
    	
    	
        QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
        String sql = "select * from tb_computer;";// 定义查询语句
        Connection conn = getConnection();// 获得连接
        ResultSetHandler<List<Computer>> rsh = new BeanListHandler<Computer>(Computer.class,processor);// 创建结果集处理类
        try {
            List<Computer> result = (List<Computer>)runner.query(conn, sql, rsh);// 获得查询结果
            Iterator iterator = result.iterator();
            while (iterator.hasNext()) {
    			Computer computer = (Computer) iterator.next();
    			String sql2 = "select id_component from tb_computer_has_component "
    					+ "where id_computer = '"+computer.getId()+"';";
    			ResultSetHandler<List<String>> rsh2 = new ColumnListHandler();;
    			List<String>result2 = (List<String>)runner.query(conn,sql2,rsh2);
    			Iterator iterator2 = result2.iterator();
    			while (iterator2.hasNext()){
    				String idcomponent = (String) iterator2.next();
    				String sql3 ="select * from tb_component"
    					+ " where id_component ='"+idcomponent+"';";
    				ResultSetHandler<Component> rsh3 = new BeanHandler<Component>(Component.class,processor2);   				
    				Component result3 = (Component) runner.query(conn, sql3, rsh3);
    				if(result3.getType().equals("Stock")){
    					computer.setStock(result3.getName());
    				}
    				if(result3.getType().equals("Memory")){
    					computer.setMemory(result3.getName());
    				}
    				if(result3.getType().equals("Color")){
    					computer.setColor(result3.getName());
    				}
    				if(result3.getType().equals("Screen")){
    					computer.setSize(result3.getName());
    				}
    				if(result3.getType().equals("Graphics")){
    					computer.setGraphic(result3.getName());
    				}
    				if(result3.getType().equals("Processor")){
    					computer.setProcessor(result3.getName());
    				}
    				
    			}
    			
            }
          
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// 关闭连接
        }
        return null;// 如果发生异常返回null
    }
    
 
}
