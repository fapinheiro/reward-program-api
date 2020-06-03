/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.dto.IdentificationDTO;
import br.com.reward.enums.IdentificationTypeEnum;
import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="identifications")
@SequenceGenerator(sequenceName="seq_identifications", name = "seq_identifications")
public class Identification implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7185338826214097660L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_identifications")
    private Integer identificationId;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    private Integer identType;

    private String identCode;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date emissionDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date validDate;

    @JsonIgnore
    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Identification() {}

    public Identification(IdentificationDTO dto) {
        this.identType = dto.getIdentType().getCodigo();
        this.identCode = dto.getIdentCode();
        this.emissionDate = dto.getEmissionDate();
        this.validDate = dto.getValidDate();
    }

    public Integer getIdentificationId() {
        return this.identificationId;
    }

    public void setIdentificationId(Integer identificationId) {
        this.identificationId = identificationId;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public IdentificationTypeEnum getIdentType() {
        return IdentificationTypeEnum.toEnum(this.identType);
    }

    public void setIdentType(IdentificationTypeEnum identType) {
        this.identType = identType.getCodigo();
    }

    public String getIdentCode() {
        return this.identCode;
    }

    public void setIdentCode(String identCode) {
        this.identCode = identCode;
    }

    public Date getEmissionDate() {
        return this.emissionDate;
    }

    public void setEmissionDate(Date emissionDate) {
        this.emissionDate = emissionDate;
    }

    public Date getValidDate() {
        return this.validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
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
        if (!(o instanceof Identification)) {
            return false;
        }
        Identification identification = (Identification) o;
        return Objects.equals(identificationId, identification.identificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identificationId);
    }


}
