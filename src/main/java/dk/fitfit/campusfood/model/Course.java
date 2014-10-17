package dk.fitfit.campusfood.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Course extends BaseEntity {
	private String name;
	private Date dateOfServing;

	@ManyToOne
	private Canteen canteen;
	
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

	public Canteen getCanteen() {
		return canteen;
	}
	public void setCanteen(Canteen canteen) {
		this.canteen = canteen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((canteen == null) ? 0 : canteen.hashCode());
		result = prime * result
				+ ((dateOfServing == null) ? 0 : dateOfServing.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (canteen == null) {
			if (other.canteen != null)
				return false;
		} else if (!canteen.equals(other.canteen))
			return false;
		if (dateOfServing == null) {
			if (other.dateOfServing != null)
				return false;
		} else if (!dateOfServing.equals(other.dateOfServing))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
