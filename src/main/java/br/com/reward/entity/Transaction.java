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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.reward.validator.CreationValidator;

@Entity
@Table(name="transactions")
@SequenceGenerator(sequenceName="seq_transactions", name = "seq_transactions")
public class Transaction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8463628419619130689L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_transactions")
    private Integer transactionsId;

    @NotNull
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    private Integer status;

    private Integer amount;

    private Integer transactionType;

    @JsonIgnore
    @NotNull(groups=CreationValidator.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    public Integer getTransactionsId() {
        return this.transactionsId;
    }

    public void setTransactionsId(Integer transactionsId) {
        this.transactionsId = transactionsId;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
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
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        return Objects.equals(transactionsId, transaction.transactionsId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(transactionsId);
    }


}
