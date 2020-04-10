package br.com.reward.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "physical_items")
public class ItemPhysical extends Item implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5509993766811537317L;

    @NotNull
    @ManyToOne
	@JoinColumn(name="address_id")
    private Address address;
    
    @Column(name = "physical_name")
    private String phycialName;


    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhycialName() {
        return this.phycialName;
    }

    public void setPhycialName(String phycialName) {
        this.phycialName = phycialName;
    }

}