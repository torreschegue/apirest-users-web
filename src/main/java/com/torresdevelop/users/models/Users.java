package com.torresdevelop.users.models;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
// @Data // Super Notacion
@AllArgsConstructor // Contruir Constructor con Valores
@NoArgsConstructor // Contructor VAcio
public class Users {
	
	private String Id;
	private String nickName;
	private String userName;
	private String password;
		

}
