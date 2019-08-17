/**
 * @author filipe.pinheiro, 18/05/2019
*/
package br.com.reward.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.reward.validator.CreationValidator;

@Entity(name="users")
@SequenceGenerator(sequenceName="seq_users", name = "seq_users")
@JsonIgnoreProperties(value = {"creationAt"}, allowGetters = true)
public class User {

	@Id
	@Column(name="cod_user")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_users")
    private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente", nullable=true)
	private Client client;
	
    @NotBlank
	@Size(max = 100)
    private String login;
    
    @NotBlank
	@Size(max = 100)
	private String password;
	
	@NotNull(groups=CreationValidator.class)
    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
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


	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return id == user.id && Objects.equals(client, user.client) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(creationAt, user.creationAt) && Objects.equals(updatedAt, user.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, client, login, password, creationAt, updatedAt);
	}



}
