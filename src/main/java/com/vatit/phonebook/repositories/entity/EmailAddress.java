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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author buhake.sindi
 *
 */
@Entity
@Table(name="EMAIL_ADDRESSES")
@Access(AccessType.FIELD)
public class EmailAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8243420628863446123L;

	@Id
	@Column(name="ID", nullable=false)
	private String id;
	
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
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
