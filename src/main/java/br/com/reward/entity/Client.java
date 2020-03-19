/**
 * @author filipe.pinheiro, 27/07/2019
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="clients")
@SequenceGenerator(sequenceName="seq_clients", name = "seq_clients")
@JsonIgnoreProperties(value = {"creationAt"}, allowGetters = true)
public class Client implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1231630315129527992L;

    @Id
    @Column(name="cod_cliente")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_clients")
    private Integer codCliente;
    
    @NotBlank
    @Size(max = 100)
    @Email(message = "Email should be valid")
    @Column(name="email")
    private String email;

    @NotBlank
    @Size(max = 100)
    @Column(name="password")
    private String password;
    
    @NotBlank
    @Size(max = 100)
    @Column(name="name")
    private String name;
    
    @NotBlank
    @Size(max = 20)
    @Column(name="phone")
    private String phone;

    @NotBlank
    @Size(max = 10)
    @Column(name="nif")
    private String nif;

    @NotNull
    @ManyToOne
    @JoinColumn(name="cod_codigo_postal", nullable=false)
    private PostalCode postalCode;

    @NotNull(groups=CreationValidator.class)
    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;

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

    public PostalCode getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
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
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return Objects.equals(codCliente, client.codCliente) && Objects.equals(email, client.email) && Objects.equals(password, client.password) && Objects.equals(name, client.name) && Objects.equals(nif, client.nif) && Objects.equals(postalCode, client.postalCode) && Objects.equals(creationAt, client.creationAt) && Objects.equals(updatedAt, client.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codCliente, email, password, name, nif, postalCode, creationAt, updatedAt);
    }



}
