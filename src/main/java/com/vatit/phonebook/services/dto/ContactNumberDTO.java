/**
 * 
 */
package com.vatit.phonebook.services.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
public class ContactNumberDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2581213285543562010L;

	@XmlElement
	private long id;
	
	@Pattern(regexp="^(\\+?\\d{1,3}|\\d{1,4})$")
	@Size(min=3, max=4)
	@XmlElement(name="country_code")
	private String countryCode;
	
	@Pattern(regexp="\\d{7}")
	@Size(min=7, max=7)
	@XmlElement(name="contact_number")
	private String contactNumber;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
