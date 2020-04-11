/**
 * @author filipe.pinheiro, 29/09/2018
 */
package br.com.reward.exception;

public class AuthorizationException extends RuntimeException {

    
	/**
	 *
	 */
	private static final long serialVersionUID = -5588376162391194288L;

	public AuthorizationException(String msg) {
		super(msg);
	}
	
}
