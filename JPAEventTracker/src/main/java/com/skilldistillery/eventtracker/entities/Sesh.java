package com.skilldistillery.eventtracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sesh {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	@Column(name = "time_length")
	private String timeLength;

	private String desc;

	@ManyToOne
	@JoinColumn(name = "strain_id")
	private Strain strain;

	public Sesh() {
		super();
	}

	public Sesh(String title, String timeLength, String desc, Strain strain) {
		super();
		this.title = title;
		this.timeLength = timeLength;
		this.desc = desc;
		this.strain = strain;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(String timeLength) {
		this.timeLength = timeLength;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Strain getStrain() {
		return strain;
	}

	public void setStrain(Strain strain) {
		this.strain = strain;
	}

	@Override
	public String toString() {
		return "Sesh [id=" + id + ", title=" + title + ", timeLength=" + timeLength + ", desc=" + desc + ", strain="
				+ strain + "]";
	}

}
