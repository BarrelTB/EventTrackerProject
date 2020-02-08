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

	private String desc;

	@Column(name = "thc_percentage")
	private int thcPercentage;

	@Column(name = "cbd_percentage")
	private int cbdPercentage;

	@OneToMany
	@JsonIgnore
	private List<Sesh> sessions;

	public Strain() {
		super();
	}

	public Strain(String name, String desc, int thcPercentage, int cbdPercentage, List<Sesh> sessions) {
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

	public int getThcPercentage() {
		return thcPercentage;
	}

	public void setThcPercentage(int thcPercentage) {
		this.thcPercentage = thcPercentage;
	}

	public int getCbdPercentage() {
		return cbdPercentage;
	}

	public void setCbdPercentage(int cbdPercentage) {
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
