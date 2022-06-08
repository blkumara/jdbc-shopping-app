package com.ty.shopping.Controller;

import com.ty.shopping.dao.UserDao;
import com.ty.shopping.dto.User;

public class TestAuthantication {
	public static void main(String[] args) {
		
		UserDao dao=new UserDao();
		User user= dao.validateUser("pavan@gmail.com", "pavan123");
		
		if(user !=null)
		{
			System.out.println("Name Is:"+user.getName());
			System.out.println("Mobile Is:"+user.getMobile());
			System.out.println("Email Is "+user.getEmail());
		}
		else
		{
			System.out.println("Invalid Email Or password");
		}
	}
}
