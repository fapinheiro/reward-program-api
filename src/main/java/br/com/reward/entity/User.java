/**
 * @author filipe.pinheiro, 18/05/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.validator.CreationValidator;

@Entity(name="users")
@SequenceGenerator(sequenceName="seq_users", name = "seq_users")
public class User implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 6437343477566237980L;

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_users")
    private Integer id;
	
    @NotBlank
	@Size(max = 100)
    private String login;
    
    @NotBlank
	@Size(max = 100)
	private String password;
	
	@JsonIgnore
	@NotNull(groups=CreationValidator.class)
    @Column(name="creation_at")
	@Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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
		return Objects.equals(login, user.login);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(login);
	}




}
