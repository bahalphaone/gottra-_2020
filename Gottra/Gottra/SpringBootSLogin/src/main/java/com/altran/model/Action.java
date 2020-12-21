package com.altran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "action")
public class Action {
	
	@Id
	@Column(name = "ID_action", columnDefinition = "int(11) unsigned")	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		
	
	@Column(name = "ID_cas", columnDefinition = "int(11) unsigned")
	@NotEmpty
	Long useCaseId;
	
	
	@Column(name = "full_text")
	@NotEmpty	
	private String fullText;


	public Action() {
	}
	
	
	public String getFullText() {
		return fullText;
	}

}
