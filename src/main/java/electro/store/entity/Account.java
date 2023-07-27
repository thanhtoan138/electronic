package electro.store.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Accounts")
public class Account  implements Serializable{
	
	@Id
	String username;
	String password;
	String fullname;
	String email;
	Integer telephone;
	Date birthday;
	String photo;
	Boolean sex;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Auth_type")
	AuthenticationType authType;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
}
