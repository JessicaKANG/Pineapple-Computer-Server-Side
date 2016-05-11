package com.Pineapple.Dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.Pineapple.Dao.model.Component;
import com.Pineapple.Dao.model.Order;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBAddorder implements DBConfig{
	private static int id_client;
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
     * 保存用户输入的订单信息
     *
     * @return：如果保存成功返回true，保存失败返回false
     */
    public static boolean save(Order order) {
        QueryRunner runner = new QueryRunner();// 创建QueryRunner对象
        String sql = "insert into tb_order (id_order,price_order,date_order,state_order,id_client,payment,delivery) values (?, ?, ?, ?,?,?,?);";// 定义查询语句
        Connection conn = getConnection();// 获得连接
        String sql2 = "select id_client from tb_client where name_client = '"+order.getClient()+"';";
        try {
			 id_client = runner.query(conn, sql2, new ScalarHandler<Integer>());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// 获得查询结果
        Object[] params = { order.getID(),order.getPrice(),order.getDatetime(),order.getState(),id_client,order.getPayment(),order.getDelivery()};// 获得传递的参数
        try {
            int result = runner.update(conn, sql, params);// 保存用户
            if (result > 0) {// 如果保存成功返回true
                return true;
            } else {// 如果保存失败返回false
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);// 关闭连接
        }
        return false;// 如果发生异常返回false
    }

}
