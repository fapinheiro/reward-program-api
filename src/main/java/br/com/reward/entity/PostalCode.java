/**
 * @author filipe.pinheiro, 18/05/2019
*/
package br.com.reward.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="codigos_postais")
public class PostalCode {

    @Id
	@Column(name="cod_codigo_postal")
    private Integer idCodigoPostal;
    
    @NotBlank
    @ManyToOne
    @JoinColumn(name="id_concelho", nullable=false)
    private Concelho concelho;

    @NotBlank
	@Size(max = 10)
    @Column(name="codigo_postal")
    private String codigoPostal;

    @NotBlank
	@Size(max = 100)
    private String localidade;

    @NotNull(groups=CreationValidator.class)
    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;
    
    public Integer getIdCodigoPostal() {
        return this.idCodigoPostal;
    }

    public void setIdCodigoPostal(Integer idCodigoPostal) {
        this.idCodigoPostal = idCodigoPostal;
    }

    public Concelho getConcelho() {
        return this.concelho;
    }

    public void setConcelho(Concelho concelho) {
        this.concelho = concelho;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
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
        if (!(o instanceof PostalCode)) {
            return false;
        }
        PostalCode postalCode = (PostalCode) o;
        return Objects.equals(idCodigoPostal, postalCode.idCodigoPostal) && Objects.equals(concelho, postalCode.concelho) && Objects.equals(codigoPostal, postalCode.codigoPostal) && Objects.equals(localidade, postalCode.localidade) && Objects.equals(creationAt, postalCode.creationAt) && Objects.equals(updatedAt, postalCode.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCodigoPostal, concelho, codigoPostal, localidade, creationAt, updatedAt);
    }
    

}
