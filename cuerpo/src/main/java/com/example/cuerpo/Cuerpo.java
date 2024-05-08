package com.example.cuerpo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Cuerpo {

//	@Id
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cuerpo;
	private String titulacion;
	private String requisitos_edad;
	private String pais;
	private String photo;
	private String pdf;
	
	public Cuerpo() {
		
	}

	public Cuerpo(Long id, String cuerpo, String titulacion, String requisitos_edad, String pais, String photo,
			String pdf) {
		this.id = id;
		this.cuerpo = cuerpo;
		this.titulacion = titulacion;
		this.requisitos_edad = requisitos_edad;
		this.pais = pais;
		this.photo = photo;
		this.pdf = pdf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}

	public String getRequisitos_edad() {
		return requisitos_edad;
	}

	public void setRequisitos_edad(String requisitos_edad) {
		this.requisitos_edad = requisitos_edad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
}
