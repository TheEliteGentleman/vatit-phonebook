/**
 * 
 */
package com.vatit.phonebook.repositories.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.vatit.phonebook.repositories.entity.User;

/**
 * @author buhake.sindi
 *
 */
@Entity
@Table(name="CONTACT_NUMBERS")
@Access(AccessType.FIELD)
public class ContactNumber implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7333494386987016245L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false)
	private long id;
	
	@Pattern(regexp="^(\\+?\\d{1,3}|\\d{1,4})$")
	@Size(min=3, max=4)
	@Column(name="COUNTRY_CODE", nullable=false)
	private String countryCode;
	
	@Pattern(regexp="\\d{7}")
	@Size(min=7, max=7)
	@Column(name="CONTACT_NUMBER", nullable=false)
	private String contactNumber;
	
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable=false)
	private User owner;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_TIMESTAMP", updatable=false)
	private Date creationTimestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_MODIFICATION_TIMESTAMP", insertable=false)
	private Date lastModificationTimestamp;
	
	@PrePersist
	protected void preInsert() {
		if (creationTimestamp == null) {
			creationTimestamp = new Date();
		}
	}
	
	@PreUpdate
	protected void preUpdate() {
		lastModificationTimestamp = new Date();
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

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
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
	 * @return the lastModificationTimestamp
	 */
	public Date getLastModificationTimestamp() {
		return lastModificationTimestamp;
	}

	/**
	 * @param lastModificationTimestamp the lastModificationTimestamp to set
	 */
	public void setLastModificationTimestamp(Date lastModificationTimestamp) {
		this.lastModificationTimestamp = lastModificationTimestamp;
	}
}
