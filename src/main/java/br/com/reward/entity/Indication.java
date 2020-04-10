/**
 * @author filipe.pinheiro, 14/08/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.reward.enums.IndicationStatusEnum;
import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="indications")
@SequenceGenerator(sequenceName="seq_indications", name = "seq_indications")
public class Indication implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4995662849944750966L;

    @Id
    @Column(name="indication_id ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_indications")
    private Integer codIndication;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    private Integer status;

    @NotBlank
    @Size(max = 100)
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Size(max = 100)
    private String name;
    
    @NotBlank
    @Size(max = 20)
    private String phone;

    @NotNull(groups=CreationValidator.class)
    @Column(name="creation_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime creationAt;

    @Column(name="updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;


    public Integer getCodIndication() {
        return this.codIndication;
    }

    public void setCodIndication(Integer codIndication) {
        this.codIndication = codIndication;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OffsetDateTime getCreationAt() {
        return this.creationAt;
    }

    public void setCreationAt(OffsetDateTime creationAt) {
        this.creationAt = creationAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public IndicationStatusEnum getStatus() {
        return IndicationStatusEnum.toEnum(this.status);
    }

    public void setStatus(IndicationStatusEnum status) {
        this.status = status.getCodigo();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Indication)) {
            return false;
        }
        Indication indication = (Indication) o;
        return Objects.equals(email, indication.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

   


}
