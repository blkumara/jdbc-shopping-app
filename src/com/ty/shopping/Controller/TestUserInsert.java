package com.ty.shopping.Controller;

import com.ty.shopping.dao.UserDao;
import com.ty.shopping.dto.User;

public class TestUserInsert {
		public static void main(String[] args) {
			
		
	User user=new User();
	UserDao dao=new UserDao();
	
	user.setId(3);
	user.setName("sneha");
	user.setEmail("sneha@gmail.com");
	user.setPassword("vsneha");
	user.setMobile(7483249668l);
		
		
//		UserDao  dao=new UserDao();
//		
//		int res=dao.saveUser(user);
//		
//		if(res>0)
//		{
//			System.out.println("Data Inserted");
//		}
//		else
//		{
//			System.out.println("No data inserted");
//		}
//		}
	
	//dao.updateUser(user);
	
	dao.deleteUserById(1);
		}
		
		
}
