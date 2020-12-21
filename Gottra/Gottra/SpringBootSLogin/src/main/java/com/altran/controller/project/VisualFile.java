package com.altran.controller.project;

import java.util.List;

import com.altran.model.SpecFile;

public class VisualFile {

	public String name;
	public List<VisualUseCase> useCases;

	public VisualFile(SpecFile specFile) {
		name = specFile.getName();
	}

}
