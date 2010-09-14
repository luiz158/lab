package examples;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Credentials {
	
	@NotNull
	@Size(max = 10)
	private String username;
	
	@NotNull
	@Size(min = 4, max = 10)
	@Password
	private String password;
	
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}
