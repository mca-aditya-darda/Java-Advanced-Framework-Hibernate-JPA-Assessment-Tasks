package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_user_task15.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Profile implements Serializable {

    @Id
    private Integer id;

    private String phone;
    private String gender;
    private LocalDate dob;

    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, gender, dob);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Profile)) return false;
        Profile other = (Profile) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Phone: " + phone +
               "\n  Gender: " + gender +
               "\n  DOB: " + dob;
    }
}