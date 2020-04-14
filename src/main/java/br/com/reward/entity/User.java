/**
 * @author filipe.pinheiro, 18/05/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import br.com.reward.enums.RolesEnum;
import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="users")
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
	
	@NotNull(groups=CreationValidator.class)
	@Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

	@Type(type="true_false")
	@Column(columnDefinition = "char(1)")
    private Boolean active;

	@JsonIgnore
	@ManyToMany
	@Fetch(FetchMode.JOIN)
	@JoinTable(name = "users_roles", 
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
	
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


	public Boolean isActive() {
		return this.active;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<RolesEnum> getRoles() {
		return this.roles
				.stream()
				.map( role -> role.getRoleId() )
				.collect(Collectors.toList());
	}

	public void setRoles(List<RolesEnum> rolesList) {
		this.roles = rolesList.stream()
								  .map( role -> new Role(role))
								  .collect(Collectors.toList());
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
