/**
 * @author filipe.pinheiro, 27/07/2019
*/
package br.com.reward.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="accounts")
@SequenceGenerator(sequenceName="seq_accounts", name = "seq_accounts")
public class Account implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -677239098521627508L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_accounts")
    private Integer accountId;

    private Integer scoreBalance;

    @JsonIgnore
    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JsonIgnore
	@OneToOne
	@JoinColumn(name="client_id")
	@MapsId
    private Client client;

    @JsonIgnore
    @OneToMany(mappedBy="client", fetch = FetchType.LAZY)
    private List<Transaction> transactionList = new ArrayList<>();

    public Integer getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getScoreBalance() {
        return this.scoreBalance;
    }

    public void setScoreBalance(Integer scoreBalance) {
        this.scoreBalance = scoreBalance;
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

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountId);
    }

}
