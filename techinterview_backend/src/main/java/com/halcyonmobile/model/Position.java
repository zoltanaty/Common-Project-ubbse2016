package com.halcyonmobile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position {
	
	@Id
    @Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;

	public Position() {
		super();
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

	@Override
	public String toString() {
		return "Position [id=" + id + ", name=" + name + "]";
	}
}
