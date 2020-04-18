/**
 * @author filipe.pinheiro, 27/02/2020
*/
package br.com.reward.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.reward.annotation.ParameterUpdate;
import br.com.reward.entity.Parameter;

@ParameterUpdate
public class ParameterDTO implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 4923994356891040217L;

    private Integer paramId;

    private Integer indicationExpiration;

    private Integer scoreExpiration;

    private Integer requestExpiration;

    private Date creationAt;

    private Date updatedAt;

    public ParameterDTO() {}
    
    public ParameterDTO(Parameter obj) {
        this.paramId = obj.getParamId();
        this.creationAt = obj.getCreationAt();
        this.indicationExpiration = obj.getIndicationExpiration();
        this.requestExpiration = obj.getRequestExpiration();
        this.scoreExpiration = obj.getScoreExpiration();
        this.updatedAt = obj.getUpdatedAt();
	}

    public Integer getParamId() {
        return this.paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

	public Integer getIndicationExpiration() {
        return this.indicationExpiration;
    }

    public void setIndicationExpiration(Integer indicationExpiration) {
        this.indicationExpiration = indicationExpiration;
    }

    public Integer getScoreExpiration() {
        return this.scoreExpiration;
    }

    public void setScoreExpiration(Integer scoreExpiration) {
        this.scoreExpiration = scoreExpiration;
    }

    public Integer getRequestExpiration() {
        return this.requestExpiration;
    }

    public void setRequestExpiration(Integer requestExpiration) {
        this.requestExpiration = requestExpiration;
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
   

}
