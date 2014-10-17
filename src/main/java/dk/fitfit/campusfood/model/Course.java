package dk.fitfit.campusfood.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Course extends BaseEntity {
	private String name;
	private Date dateOfServing;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfServing() {
		return dateOfServing;
	}
	public void setDateOfServing(Date dateOfServing) {
		this.dateOfServing = dateOfServing;
	}
}
