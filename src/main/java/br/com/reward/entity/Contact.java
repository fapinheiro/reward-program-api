/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.enums.ContactTypeEnum;
import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="contacts")
@SequenceGenerator(sequenceName="seq_contacts", name = "seq_contacts")
public class Contact implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3363979565212651628L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_contacts")
    private Integer contactId;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    private Integer contactType;

    private String contact;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validatedAt;

    @JsonIgnore
    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Integer getContactId() {
        return this.contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ContactTypeEnum getContactType() {
        return ContactTypeEnum.toEnum(this.contactType);
    }

    public void setContactType(ContactTypeEnum contactType) {
        this.contactType = contactType.getCodigo();
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getValidatedAt() {
        return this.validatedAt;
    }

    public void setValidatedAt(Date validatedAt) {
        this.validatedAt = validatedAt;
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
        if (!(o instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(contactId);
    }


}
