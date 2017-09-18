package com.yan.LibrarySystem.mapper;


import com.yan.LibrarySystem.po.Admin;



public interface AdminMapper {
 
	public Admin findAdminByUserName(String username) throws Exception;
	
	public void changePassword(Admin admin) throws Exception;
	
	
	
}
