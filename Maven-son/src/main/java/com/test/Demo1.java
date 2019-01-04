package com.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.User;
import com.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Demo1 {
   
    public static void main(String[] args){
    	try {
    		System.out.println("user表数据：");
			System.out.println(getUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static List<User> getUser() throws Exception{
        Connection conn=JdbcUtils.getConnection();
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from user");
        //4.处理数据库的返回结果(使用ResultSet类)
        List<User> list = new ArrayList<>();
        while(rs.next()){
            String teanname = rs.getString("组名");
            String name = rs.getString("成员名称");
            String info = rs.getString("备注");	
            User user = new User();
            user.setTeanname(teanname);
            user.setName(name);
            user.setInfo(info);
            list.add(user);
        }
        
        //关闭资源
        JdbcUtils.colseResource(conn, st, rs);
        System.out.println(list);
		return list;
    }
}