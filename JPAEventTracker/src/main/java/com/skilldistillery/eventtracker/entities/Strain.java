package com.skilldistillery.eventtracker.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Strain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Column(name = "description")
	private String desc;

	@Column(name = "thc_percentage")
	private Integer thcPercentage;

	@Column(name = "cbd_percentage")
	private Integer cbdPercentage;

	@OneToMany(mappedBy = "strain")
	@JsonIgnore
	private List<Sesh> sessions;

	public Strain() {
		super();
	}

	public Strain(String name, String desc, Integer thcPercentage, Integer cbdPercentage, List<Sesh> sessions) {
		super();
		this.name = name;
		this.desc = desc;
		this.thcPercentage = thcPercentage;
		this.cbdPercentage = cbdPercentage;
		this.sessions = sessions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getThcPercentage() {
		return thcPercentage;
	}

	public void setThcPercentage(Integer thcPercentage) {
		this.thcPercentage = thcPercentage;
	}

	public Integer getCbdPercentage() {
		return cbdPercentage;
	}

	public void setCbdPercentage(Integer cbdPercentage) {
		this.cbdPercentage = cbdPercentage;
	}

	public List<Sesh> getSessions() {
		return sessions;
	}

	public void setSessions(List<Sesh> sessions) {
		this.sessions = sessions;
	}

	@Override
	public String toString() {
		return "Strain [id=" + id + ", name=" + name + ", desc=" + desc + ", thcPercentage=" + thcPercentage
				+ ", cbdPercentage=" + cbdPercentage + ", sessions=" + sessions + "]";
	}

	

}
