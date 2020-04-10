package br.com.reward.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RequestItemPK implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4530190323116953235L;

    @ManyToOne
	@JoinColumn(name="request_id")
	private Request request;

	@ManyToOne
	@JoinColumn(name="item_id")
	private Item item;

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RequestItemPK)) {
            return false;
        }
        RequestItemPK requestItemPK = (RequestItemPK) o;
        return Objects.equals(request, requestItemPK.request) && Objects.equals(item, requestItemPK.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, item);
    }


}