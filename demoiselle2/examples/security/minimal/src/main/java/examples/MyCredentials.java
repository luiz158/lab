package examples;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class MyCredentials implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	public void clear() {
		username = null;
		password = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
