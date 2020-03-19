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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="parameters")
@SequenceGenerator(sequenceName="seq_parameters", name = "seq_parameters")
@JsonIgnoreProperties(value = {"creationAt"}, allowGetters = true)
public class Parameter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 144480522155519317L;

    @Id
    @Column(name="cod_param")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_parameters")
    private Integer codParam;
    
    @NotNull
    @Column(name="indication_expiration")
    private Integer indicationExpiration;

    @NotNull
    @Column(name="score_expiration")
    private Integer scoreExpiration;

    @NotNull(groups=CreationValidator.class)
    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
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
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Parameter)) {
            return false;
        }
        Parameter parameter = (Parameter) o;
        return Objects.equals(codParam, parameter.codParam) && Objects.equals(indicationExpiration, parameter.indicationExpiration) && Objects.equals(scoreExpiration, parameter.scoreExpiration) && Objects.equals(creationAt, parameter.creationAt) && Objects.equals(updatedAt, parameter.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codParam, indicationExpiration, scoreExpiration, creationAt, updatedAt);
    }




}
