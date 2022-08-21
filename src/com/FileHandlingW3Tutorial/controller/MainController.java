package com.FileHandlingW3Tutorial.controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
	
	private static Logger logger = Logger.getLogger(MainController.class.getName());

	public static void main(String[] args) {
		try {
			String baseDirectory = System.getProperty("user.dir");
			String fileName = "filename.txt";
			
			logger.log(Level.INFO, baseDirectory);
			
			String fileDestination = baseDirectory + "\\src\\resources\\";
			logger.log(Level.INFO, fileDestination);
			logger.log(Level.INFO, fileDestination+fileName);
			
			File directory = new File(fileDestination);
			File objectFile = new File(fileDestination + fileName);
			
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			if(objectFile.createNewFile()) {
				System.out.println("File created: " + objectFile.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch(IOException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}

	}

}
