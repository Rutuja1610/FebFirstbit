package com.bean;

//data manager
public class User {
	
	private String username,password,sqn,sqa;
	
	public User(String username,String password,String sqn,String sqa) {
		this.username=username;
		this.password=password;
		this.sqn=sqn;
		this.sqa=sqa;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getSqn() {
		return sqn;
	}
	
	public String getSqa() {
		return sqa;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean verifySecurityAnswer(String sqa) {
        return this.sqn.equals(sqa);
    }

    // Password update methods
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public boolean verifyPassword(String oldPassword) {
        return this.password.equals(oldPassword);
    }
	
	
	

}
