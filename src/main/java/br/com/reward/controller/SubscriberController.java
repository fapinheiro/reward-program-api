// /**
//  * @author filipe.pinheiro, 29/09/2018
//  */
// package mt.com.vodafone.controller;

// import javax.validation.Valid;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import mt.com.vodafone.entity.*;
// import mt.com.vodafone.service.SubscriberService;

// @RestController
// @RequestMapping("/api")
// @CrossOrigin("*")
// public class SubscriberController {

// 	private final Logger LOG = LoggerFactory.getLogger(SubscriberController.class);

// 	@Autowired
// 	private SubscriberService service;

// 	@GetMapping(path = "/subscribers")
// 	public Iterable<Subscriber> getAllMobileSubscribers() throws Throwable {
// 		return service.findAll();
// 	}

// 	@GetMapping(path = "/subscribers/{id}")
// 	public Subscriber getMobileSubscriberById(@PathVariable Integer id) throws Throwable {
// 		LOG.debug("id", id);
// 		return service.findById(id);
// 	}

// 	@PostMapping(path = "/subscribers")
// 	public Subscriber addMobileSubscriber(@Valid @RequestBody Subscriber subscriber) throws Throwable {
// 		LOG.debug("subscriber", subscriber);
// 		return service.save(subscriber);
// 	}

// 	@PutMapping("/subscribers/{id}")
// 	public Subscriber updateMobileSubscriber(@Valid @RequestBody Subscriber newSubscriber, @PathVariable Integer id)
// 			throws Throwable {
// 		LOG.debug("id", id);
// 		LOG.debug("newSubscriber", newSubscriber);
// 		return service.merge(id, newSubscriber);
// 	}

// 	@DeleteMapping("/subscribers/{id}")
// 	public ResponseEntity<Subscriber> deleteMobileSubscriber(@PathVariable Integer id) throws Throwable {
// 		LOG.debug("id", id);
// 		service.delete(id);
// 		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
// 	}
// }
