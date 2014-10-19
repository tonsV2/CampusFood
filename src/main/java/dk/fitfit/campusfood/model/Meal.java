package dk.fitfit.campusfood.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import dk.fitfit.campusfood.utils.DateUtil;

@Entity
public class Meal extends BaseEntity {
	private String name;
	private Date dateOfServing;

	@ManyToOne(fetch = FetchType.EAGER)
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
		// TODO: is this ok? DateUtil.removeTime that is...
		this.dateOfServing = DateUtil.removeTime(dateOfServing);
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
		Meal other = (Meal) obj;
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
