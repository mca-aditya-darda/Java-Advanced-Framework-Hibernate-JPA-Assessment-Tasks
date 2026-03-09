package com.capgemini.java_dev.framework.hibernate_jpa.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String mailid;
    private Long contactNumber;

    public Student() {
    }

    public Student(Integer id, String name, String mailid, Long contactNumber) {
        this.id = id;
        this.name = name;
        this.mailid = mailid;
        this.contactNumber = contactNumber;
    }

    // Getters

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMailid() {
        return mailid;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    // Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    // toString Override

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", mailid=" + mailid + ", contactNumber=" + contactNumber + "]";
    }

    // hashCode Override

    @Override
    public int hashCode() {
        return Objects.hash(contactNumber, id, mailid, name);
    }

    // equals Override

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(mailid, other.mailid)
                && Objects.equals(contactNumber, other.contactNumber);
    }
}