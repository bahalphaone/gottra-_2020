package com.altran.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.altran.model.SpecFile;
import com.altran.model.SpecFileHelper;
import com.altran.model.SpecProject;
import com.altran.processor.SpecExtractor;
import com.altran.repository.SpecProjectRepository;


@Controller
public class UploadController {

	@Value("${dir.files}")
	private String uploadingdir;

	@Autowired
	private SpecFileHelper specFileHelper;

	@Autowired
	private SpecExtractor specExtractor;	

	@Autowired
	private SpecProjectRepository projectRepository;	
	
	@RequestMapping(value = {"/fileUpload2"}, method = RequestMethod.POST)
	@ResponseBody	
	public String fileUploadPost(
			@RequestParam("qqfile") MultipartFile[] uploadingFiles,
			@RequestParam(value = "projectId", required = false) Long projectId) throws IOException {

		specFileHelper.ensureDirectoriesExist();

		// ================================
		// Transfert des fichiers
		// ================================
		assert uploadingFiles.length == 1;
		MultipartFile uploadedFile = uploadingFiles[0];

		SpecFile specFile = specFileHelper.persistSpecFile(uploadedFile.getOriginalFilename(), projectId);

		specFileHelper.saveOriginalFile(uploadedFile, specFile);
 
		specFileHelper.convertToTxtFile(specFile);
		System.out.println("la spec est "+specFile.toString()+"Voila");

		SpecProject project = projectRepository.getOne(projectId);
		specExtractor.extractSpec(specFileHelper.getTxtFile(specFile), project.getType(), specFile.getId());

		return String.format("{\"success\":true, \"specFileId\": %d}", specFile.getId()); 
	}
}
