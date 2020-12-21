package com.altran.controller.project;

import com.altran.model.Action;

public class VisualAction {

	public String text;
	
	public VisualAction(Action action) {
		text = action.getFullText();
	}
	
}
