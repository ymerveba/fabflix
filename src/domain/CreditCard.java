package domain;

import java.sql.Date;

public class CreditCard {
	public String firstName;
	public String lastName;
	public String creditCard;
	public Date expiration;
	
	public CreditCard (String firstName, String lastName, String creditCard, Date expiration) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.creditCard = creditCard;
		this.expiration = expiration;
	}
}
