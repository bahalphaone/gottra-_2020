package com.altran.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpecExtractor {

	@Value("${python.executable:python}")
	private String pythonExe;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public void extractSpec(File file, String projectType, Long fileId) {
					
		File scriptFile = new File("python", "extraction.py");
		
		try{
			List<String> args = new ArrayList<>(Arrays.asList(pythonExe, scriptFile.getPath(), file.getAbsolutePath().replace("\\", "/"), projectType));
			if (fileId != null) {
				args.add(fileId.toString());
			}
			log.info("processBuilder: {}", args);
			ProcessBuilder builder = new ProcessBuilder(args);
			builder.redirectErrorStream(true);
			System.out.println(args);

			Process p = builder.start();
			InputStream stdout = p.getInputStream();
			BufferedReader reader = new BufferedReader (new InputStreamReader(stdout, "UTF-8"));

			String line;
			while ((line = reader.readLine ()) != null) {
				System.out.println (line);
			}
		} catch (Exception e){
			System.out.println("Erreur python");
			e.printStackTrace();
		}
	}
	
	/*
	 * test function
	 */
	public static void main(String[] args) {
		File file = new File("reference", "spec_test.txt"); //relative to project
		System.out.println(file);
		SpecExtractor specExtractor = new SpecExtractor();
		specExtractor.pythonExe = "C:/Users/Rabii/AppData/Local/Programs/Python/Python36/python";
		specExtractor.extractSpec(file, "standard", 1L);
	}
}
