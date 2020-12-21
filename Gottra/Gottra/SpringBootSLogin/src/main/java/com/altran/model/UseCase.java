package com.altran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "usecase")
public class UseCase {
	
	@Id
	@Column(name = "ID_cas", columnDefinition = "int(11) unsigned")	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
			
	@Column(name = "nom")
	@NotEmpty
	@Size(max=100)	
	private String name;

	@Column(name = "titre")
	@NotEmpty
	@Size(max=100)	
	private String title;

	@Column(name = "ID_fichier", columnDefinition = "int(11) unsigned")
	@NotEmpty
	Long specFileId;


	public UseCase() {
	}
	

	public Long getId() {
		return id;
	}
	

	public String getName() {
		return name;
	}

	
	public String getTitle() {
		return title;
	}


}
