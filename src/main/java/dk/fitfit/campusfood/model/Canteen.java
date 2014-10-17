package dk.fitfit.campusfood.model;

import javax.persistence.Entity;

@Entity
public class Canteen extends BaseEntity {
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
}
