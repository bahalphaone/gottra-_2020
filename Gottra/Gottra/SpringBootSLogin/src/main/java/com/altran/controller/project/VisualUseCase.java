package com.altran.controller.project;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.altran.model.UseCase;

public class VisualUseCase {

	public Long id;
	public String name;
	public List<VisualAction> actions;	

	public VisualUseCase(UseCase useCase) {
		id = useCase.getId();
		name = Arrays.asList(useCase.getName(), useCase.getTitle())
				.stream()
				.filter(s -> s != null)
				.collect(Collectors.joining(": "));
	}

}
