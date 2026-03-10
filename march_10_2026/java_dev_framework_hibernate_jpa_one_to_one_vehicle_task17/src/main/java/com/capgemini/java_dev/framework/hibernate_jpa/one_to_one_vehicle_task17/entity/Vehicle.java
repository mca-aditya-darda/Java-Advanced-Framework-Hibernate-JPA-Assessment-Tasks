package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_vehicle_task17.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Vehicle implements Serializable {

    @Id
    private Integer id;

    private String brand;
    private String model;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="registration_id")
    private Registration registration;

    public Vehicle() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Vehicle)) return false;
        Vehicle other = (Vehicle) obj;
        return Objects.equals(id, other.id);
    }

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", brand=" + brand + ", model=" + model + ", registration=" + registration + "]";
	}
    
}