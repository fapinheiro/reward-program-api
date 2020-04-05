/**
 * @author filipe.pinheiro, 27/02/2020
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="parameters")
@SequenceGenerator(sequenceName="seq_parameters", name = "seq_parameters")
public class Parameter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 144480522155519317L;

    @Id
    @Column(name="param_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_parameters")
    private Integer codParam;
    
    @NotNull
    @Column(name="indication_expiration")
    private Integer indicationExpiration;

    @NotNull
    @Column(name="score_expiration")
    private Integer scoreExpiration;

    @NotNull
    @Column(name="request_expiration")
    private Integer requestExpiration;

    @NotNull(groups=CreationValidator.class)
    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;


    public Integer getCodParam() {
        return this.codParam;
    }

    public void setCodParam(Integer codParam) {
        this.codParam = codParam;
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
    

    public Integer getRequestExpiration() {
        return this.requestExpiration;
    }

    public void setRequestExpiration(Integer requestExpiration) {
        this.requestExpiration = requestExpiration;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Parameter)) {
            return false;
        }
        Parameter parameter = (Parameter) o;
        return Objects.equals(codParam, parameter.codParam);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codParam);
    }




}
