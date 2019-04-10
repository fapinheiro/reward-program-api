// package mt.com.vodafone.service.impl;

// import java.util.Date;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Isolation;
// import org.springframework.transaction.annotation.Transactional;

// import mt.com.vodafone.repository.*;
// import mt.com.vodafone.service.SubscriberService;
// import mt.com.vodafone.exception.SubscriberNotFoundException;
// import mt.com.vodafone.entity.Subscriber;

// @Service
// public class SubscriberServiceImpl implements SubscriberService {

// 	@Autowired
// 	private SubscriberRepository dao;

// 	// API
// 	public Subscriber save(final Subscriber subscriber) throws Throwable {
// 		subscriber.setCreationAt(new Date());
// 		return dao.save(subscriber);
// 	}

// 	public Subscriber findById(final Integer id) throws Throwable {
// 		return dao.findById(id).orElseThrow(() -> {
// 			throw new SubscriberNotFoundException(String.format("A subscriber of id {%d} not found for selecting", id));
// 		});
// 	}

// 	public Iterable<Subscriber> findAll() {
// 		return dao.findAll();
// 	}

// 	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
// 	public Subscriber merge(final Integer id, final Subscriber newSubscriber) throws Throwable {
// 		return dao.findById(id).map(subscriber -> {
// 			subscriber.setServiceType(newSubscriber.getServiceType());
// 			return dao.save(subscriber);
// 		}).orElseThrow(() -> {
// 			throw new SubscriberNotFoundException(String.format("A subscriber of id {%d} not found for updating", id));
// 		});
// 	}

// 	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
// 	public void delete(final Integer id) throws Throwable {
// 		dao.findById(id).orElseThrow(() -> {
// 			throw new SubscriberNotFoundException(String.format("A subscriber of id {%d} not found for deleting", id));
// 		});
// 		//Thread.sleep(4000);
// 		dao.deleteById(id);
// 	}
// }
