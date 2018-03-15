/**
 * 
 */
package com.vatit.phonebook.services.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author buhake.sindi
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneBookDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3502803934041916396L;

	@XmlElement(name="user")
	private UserDTO user;
	
	@XmlElement(name="contact_numbers")
	private List<ContactNumberDTO> contactNumbers;
	
	@XmlElement(name="email_addresses")
	private List<String> emailAddresses;
	
	/**
	 * 
	 */
	public PhoneBookDTO() {
		super();
		// TODO Auto-generated constructor stub
		user = new UserDTO();
	}

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

	/**
	 * @return the contactNumbers
	 */
	public List<ContactNumberDTO> getContactNumbers() {
		return contactNumbers;
	}

	/**
	 * @param contactNumbers the contactNumbers to set
	 */
	public void setContactNumbers(List<ContactNumberDTO> contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	/**
	 * @return the emailAddresses
	 */
	public List<String> getEmailAddresses() {
		return emailAddresses;
	}

	/**
	 * @param emailAddresses the emailAddresses to set
	 */
	public void setEmailAddresses(List<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
}
