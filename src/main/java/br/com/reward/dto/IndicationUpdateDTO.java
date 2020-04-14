/**
 * @author filipe.pinheiro, 20/03/2020
*/
package br.com.reward.dto;

import java.io.Serializable;

import br.com.reward.annotation.IndicationUpdate;

/**
 * DTO to prevent sending sensitive datas like passwords
 */
@IndicationUpdate
public class IndicationUpdateDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4808604973934764189L;

    private String email;

    private String name;
    
    private String phone;

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

}
