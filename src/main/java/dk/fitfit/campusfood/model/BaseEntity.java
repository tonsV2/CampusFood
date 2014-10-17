package dk.fitfit.campusfood.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	private Date createdAt;
	private Date updatedAt;

	// TODO: http://stackoverflow.com/a/11174297/672009
	// Using the above we wouldn't have to created a CommentRepository
	// Is that a good idea?
	
	/**
	 * http://www.devsniper.com/base-entity-class-in-jpa/
	 */
	/** 
	 * Sets createdAt before insert
	 */
	@PrePersist
	public void setCreationDate() {
		this.setCreatedAt(new Date());
	}

	/** 
	 * Sets updatedAt before update 
	 */
	@PreUpdate
	public void setChangeDate() {
		this.setUpdatedAt(new Date());
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	protected void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	protected void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}