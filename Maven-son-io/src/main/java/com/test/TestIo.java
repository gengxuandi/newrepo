package com.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bean.User;
import com.utils.JdbcUtils;

public class TestIo {

	public static void main(String[] args) {
		try {
		System.out.println("***************");
        System.out.println("*1：查看文件数据                   *");
        System.out.println("*2：查看数据库数据                   *");
        System.out.println("*3：写入数据库                    *");
        System.out.println("*4：退出                    *");
        System.out.println("***************");
        
        System.out.println("您想选择的操作是：");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();        
        switch(choice){
        case 1:
        	toread();
            break;
        case 2:
        	Demo1.getUser();
            break;
        case 3:
        	tobase();
            break;
        case 4:
        	System.exit(0);
            break;
        default:
            System.out.println("您输入的数字有误，请重新输入：");
            break;
        }
	} catch (Exception e) {
		e.printStackTrace();
	}
    }
	/*
	 * 读取数据库并将数据写入文件
	 */
	public static void towrite() throws Exception {
		List<User> list = Demo1.getUser();
		FileOutputStream outStream = new FileOutputStream("D://user.txt");
		ObjectOutputStream objstream = new ObjectOutputStream(outStream);
		objstream.writeObject(list);
		objstream.close();
		outStream.close();
	}
	/*
	 * 读取文件
	 */
	public static List<User>  toread() throws Exception {
		List<User> list = new ArrayList<>();
		FileInputStream inputStream = new FileInputStream("D://user.txt");
		ObjectInputStream objstream = new ObjectInputStream(inputStream);
		list = (List<User>) objstream.readObject();
		System.out.println(list);
		return list ;
	}
	/*
	 * 写入数据库
	 */
	public static void tobase() throws Exception {
		List<User> list = toread();
		Connection conn = JdbcUtils.getConnection();
		String sql = "insert into user values(?,?,?)";
		PreparedStatement pre = conn.prepareStatement(sql);
		for(User u : list) {
			pre.setString(1, u.getTeanname());
			pre.setString(2, u.getName());
			pre.setString(3, u.getInfo());
			pre.executeUpdate();
		}
		conn.close();
		pre.close();
	}
}
