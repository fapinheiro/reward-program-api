/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.reward.enums.IdentificationTypeEnum;

public class IdentificationDTO implements Serializable {



    /**
     *
     */
    private static final long serialVersionUID = -5186165143082811156L;

    @NotNull
    private IdentificationTypeEnum identType;

    @NotEmpty
    private String identCode;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date emissionDate;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date validDate;


    public IdentificationTypeEnum getIdentType() {
        return this.identType;
    }

    public void setIdentType(IdentificationTypeEnum identType) {
        this.identType = identType;
    }

    public String getIdentCode() {
        return this.identCode;
    }

    public void setIdentCode(String identCode) {
        this.identCode = identCode;
    }

    public Date getEmissionDate() {
        return this.emissionDate;
    }

    public void setEmissionDate(Date emissionDate) {
        this.emissionDate = emissionDate;
    }

    public Date getValidDate() {
        return this.validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }



}
