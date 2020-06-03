/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.dto;

import java.io.Serializable;



public class AddressResponseDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 636456323792349088L;

    private Integer addressId;

    private Integer postalCodeId;

    private String county;

    private String district;

    private String postaCode;
    
    private String locale;

    private String localeInfo;

    private String additionalInfo;


    public Integer getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getPostalCodeId() {
        return this.postalCodeId;
    }

    public void setPostalCodeId(Integer postalCodeId) {
        this.postalCodeId = postalCodeId;
    }

    public String getCounty() {
        return this.county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostaCode() {
        return this.postaCode;
    }

    public void setPostaCode(String postaCode) {
        this.postaCode = postaCode;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
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
