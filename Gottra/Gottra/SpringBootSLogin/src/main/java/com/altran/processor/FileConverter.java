package com.altran.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class FileConverter {
	
	public boolean convertToTxt(File input, File output) throws IOException {
		String text = getContentText(input);
		boolean success = false;
		if (text != null) {
			FileOutputStream outputStream = new FileOutputStream(output);
			outputStream.write(text.getBytes("UTF-8"));
			outputStream.close();
			success = true;
		}
		return success;
	}
	
	private String getContentText(File f) throws IOException {
		String extension = FilenameUtils.getExtension(f.getAbsolutePath());
		String text = null;
		if(extension.equals("docx")) {
			text = getDocxTextContent(f);
		}
		else if (extension.equals("txt")) {
			text = getTxtTextContent(f);
		}
		else if (extension.equals("pdf")) {
			text = getPdfTextContent(f);
		}
		return text;
	}

	private static String getTxtTextContent(File file) throws FileNotFoundException, IOException, UnsupportedEncodingException {
		String text;
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		text = new String(data, "UTF-8");
		return text;
	}

	private static String getPdfTextContent(File f) throws IOException {
		PDDocument document = PDDocument.load(new File(f.getAbsolutePath()));
		if (document.isEncrypted()) {
			document.close();			
			throw new IOException("PDF is encrypted");
		}
		PDFTextStripper stripper = new PDFTextStripper();
		String text = stripper.getText(document);
		document.close();
		return text;
	}

	private static String getDocxTextContent(File f) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		XWPFDocument xdoc;
		try {
			xdoc = new XWPFDocument(OPCPackage.open(fis));
		} catch (InvalidFormatException e) {
			throw new IOException("Invalid docx:" + e.getMessage());
		}
		XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
		String text = extractor.getText();
		extractor.close();
		return text;
	}

	public static void fileRead(String name) {
		File f = new File(name);		
		try {
			String contentText = new FileConverter().getContentText(f);
			System.out.println(contentText);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {  // to be used as autonomous batch application 		
		try {
			{ 
				File f = new File("reference", "spec_test.pdf");
				String contentText = new FileConverter().getContentText(f);
				System.out.println("pdf size: " + contentText.length());
			}
			{ 
				File f = new File("reference", "spec_test.docx");
				String contentText = new FileConverter().getContentText(f);
				System.out.println("docx size: " + contentText.length());
			}

			{ 
				File f = new File("reference", "spec_test.txt");
				String contentText = new FileConverter().getContentText(f);
				System.out.println("txt size: " + contentText.length());
			}

//			{ 
//				File f = new File("reference", "spec_test.doc");
//				String contentText = new FileConverter().getContentText(f);
//				System.out.println("doc size: " + contentText.length());
//			}

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
