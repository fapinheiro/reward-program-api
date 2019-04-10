package br.com.reward.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="users")
@SequenceGenerator(sequenceName="seq_user", name = "seq_user")
@JsonIgnoreProperties(value = {"creationAt"}, allowGetters = true)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_user")
    private long id;
    
    @NotBlank
	@Size(max = 100)
    private String login;
    
    @NotBlank
	@Size(max = 100)
	private String password;
	
	@Column(name="creation_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationAt;


	

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreationAt() {
		return this.creationAt;
	}

	public void setCreationAt(Date creationAt) {
		this.creationAt = creationAt;
	}


	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(creationAt, user.creationAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, password, creationAt);
	}


	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", login='" + getLogin() + "'" +
			", password='" + getPassword() + "'" +
			", creationAt='" + getCreationAt() + "'" +
			"}";
	}



}
