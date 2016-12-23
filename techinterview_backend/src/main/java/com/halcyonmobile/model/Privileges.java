package com.halcyonmobile.model;

import javax.persistence.*;

@Entity
@Table(name = "privileges")
public class Privileges {
	
	@Id
	@Column(name = "idprivileges")
	private String idprivileges;
	
	@Column(name = "nameprivileges")
	private String nameprivileges;

	public String getIdprivileges() {
		return idprivileges;
	}

	public void setIdprivileges(String idprivileges) {
		this.idprivileges = idprivileges;
	}

	public String getNameprivileges() {
		return nameprivileges;
	}

	public void setNameprivileges(String nameprivileges) {
		this.nameprivileges = nameprivileges;
	}
}
