/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="addresses")
@SequenceGenerator(sequenceName="seq_addresses", name = "seq_addresses")
public class Address implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3196330963969934462L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_addresses")
    private Integer addressId;

    @NotNull
    @ManyToOne
    @JoinColumn(name="postal_code_id", nullable=false)
    private PostalCode postalCode;

    @Size(max = 100)
    private String localeInfo;

    @Size(max = 100)
    private String additionalInfo;

    @JsonIgnore
    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JsonIgnore
	@OneToOne(mappedBy = "address")
    private Client client;

    @JsonIgnore
    @OneToMany(mappedBy="address", fetch = FetchType.LAZY)
    private List<ItemPhysical> requests = new ArrayList<>();

    public Integer getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public PostalCode getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
    }


    public String getLocaleInfo() {
        return this.localeInfo;
    }

    public void setLocaleInfo(String localeInfo) {
        this.localeInfo = localeInfo;
    }
    

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ItemPhysical> getRequests() {
        return this.requests;
    }

    public void setRequests(List<ItemPhysical> requests) {
        this.requests = requests;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Address)) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(addressId);
    }

}
