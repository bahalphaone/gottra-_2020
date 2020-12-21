package com.altran.model;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.altran.model.SpecFile;
import com.altran.model.SpecFileHelper;

public class SpecFileHelperTest {

	private SpecFileHelper fileHelper;

	@Before
	public void setup(){		
		fileHelper = new SpecFileHelper("dir");
	}
	
	@Test
	public void testGetTxtFilename() {		
		SpecFile specFile = Mockito.mock(SpecFile.class);
		Mockito.when(specFile.getName()).thenReturn("file.pdf");
		Mockito.when(specFile.getId()).thenReturn(1L);
		
		File txtFile = fileHelper.getTxtFile(specFile);
		
		Assert.assertEquals("dir/txt/1_file.txt", txtFile.getPath());
	}

	@Test
	public void testNoExtFilename() {		
		SpecFile specFile = Mockito.mock(SpecFile.class);
		Mockito.when(specFile.getName()).thenReturn("file");
		Mockito.when(specFile.getId()).thenReturn(1L);
		
		File txtFile = fileHelper.getTxtFile(specFile);
		
		Assert.assertEquals("dir/txt/1_file.txt", txtFile.getPath());
	}	
}
