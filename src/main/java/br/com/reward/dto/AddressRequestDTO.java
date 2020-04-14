/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class AddressRequestDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -141118719957403435L;

    @NotNull
    private Integer postalCodeId;

    @NotEmpty
    @Size(max = 10)
    private String localeNumber;

    @NotEmpty
    @Size(max = 100)
    private String additionalInfo;


    public Integer getPostalCodeId() {
        return this.postalCodeId;
    }

    public void setPostalCodeId(Integer postalCodeId) {
        this.postalCodeId = postalCodeId;
    }

    public String getLocaleNumber() {
        return this.localeNumber;
    }

    public void setLocaleNumber(String localeNumber) {
        this.localeNumber = localeNumber;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

}
