package com.altran.model;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.altran.processor.FileConverter;
import com.altran.repository.SpecFileRepository;

@Component
public class SpecFileHelper {
	
	private String storageDir;

	@Autowired
	private SpecFileRepository specFileRepository;	

	@Autowired
	private EntityManager entityManager;
	
	public SpecFileHelper(@Value("${dir.files}") String storageDir) {
		this.storageDir = storageDir;
	}

	public SpecFile persistSpecFile(String filename, Long projectId) {
		
		SpecFile specFile = new SpecFile(filename);
		
		if (projectId != null) {
			SpecProject project = entityManager.getReference(SpecProject.class, projectId); // does not fetch project
			specFile.project = project;
		}
		specFileRepository.save(specFile);
		
		return specFile;
	}

	public void saveOriginalFile(MultipartFile uploadedFile, SpecFile specFile) throws IllegalStateException, IOException {
		File file = getOriginalFile(specFile);
		System.out.println("Saving to : "+ file);
		uploadedFile.transferTo(file);
	}
	
	public boolean convertToTxtFile(SpecFile specFile) throws IOException {
		return new FileConverter().convertToTxt(getOriginalFile(specFile), getTxtFile(specFile));		
	}

	public File getOriginalFile(SpecFile specFile) {
		return new File(getOriginalDir(), getFilename(specFile));
	}
	
	
	public File getTxtFile(SpecFile specFile) {

		String filename = getFilename(specFile);
		String txtFilename; 
		int indexOfExtension = FilenameUtils.indexOfExtension(filename);
		if (indexOfExtension > 0) {
			txtFilename = filename.substring(0, indexOfExtension + 1) + "txt";
		}
		else {
			txtFilename = filename + ".txt";
		}

		return new File(getTxtDir(), txtFilename);	
	}


	public void ensureDirectoriesExist() {
		getOriginalDir().mkdirs();
		getTxtDir().mkdirs();
	}	
	
	private File getOriginalDir() {
		return new File(storageDir, "original");
	}
	
	private File getTxtDir() {
		return new File(storageDir, "txt");
	}

	private static String getFilename(SpecFile specFile) {		
		return specFile.getId() + "_" + specFile.getName();
	}
}
