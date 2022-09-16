package com.ugisoftware.hotelmanagement.dto.response;

import java.util.List;

public class AuthResponseDTO {
	private String token;
	  private String type = "Bearer";
	  private Long id;
	  private String username;
	  private String email;
	  private List<String> roles;
      private String refreshToken;

	  public AuthResponseDTO(String accessToken, Long id, String username, String email, List<String> roles,String refreshToken) {
	    this.token = accessToken;
	    this.id = id;
	    this.username = username;
	    this.email = email;
	    this.roles = roles;
	    this.refreshToken=refreshToken;
	  }

	  public String getAccessToken() {
	    return token;
	  }

	  public void setAccessToken(String accessToken) {
	    this.token = accessToken;
	  }

	  public String getTokenType() {
	    return type;
	  }

	  public void setTokenType(String tokenType) {
	    this.type = tokenType;
	  }

	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getRoles() {
	    return roles;
	  }

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	
	  
}
