/**
 * @author filipe.pinheiro, 20/03/2020
*/
package br.com.reward.dto;

import java.io.Serializable;

import br.com.reward.annotation.ClientUpdate;

/**
 * DTO to prevent sending sensitive datas like passwords
 */
@ClientUpdate
public class ClientUpdateDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6827473505666861254L;

    private String email;

    private String name;

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
    

}
