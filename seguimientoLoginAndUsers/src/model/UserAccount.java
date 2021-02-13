package model;

public class UserAccount {

	private String Username;
	private String Passcode;
	private String Gender;
	private String Career;
	


	public UserAccount(String username, String passcode, String gender, String career) {
		Username = username;
		Passcode = passcode;
		Gender = gender;
		Career = career;	
	}


	public String getUser() {
		return Username;
	}
	
	public String getPasscode() {
		return Passcode;
	}
	
	public String getGender() {
		return Gender;
	}
	
	public String getCareer() {
		return Career;
	}	
	
}
