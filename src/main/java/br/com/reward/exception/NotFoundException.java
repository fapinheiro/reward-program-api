/**
 * @author filipe.pinheiro, 29/09/2018
 */
package br.com.reward.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1420419282914708215L;
    
	public NotFoundException(String msg) {
		super(msg);
	}
	
}
