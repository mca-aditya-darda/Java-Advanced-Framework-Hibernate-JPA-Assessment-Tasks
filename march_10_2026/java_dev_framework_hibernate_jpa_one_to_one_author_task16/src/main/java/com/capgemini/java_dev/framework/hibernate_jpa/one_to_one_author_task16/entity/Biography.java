package com.capgemini.java_dev.framework.hibernate_jpa.one_to_one_author_task16.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Biography implements Serializable {

	@Id
	private Integer id;

	private String summary;
	private String birthPlace;
	private LocalDate birthDate;

	@OneToOne(mappedBy = "biography")
	private Author author;

	public Biography() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Biography))
			return false;
		Biography other = (Biography) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Biography [id=" + id + ", summary=" + summary + ", birthPlace=" + birthPlace + ", birthDate="
				+ birthDate + "]";
	}

}