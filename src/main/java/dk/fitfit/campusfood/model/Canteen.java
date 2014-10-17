package dk.fitfit.campusfood.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Canteen extends BaseEntity {
	private static final Logger logger = LoggerFactory.getLogger(Canteen.class);

	private String name;

	// TODO: https://schuchert.wikispaces.com/JPA+Tutorial+1+-+Embedded+Entity
	// http://docs.oracle.com/javaee/6/api/javax/xml/registry/infomodel/PostalAddress.html
	//private Address address;
	//private PostalAddress postalAddress;
	/**
	 * In honor of KISS I simply use a simple string address as a holder for the restaurants address.
	 * The idea is that the string will contain an address which will be valid according to google maps.
	 * Same goes for openingHours, phoneNumber and homepage... KISS wise.
	 */
	private String address;
	private String openingHours;	// A string which will be presented within a pre tag
									// Eg. <pre>Mandag - Torsdag 10-22
									// Fredag - Lørdag 10-24
									// Søndag 11-20</pre>
	private String contact;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Course> courses = new HashSet<>();

	public Canteen() {
	}

	public Canteen(String name, String address, String openingHours, String contact) {
		this.name = name;
		this.address = address;
		this.openingHours = openingHours;
		this.contact = contact;
	}

	public Canteen(String name, String address, String openingHours, String contact, Set<Course> courses) {
		this.name = name;
		this.address = address;
		this.openingHours = openingHours;
		this.contact = contact;
		this.courses = courses;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getOpeningHours() {
		return openingHours;
	}
	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}

	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public boolean addCourse(Course course)
	{
		return getCourses().add(course);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		Canteen other = (Canteen) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
