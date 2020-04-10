package br.com.reward.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.reward.enums.RolesEnum;

@Entity
@Table(name="roles")
public class Role implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4025903627078380582L;

    @Id
    private Integer roleId;

    @NotBlank
    @Size(max = 45)
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private List<Client> clients = new ArrayList<>();

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Partner> partners = new ArrayList<>();
    
    public Role(){}
    
    public Role(RolesEnum role) {
        roleId = role.getCodigo();
        description = role.getDescricao();
	}

	public RolesEnum getRoleId() {
        return RolesEnum.toEnum(this.roleId);
    }

    public void setRoleId(RolesEnum roleId) {
        this.roleId = roleId.getCodigo();
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Partner> getPartners() {
        return this.partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roleId);
    }

}