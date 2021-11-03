package DAO;

public class Admin {
	
	String userName, adminPassword;
	
	

	public Admin(String userName, String adminPassword) {
		this.userName = userName;
		this.adminPassword = adminPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	

}
