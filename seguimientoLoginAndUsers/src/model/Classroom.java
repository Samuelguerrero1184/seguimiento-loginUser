package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	
	private List<UserAccount>userAcc;
	
	public Classroom () {
		userAcc = new ArrayList<>();
	}
	
	public void addAccount(String user, String pass, String gender, String career) {
		userAcc.add(new UserAccount(user,pass,gender,career));
	}

	public List<UserAccount> getUserAcc(){
		return userAcc;
	}
}
