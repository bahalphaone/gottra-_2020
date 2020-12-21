package com.altran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "fichier")
public class SpecFile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		
	@Column(name = "nom")
	@NotEmpty
	@Size(min=1, max=255)	
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_projet", columnDefinition = "int(11) unsigned")
	SpecProject project;

	
	public SpecFile() {
	}
	
	public SpecFile(String name) {
		this();
		this.name = name;
	}
		
	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}
}
