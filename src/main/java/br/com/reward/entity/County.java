/**
 * @author filipe.pinheiro, 27/07/2019
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
@Table(name="counties")
public class County implements Serializable {

   

    /**
     *
     */
    private static final long serialVersionUID = -2642525051197921841L;

    @Id
    private Integer countyId;

    @NotNull
    private Integer countyCode;

    @ManyToOne
    @JoinColumn(name="district_id", nullable=false)
    private District district;

    @NotBlank
	@Size(max = 100)
    private String description;

    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    

    public Integer getCountyId() {
        return this.countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }
    


    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District distrito) {
        this.district = distrito;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getCountyCode() {
        return this.countyCode;
    }

    public void setCountyCode(Integer countyCode) {
        this.countyCode = countyCode;
    }
   


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof County)) {
            return false;
        }
        County county = (County) o;
        return Objects.equals(countyId, county.countyId) && Objects.equals(district, county.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countyId, district);
    }

    
    

}
