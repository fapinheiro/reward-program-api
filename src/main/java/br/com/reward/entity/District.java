/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="districts")
public class District implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 928697323608535397L;

    @Id
    private Integer districtId;
    
    @NotBlank
	@Size(max = 10)
    private String districtCode;

    @NotBlank
	@Size(max = 100)
    private String description;

    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public String getDistrictCode() {
        return this.districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
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


    public Integer getDistrictId() {
        return this.districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof District)) {
            return false;
        }
        District district = (District) o;
        return Objects.equals(districtId, district.districtId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(districtId);
    }

    

}
