/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="distritos")
public class Distrito {

    @Id
	@Column(name="cod_distrito")
    private Integer codDistrito;
    
    @NotBlank
	@Size(max = 100)
    @Column(name="nome_distrito")
    private String nomeDistrito;

    @NotNull(groups=CreationValidator.class)
    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;
    
    public Integer getCodDistrito() {
        return this.codDistrito;
    }

    public void setCodDistrito(Integer codDistrito) {
        this.codDistrito = codDistrito;
    }

    public String getNomeDistrito() {
        return this.nomeDistrito;
    }

    public void setNomeDistrito(String nomeDistrito) {
        this.nomeDistrito = nomeDistrito;
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
        if (!(o instanceof Distrito)) {
            return false;
        }
        Distrito distrito = (Distrito) o;
        return Objects.equals(codDistrito, distrito.codDistrito) && Objects.equals(nomeDistrito, distrito.nomeDistrito) && Objects.equals(creationAt, distrito.creationAt) && Objects.equals(updatedAt, distrito.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codDistrito, nomeDistrito, creationAt, updatedAt);
    }

}
