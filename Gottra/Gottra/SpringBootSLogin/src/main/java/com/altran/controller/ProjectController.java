package com.altran.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altran.controller.project.VisualAction;
import com.altran.controller.project.VisualFile;
import com.altran.controller.project.VisualUseCase;
import com.altran.model.Action;
import com.altran.model.SpecFile;
import com.altran.model.SpecProject;
import com.altran.model.UseCase;
import com.altran.repository.ActionRepository;
import com.altran.repository.SpecProjectRepository;
import com.altran.repository.UseCaseRepository;

@Controller
public class ProjectController {

	@Autowired
	SpecProjectRepository projectRepository;
	
	@Autowired
	UseCaseRepository useCaseRepository;

	@Autowired
	ActionRepository actionRepository;

    @RequestMapping(value="/newproject", method=RequestMethod.GET)  
    public String newProject(Model model) {
        return "project/newproject";
    }
    

    @RequestMapping(value="/newproject", method=RequestMethod.POST)
    public String createProject(@RequestParam("projectTitle") String title, @RequestParam("projectType") String type) {
    	SpecProject specProject = new SpecProject(title, type);
    	projectRepository.save(specProject);
    	return "redirect:/addfiles/" + specProject.getId();
    }
    

    @RequestMapping(value="/projects", method=RequestMethod.GET)  
    public String projects(Model model) {
    	List<SpecProject> projects = projectRepository.findAll();
    	model.addAttribute("projects", projects);
        return "project/projects";
    }

    
    @RequestMapping(value="/addfiles/{id}", method=RequestMethod.GET)  
    public String addFiles(Model model, @PathVariable Long id) {
    	SpecProject project = projectRepository.getOne(id);
    	if (project == null) {
            return "redirect:/newproject"; 
    	}
		model.addAttribute("projectTitle", project.getTitle());
		model.addAttribute("projectId", project.getId());
        return "project/addfiles";
    }
    
    
    @RequestMapping(value="/project/{id}", method=RequestMethod.GET)  
    public String project(Model model, @PathVariable Long id) {
    	SpecProject project = projectRepository.getOne(id);
    	if (project == null) {
            return "redirect:/newproject"; 
    	}
		model.addAttribute("projectTitle", project.getTitle());
		model.addAttribute("projectId", project.getId());
		
		List<VisualFile> files = new ArrayList<>();
		model.addAttribute("specFiles", files);
		
		for (SpecFile specFile: project.getSpecFiles()) {
			
			VisualFile visualFile = new VisualFile(specFile);
			visualFile.useCases = fetchUseCases(specFile);						
			
			files.add(visualFile);
		}
		
        return "project/project";
    }

    
    @RequestMapping(value="/runtests", method=RequestMethod.POST)  
    @ResponseBody
    public String addFiles(@RequestParam("projectId") Long projectId, @RequestParam("testIds") String testIds) {
    	return String.format("Should run tests for project %d, testIds=%s", projectId, testIds);    	
    }
    
    
	private List<VisualUseCase> fetchUseCases(SpecFile specFile) {
		List<UseCase> useCases = useCaseRepository.findByFileId(specFile.getId());
		List<VisualUseCase> vUseCases = new ArrayList<>();
		
		for (UseCase useCase: useCases) {
			VisualUseCase visualUseCase = new VisualUseCase(useCase);
//			visualUseCase.actions = fetchActions(useCase);
			vUseCases.add(visualUseCase);
		}
		return vUseCases;
	}


	@SuppressWarnings("unused")
	private List<VisualAction> fetchActions(UseCase useCase) {
		List<Action> actions = actionRepository.findByUseCaseId(useCase.getId());
		List<VisualAction> vActions = new ArrayList<>();

		for (Action action: actions) {
			vActions.add(new VisualAction(action));
		}
		
		return vActions;
		
	}
}
