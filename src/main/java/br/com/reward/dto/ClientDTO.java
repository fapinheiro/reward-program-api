/**
 * @author filipe.pinheiro, 20/03/2020
*/
package br.com.reward.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import br.com.reward.entity.Client;

/**
 * DTO to prevent sending sensitive datas like passwords
 */
public class ClientDTO implements Serializable {

   
    /**
     *
     */
    private static final long serialVersionUID = 1855000317168573808L;

    private Integer codCliente;
    private String email;
    private String name;
    private String phone;
    private String nif;
    private Date creationAt;

    public ClientDTO() {}
    
    public ClientDTO(Client client) {
        this.codCliente = client.getCodCliente();
        this.email = client.getEmail();
        this.name = client.getEmail();
        this.phone = client.getEmail();
        this.nif = client.getEmail();
        this.creationAt = client.getCreationAt();
    }

    public Integer getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNif() {
        return this.nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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
        if (!(o instanceof ClientDTO)) {
            return false;
        }
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(codCliente, clientDTO.codCliente) && Objects.equals(email, clientDTO.email) && Objects.equals(name, clientDTO.name) && Objects.equals(phone, clientDTO.phone) && Objects.equals(nif, clientDTO.nif) && Objects.equals(creationAt, clientDTO.creationAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codCliente, email, name, phone, nif, creationAt);
    }
    


}
