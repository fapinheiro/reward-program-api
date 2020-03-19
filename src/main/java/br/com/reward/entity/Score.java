/**
 * @author filipe.pinheiro, 03/03/2020
 */
package br.com.reward.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.reward.enums.ScoreTypeEnum;
import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="scores")
@SequenceGenerator(sequenceName="seq_scores", name = "seq_scores")
@JsonIgnoreProperties(value = {"creationAt"}, allowGetters = true)
public class Score implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4162519403186281452L;

    @Id
    @Column(name="cod_score")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_scores")
    private Integer codScore;
    
    @NotNull
    @Column(name="good_type", nullable=false)
    @Enumerated(EnumType.STRING)
    private ScoreTypeEnum goodType;

    @NotNull
    @Column(name="credit_min", nullable=false)
    private Integer creditMin;

    @NotNull
    @Column(name="credit_max", nullable=false)
    private Integer creditMax;

    @NotNull
    @Column(name="inst_min", nullable=false)
    private Integer instMin;

    @NotNull
    @Column(name="inst_max", nullable=false)
    private Integer instMax;

    @NotNull
    @Column(name="score", nullable=false)
    private Integer score;

    @NotNull(groups=CreationValidator.class)
    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;



    public Integer getCodScore() {
        return this.codScore;
    }

    public void setCodScore(Integer codScore) {
        this.codScore = codScore;
    }

    public ScoreTypeEnum getGoodType() {
        return this.goodType;
    }

    public void setGoodType(ScoreTypeEnum goodType) {
        this.goodType = goodType;
    }

    public Integer getCreditMin() {
        return this.creditMin;
    }

    public void setCreditMin(Integer creditMin) {
        this.creditMin = creditMin;
    }

    public Integer getCreditMax() {
        return this.creditMax;
    }

    public void setCreditMax(Integer creditMax) {
        this.creditMax = creditMax;
    }

    public Integer getInstMin() {
        return this.instMin;
    }

    public void setInstMin(Integer instMin) {
        this.instMin = instMin;
    }

    public Integer getInstMax() {
        return this.instMax;
    }

    public void setInstMax(Integer instMax) {
        this.instMax = instMax;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
        if (!(o instanceof Score)) {
            return false;
        }
        Score score = (Score) o;
        return Objects.equals(codScore, score.codScore) && Objects.equals(goodType, score.goodType) && Objects.equals(creditMin, score.creditMin) && Objects.equals(creditMax, score.creditMax) && Objects.equals(instMin, score.instMin) && Objects.equals(instMax, score.instMax) && Objects.equals(score, score.score) && Objects.equals(creationAt, score.creationAt) && Objects.equals(updatedAt, score.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codScore, goodType, creditMin, creditMax, instMin, instMax, score, creationAt, updatedAt);
    }


}
