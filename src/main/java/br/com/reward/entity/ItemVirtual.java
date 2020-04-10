package br.com.reward.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "virtual_items")
public class ItemVirtual extends Item implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1440765215371857189L;

    @NotNull
    private String urlSite;

    public String getUrlSite() {
        return this.urlSite;
    }

    public void setUrlSite(String urlSite) {
        this.urlSite = urlSite;
    }


}