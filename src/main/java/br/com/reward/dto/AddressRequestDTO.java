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
    private static final long serialVersionUID = 1218649949180076383L;

    @NotNull
    private Integer postalCodeId;

    @NotEmpty
    @Size(max = 100)
    private String localeInfo;

    @NotEmpty
    @Size(max = 100)
    private String additionalInfo;


    public Integer getPostalCodeId() {
        return this.postalCodeId;
    }

    public void setPostalCodeId(Integer postalCodeId) {
        this.postalCodeId = postalCodeId;
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

}
