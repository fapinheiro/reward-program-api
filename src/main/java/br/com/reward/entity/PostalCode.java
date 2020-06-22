/**
 * @author filipe.pinheiro, 18/05/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="postal_codes")
public class PostalCode implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6318133029667978978L;

    @Id
    private Integer postalCodeId;
    
    @ManyToOne
    @JoinColumn(name="county_id", nullable=false)
    private County county;

    @NotBlank
	@Size(max = 10)
    private String postalCode;

    @NotBlank
    @Size(max = 100)
    private String locale;

    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    

    public Integer getPostalCodeId() {
        return this.postalCodeId;
    }

    public void setPostalCodeId(Integer postalCodeId) {
        this.postalCodeId = postalCodeId;
    }
    


    public County getCounty() {
        return this.county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
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
        if (!(o instanceof PostalCode)) {
            return false;
        }
        PostalCode postalCode = (PostalCode) o;
        return Objects.equals(postalCodeId, postalCode.postalCodeId) && Objects.equals(county, postalCode.county);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCodeId, county);
    }
    


}
