/**
 * @author filipe.pinheiro, 14/08/2019
*/
package br.com.reward.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.reward.entity.Indication;
import br.com.reward.enums.IndicationStatusEnum;

public class IndicationDTO implements Serializable {
   
    /**
     *
     */
    private static final long serialVersionUID = 5973542511230184144L;

    private Integer indicationId;
    
    @NotNull
    private Integer clientId;

    private Integer status;

    @NotBlank
    @Size(max = 100)
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Size(max = 100)
    private String name;
    
    @NotBlank
    @Size(max = 20)
    private String phone;

    private OffsetDateTime creationAt;

    private OffsetDateTime updatedAt;

    public IndicationDTO() {}
    
    public IndicationDTO(Indication ind) {
        this.clientId = ind.getClient().getClientId();
        this.creationAt = ind.getCreationAt();
        this.email = ind.getEmail();
        this.indicationId = ind.getCodIndication();
        this.name = ind.getName();
        this.phone = ind.getPhone();
        this.status = ind.getStatus().getCodigo();
        this.updatedAt = ind.getUpdatedAt();
	}

	public Integer getIndicationId() {
        return this.indicationId;
    }

    public void setIndicationId(Integer indicationId) {
        this.indicationId = indicationId;
    }

    public Integer getClientId() {
        return this.clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public IndicationStatusEnum getStatus() {
        return IndicationStatusEnum.toEnum(this.status);
    }

    public void setStatus(IndicationStatusEnum status) {
        this.status = status.getCodigo();
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

    public OffsetDateTime getCreationAt() {
        return this.creationAt;
    }

    public void setCreationAt(OffsetDateTime creationAt) {
        this.creationAt = creationAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


}
