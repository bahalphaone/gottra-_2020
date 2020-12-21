package com.altran.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "projet")
public class SpecProject {
	
	@Id
	@Column(name = "ID_projet")	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		
	
	@Column(name = "titre")
	@NotEmpty
	@Size(min=1, max=100)	
	private String title;

	
	@Column(name = "type")
	@NotEmpty
	@Size(min=1, max=50)	
	private String type;
	
	
	@OneToMany(mappedBy = "project")
	@OrderBy("id")	
	Set<SpecFile> specFiles;
	
	public SpecProject() {
		super();
	}
	
	public SpecProject(String title, String type) {
		this();
		this.title = title;
		this.type = type;
	}

		
	public Long getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public Set<SpecFile> getSpecFiles() {
		return specFiles;
	}	
}
