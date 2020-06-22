/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.reward.enums.ContactTypeEnum;

public class ContactDTO implements Serializable {



    /**
     *
     */
    private static final long serialVersionUID = 6535437736070682761L;

    @NotNull
    private ContactTypeEnum contactType;

    @NotEmpty
    private String contact;


    public ContactTypeEnum getContactType() {
        return this.contactType;
    }

    public void setContactType(ContactTypeEnum contactType) {
        this.contactType = contactType;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }



}
