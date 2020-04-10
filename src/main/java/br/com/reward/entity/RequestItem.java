package br.com.reward.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "request_items")
public class RequestItem implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -614911449172346609L;
    
    @JsonIgnore
    @EmbeddedId
	private RequestItemPK id = new RequestItemPK();

    @NotNull
    private Integer requiredScore;

    @NotNull
    private Integer discount;

    @NotNull
    private Integer winScore;

    public RequestItemPK getId() {
        return this.id;
    }

    public void setId(RequestItemPK id) {
        this.id = id;
    }

    public Integer getRequiredScore() {
        return this.requiredScore;
    }

    public void setRequiredScore(Integer requiredScore) {
        this.requiredScore = requiredScore;
    }

    public Integer getWinScore() {
        return this.winScore;
    }

    public void setWinScore(Integer winScore) {
        this.winScore = winScore;
    }

    public Integer getDiscount() {
        return this.discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }


}