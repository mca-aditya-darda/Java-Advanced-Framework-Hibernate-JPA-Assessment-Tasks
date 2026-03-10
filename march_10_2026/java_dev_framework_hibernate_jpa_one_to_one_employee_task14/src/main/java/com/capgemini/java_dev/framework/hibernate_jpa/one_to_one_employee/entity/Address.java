package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_employee.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Address implements Serializable {

    @Id
    private Integer id;

    private String street;
    private String city;
    private String zipcode;

    @OneToOne(mappedBy = "address")
    private Employee employee;

    public Address() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, zipcode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(street, other.street) &&
               Objects.equals(city, other.city) &&
               Objects.equals(zipcode, other.zipcode);
    }

    @Override
    public String toString() {
        return "Address [id=" + id +
               ", street=" + street +
               ", city=" + city +
               ", zipcode=" + zipcode + "]";
    }

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
}