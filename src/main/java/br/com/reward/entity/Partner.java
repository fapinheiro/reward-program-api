package br.com.reward.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.enums.RolesEnum;


@Entity
@Table(name="partners")
@SequenceGenerator(sequenceName="seq_partners", name = "seq_partners")
public class Partner implements Serializable {

    
    /**
     *
     */
    private static final long serialVersionUID = 3711142956564935547L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_partners")
    private Integer partnerId;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    private String name;
    
    @Column(columnDefinition = "char(1)")
    private Boolean active;

    private String primaryColor;

    private String secondaryColor;

    private String logoUrl;

    private String email;

    private String password;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "partners_roles", 
		joinColumns = @JoinColumn(name = "partner_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public Integer getPartnerId() {
        return this.partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return this.secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(o instanceof Partner)) {
            return false;
        }
        Partner partner = (Partner) o;
        return Objects.equals(partnerId, partner.partnerId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(partnerId);
    }

}