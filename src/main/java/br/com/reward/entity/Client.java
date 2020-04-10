/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.enums.RolesEnum;
import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="clients")
@SequenceGenerator(sequenceName="seq_clients", name = "seq_clients")
public class Client implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1231630315129527992L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_clients")
    private Integer clientId;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @NotBlank
    @Size(max = 100)
    @Email(message = "Email not valid") // Internacionalization https://www.baeldung.com/spring-custom-validation-message-source
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;
    
    @NotBlank
    @Size(max = 100)
    private String name;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @JsonIgnore
    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(columnDefinition = "char(1")
    private Boolean active;
    
    @JsonIgnore
    @OneToMany(mappedBy="client", fetch = FetchType.LAZY)
    private List<Request> requests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy="client", fetch = FetchType.LAZY)
    private List<Indication> indications = new ArrayList<>();

    @OneToMany(mappedBy="client")
    private List<Identification> identifications = new ArrayList<>();

    @OneToMany(mappedBy="client")
    private List<Contact> contacts = new ArrayList<>();
    
    @JsonIgnore
	@ManyToMany
	@JoinTable(name = "clients_roles", 
		joinColumns = @JoinColumn(name = "client_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
    
    public Integer getClientId() {
        return this.clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public List<Request> getRequests() {
        return this.requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Indication> getIndications() {
        return this.indications;
    }

    public void setIndications(List<Indication> indications) {
        this.indications = indications;
    }

    public List<Identification> getIdentifications() {
        return this.identifications;
    }

    public void setIdentifications(List<Identification> identifications) {
        this.identifications = identifications;
    }

    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
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
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

}
