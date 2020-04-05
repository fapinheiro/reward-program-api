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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.enums.RequestStatusEnum;
import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="requests")
@SequenceGenerator(sequenceName="seq_requests", name = "seq_requests")
public class Request implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -8742358508302544282L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_requests")
    private Integer requestId;

    @NotNull
    @ManyToOne
	@JoinColumn(name="client_id")
    private Client client;

    private Integer status;
    
    @NotBlank
    @Size(max = 200)
    private String cupom;

    @JsonIgnore
    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date validUtil;


    public Integer getRequestId() {
        return this.requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public RequestStatusEnum getStatus() {
        return RequestStatusEnum.toEnum(status);
    }

    public void setStatus(RequestStatusEnum status) {
        this.status = status.getCodigo();
    }

    public String getCupom() {
        return this.cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
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

    public Date getValidUtil() {
        return this.validUtil;
    }

    public void setValidUtil(Date validUtil) {
        this.validUtil = validUtil;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Request)) {
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(requestId, request.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(requestId);
    }

    
}