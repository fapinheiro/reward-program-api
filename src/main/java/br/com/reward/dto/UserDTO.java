/**
 * @author filipe.pinheiro, 18/05/2019
*/
package br.com.reward.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import br.com.reward.entity.User;

public class UserDTO implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = -2414895188485107655L;

    private Integer id;
    private String login;
    private Date creationAt;
    private Date updatedAt;
    private Boolean active;

	public UserDTO(User user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.creationAt = user.getCreationAt();
		this.updatedAt = user.getUpdatedAt();
		this.active = user.isActive();
	}

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

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof UserDTO)) {
			return false;
		}
		UserDTO userDTO = (UserDTO) o;
		return Objects.equals(id, userDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}


}
