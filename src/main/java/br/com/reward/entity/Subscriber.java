// /**
//  * @author filipe.pinheiro, 29/09/2018
//  */
// package mt.com.vodafone.entity;

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


// @Entity(name="subscriber")
// @SequenceGenerator(sequenceName="seq_subscriber", name = "seq_subscriber")
// @JsonIgnoreProperties(value = {"creationAt"}, allowGetters = true)
// public class Subscriber {

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
