/**
 * @author filipe.pinheiro, 20/03/2020
*/
package br.com.reward.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.reward.annotation.ClientInsert;

/**
 * DTO to prevent sending sensitive datas like passwords
 */
@ClientInsert
public class ClientRequestDTO implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 4559913915106527377L;

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
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    
    @NotNull
    private AddressRequestDTO address;

    @NotEmpty
    private List<IdentificationDTO> identifications = new ArrayList<>();

    @NotEmpty
    private List<ContactDTO> contacts = new ArrayList<>();


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

    public AddressRequestDTO getAddress() {
        return this.address;
    }

    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }


    public List<IdentificationDTO> getIdentifications() {
        return this.identifications;
    }

    public void setIdentifications(List<IdentificationDTO> identifications) {
        this.identifications = identifications;
    }

    public List<ContactDTO> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }
    


}
