/**
 * @author filipe.pinheiro, 20/03/2020
*/
package br.com.reward.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.reward.entity.Account;
import br.com.reward.entity.Client;
import br.com.reward.entity.Contact;
import br.com.reward.entity.Identification;

/**
 * DTO to prevent sending sensitive datas like passwords
 */
public class ClientResponseDTO implements Serializable {

    
    /**
     *
     */
    private static final long serialVersionUID = 7625033787850777752L;

    private Integer clientId;
    private String email;
    private String name;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date creationAt;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updatedAt;

    private Boolean active;
    
    private AddressResponseDTO address;

    private Account account;

    private List<Identification> identifications = new ArrayList<>();

    private List<Contact> contacts = new ArrayList<>();
   
    public ClientResponseDTO() {}

    public ClientResponseDTO(Client client) {

        this.clientId = client.getClientId();
        this.email = client.getEmail();
        this.name = client.getName();
        this.birthDate = client.getBirthDate();
        this.creationAt = client.getCreationAt();
        this.updatedAt = client.getUpdatedAt();
        this.active = client.getActive();
        this.account = client.getAccount();
        this.contacts.addAll(client.getContacts());
        this.identifications.addAll(client.getIdentifications());

        // Create address
        this.address = new AddressResponseDTO();
        this.address.setAddressId(client.getAddress().getAddressId());
        this.address.setPostalCodeId(client.getAddress().getPostalCode().getPostalCodeId());
        if (client.getAddress().getPostalCode().getCounty() != null) {
            this.address.setCounty(client.getAddress().getPostalCode().getCounty().getDescription());
            this.address.setDistrict(client.getAddress().getPostalCode().getCounty().getDistrict().getDescription());
        }
        this.address.setPostaCode(client.getAddress().getPostalCode().getPostalCode());
        this.address.setLocale(client.getAddress().getPostalCode().getLocale());
        this.address.setLocaleInfo(client.getAddress().getLocaleInfo());
        this.address.setAdditionalInfo(client.getAddress().getAdditionalInfo());

	}

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

    public AddressResponseDTO getAddress() {
        return this.address;
    }

    public void setAddress(AddressResponseDTO address) {
        this.address = address;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

}
