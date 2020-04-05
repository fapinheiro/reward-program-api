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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="categories")
@SequenceGenerator(sequenceName="seq_categories", name = "seq_categories")
public class Category implements Serializable {

   
    /**
     *
     */
    private static final long serialVersionUID = 6817570385263379621L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_categories")
    private Integer categoriesId;

    private String description;

    @JsonIgnore
    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    public Integer getCategoriesId() {
        return this.categoriesId;
    }

    public void setCategoriesId(Integer categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(categoriesId, category.categoriesId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(categoriesId);
    }

}
