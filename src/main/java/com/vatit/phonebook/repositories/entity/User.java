/**
 * 
 */
package com.vatit.phonebook.repositories.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author buhake.sindi
 *
 */
@Entity
@Table(name="USERS")
@Cacheable
@Access(AccessType.FIELD)
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6979092144458807328L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false)
	private long id;
	
	@Column(name="GIVEN_NAME", nullable=false)
	private String givenName;
	
	@Column(name="MIDDLE_NAME", nullable=false)
	private String middleName;
	
	@Column(name="FAMILY_NAME", nullable=false)
	private String familyName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_TIMESTAMP", updatable=false)
	private Date creationTimestamp;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="owner")
	private List<ContactNumber> contactNumbers;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="owner")
	private List<EmailAddress> emailAddresses;
	
	public void addContactNumber(ContactNumber number) {
		if (number != null) {
			if (contactNumbers == null) {
				contactNumbers = new ArrayList<>();
			}
			
			number.setOwner(this);
			contactNumbers.add(number);
		}
	}
	
	public void addEmailAddress(EmailAddress emailAddress) {
		if (emailAddress != null) {
			if (emailAddresses == null) {
				emailAddresses = new ArrayList<>();
			}
			
			emailAddress.setOwner(this);
			emailAddresses.add(emailAddress);
		}
	}
	
	@PrePersist
	protected void preInsert() {
		if (creationTimestamp == null) {
			creationTimestamp = new Date();
		}
	}

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
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the creationTimestamp
	 */
	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	/**
	 * @param creationTimestamp the creationTimestamp to set
	 */
	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	/**
	 * @return the contactNumbers
	 */
	public List<ContactNumber> getContactNumbers() {
		return contactNumbers;
	}

	/**
	 * @param contactNumbers the contactNumbers to set
	 */
	public void setContactNumbers(List<ContactNumber> contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	/**
	 * @return the emailAddresses
	 */
	public List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	/**
	 * @param emailAddresses the emailAddresses to set
	 */
	public void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
}
