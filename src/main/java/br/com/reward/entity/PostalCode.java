/**
 * @author filipe.pinheiro, 18/05/2019
*/
package br.com.reward.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

// import java.util.Date;
// import java.util.Objects;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.SequenceGenerator;
// import javax.persistence.Temporal;
// import javax.persistence.TemporalType;
// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.Size;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import mt.com.vodafone.enums.*;


@Entity(name="codigos_postais")
// @SequenceGenerator(sequenceName="seq_subscriber", name = "seq_subscriber")
// @JsonIgnoreProperties(value = {"creationAt"}, allowGetters = true)
public class PostalCode {

    @Id
	@Column(name="cod_codigo_postal")
    private Integer idCodigoPostal;
    
    @NotBlank
    @Column(name="cod_distrito")
    private Integer codigoDistrito;

    @NotBlank
    @Column(name="cod_concelho")
    private Integer codigoConcelho;

    @NotBlank
	@Size(max = 10)
    @Column(name="codigo_postal")
    private String codigoPostal;

    @NotBlank
	@Size(max = 100)
    private String localidade;

    @NotNull
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

    public Integer getCodigoDistrito() {
        return this.codigoDistrito;
    }

    public void setCodigoDistrito(Integer codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    public Integer getCodigoConcelho() {
        return this.codigoConcelho;
    }

    public void setCodigoConcelho(Integer codigoConcelho) {
        this.codigoConcelho = codigoConcelho;
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
        return Objects.equals(idCodigoPostal, postalCode.idCodigoPostal) && Objects.equals(codigoDistrito, postalCode.codigoDistrito) && Objects.equals(codigoConcelho, postalCode.codigoConcelho) && Objects.equals(codigoPostal, postalCode.codigoPostal) && Objects.equals(localidade, postalCode.localidade) && Objects.equals(creationAt, postalCode.creationAt) && Objects.equals(updatedAt, postalCode.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCodigoPostal, codigoDistrito, codigoConcelho, codigoPostal, localidade, creationAt, updatedAt);
    }

}

// 	@Id
// 	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_subscriber")
// 	private Integer id;

// 	@NotBlank
// 	@Size(max = 100)
// 	private String msisdn;
// 	private Integer customerIdOwner;
// 	private Integer customerIdUser;
// 	private MobileEnum serviceType;
// 	private Long serviceStartDate;

// 	@Column(name="creation_at")
// 	@Temporal(TemporalType.TIMESTAMP)
// 	private Date creationAt;

// 	// Getter and Setters
// 	public Integer getId() {
// 		return id;
// 	}
// 	public void setId(Integer id) {
// 		this.id = id;
// 	}
// 	public String getMsisdn() {
// 		return msisdn;
// 	}
// 	public void setMsisdn(String msisdn) {
// 		this.msisdn = msisdn;
// 	}
// 	public Integer getCustomerIdOwner() {
// 		return customerIdOwner;
// 	}
// 	public void setCustomerIdOwner(Integer customerIdOwner) {
// 		this.customerIdOwner = customerIdOwner;
// 	}
// 	public Integer getCustomerIdUser() {
// 		return customerIdUser;
// 	}
// 	public void setCustomerIdUser(Integer customerIdUser) {
// 		this.customerIdUser = customerIdUser;
// 	}
// 	public MobileEnum getServiceType() {
// 		return serviceType;
// 	}
// 	public void setServiceType(MobileEnum serviceType) {
// 		this.serviceType = serviceType;
// 	}
// 	public Long getServiceStartDate() {
// 		return serviceStartDate;
// 	}
// 	public void setServiceStartDate(Long serviceStartDate) {
// 		this.serviceStartDate = serviceStartDate;
// 	}
// 	public Date getCreationAt() {
// 		return creationAt;
// 	}

// 	public void setCreationAt(Date creationAt) {
// 		this.creationAt = creationAt;
// 	}

// 	// Equals and Hashcode
// 	@Override
// 	public boolean equals(Object o) {
// 		if (o == this)
// 			return true;
// 		if (!(o instanceof Subscriber)) {
// 			return false;
// 		}
// 		Subscriber subscriber = (Subscriber) o;
// 		return Objects.equals(id, subscriber.id) && Objects.equals(msisdn, subscriber.msisdn) && Objects.equals(customerIdOwner, subscriber.customerIdOwner) && Objects.equals(customerIdUser, subscriber.customerIdUser) && Objects.equals(serviceType, subscriber.serviceType) && Objects.equals(serviceStartDate, subscriber.serviceStartDate) && Objects.equals(creationAt, subscriber.creationAt);
// 	}

// 	@Override
// 	public int hashCode() {
// 		return Objects.hash(id, msisdn, customerIdOwner, customerIdUser, serviceType, serviceStartDate, creationAt);
// 	}
	

// 	@Override
// 	public String toString() {
// 		return "{" +
// 			" id='" + getId() + "'" +
// 			", msisdn='" + getMsisdn() + "'" +
// 			", customerIdOwner='" + getCustomerIdOwner() + "'" +
// 			", customerIdUser='" + getCustomerIdUser() + "'" +
// 			", serviceType='" + getServiceType() + "'" +
// 			", serviceStartDate='" + getServiceStartDate() + "'" +
// 			", createdAt='" + getCreationAt() + "'" +
// 			"}";
// 	}
	
	
// }
