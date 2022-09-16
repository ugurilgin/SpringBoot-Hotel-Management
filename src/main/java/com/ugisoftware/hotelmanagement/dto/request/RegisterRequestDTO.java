package com.ugisoftware.hotelmanagement.dto.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterRequestDTO {
		@NotBlank
		@Size(min=3 ,max = 30)
	  private String name;
		@NotBlank
		@Size(min=3 ,max = 30)
	  private String surname;
		@NotBlank
		@Size(min=3 ,max = 20)
	  private String username;
		@NotBlank
		@Email
		@Size(max = 50)
	  private String email;
	  private Boolean enabled;
	  private Set<String> role;

	  
	  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	private String password;

	  public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

	  public Set<String> getRole() {
	    return this.role;
	  }

	  public void setRole(Set<String> role) {
	    this.role = role;
	  }

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	  
	}