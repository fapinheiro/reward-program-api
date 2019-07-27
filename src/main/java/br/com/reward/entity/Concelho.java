/**
 * @author filipe.pinheiro, 27/07/2019
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

@Entity
@Table(name="concelhos")
public class Concelho {

    @Id
	@Column(name="id_concelho")
    private Integer idConcelho;
    
    @NotBlank
    @Column(name="cod_concelho")
    private Integer codConcelho;

    @NotBlank
    @ManyToOne
    @JoinColumn(name="cod_distrito", nullable=false)
    private Distrito distrito;

    @NotBlank
	@Size(max = 100)
    @Column(name="nome_concelho")
    private String nomeConcelho;

    @NotNull
    @Column(name="creation_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;
    

    public Integer getIdConcelho() {
        return this.idConcelho;
    }

    public void setIdConcelho(Integer idConcelho) {
        this.idConcelho = idConcelho;
    }

    public Integer getCodConcelho() {
        return this.codConcelho;
    }

    public void setCodConcelho(Integer codConcelho) {
        this.codConcelho = codConcelho;
    }

    public Distrito getDistrito() {
        return this.distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public String getNomeConcelho() {
        return this.nomeConcelho;
    }

    public void setNomeConcelho(String nomeConcelho) {
        this.nomeConcelho = nomeConcelho;
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
        if (!(o instanceof Concelho)) {
            return false;
        }
        Concelho concelho = (Concelho) o;
        return Objects.equals(idConcelho, concelho.idConcelho) && Objects.equals(codConcelho, concelho.codConcelho) && Objects.equals(distrito, concelho.distrito) && Objects.equals(nomeConcelho, concelho.nomeConcelho) && Objects.equals(creationAt, concelho.creationAt) && Objects.equals(updatedAt, concelho.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConcelho, codConcelho, distrito, nomeConcelho, creationAt, updatedAt);
    }
    

}
