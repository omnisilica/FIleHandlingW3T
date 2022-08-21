package com.FileHandlingW3T.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class MainController {
	
	private static Logger logger = Logger.getLogger(MainController.class.getName());
	private static String fileDestination = System.getProperty("user.dir") + "\\src\\resources\\";
	private static JSONObject jsonObject;
	private static File directory;
	
	public static void main(String[] args) {
		
		
		String fileName = "filename.txt";
		
		logger.log(Level.INFO, fileDestination + ".\n");
		logger.log(Level.INFO, fileDestination + fileName + ".\n");
		
		directory = new File(fileDestination);
		
		String fileNamePath = fileDestination + fileName;
		
		File objectFile = new File(fileNamePath);
		
		JSONObject jsonFileObject = new JSONObject(); 
		
		directoryExists(directory);
		
		createTextFile(objectFile);
		
		writeToFile(fileNamePath, "File content.");
		
		readFile(objectFile);
		
		try {
			jsonFileObject = getFileInfo(objectFile);
		} catch (IOException e) {
			logger.log(Level.WARNING, "File probably doesn't exist.\n");
			e.printStackTrace();
		}
		
		updateFileInfo(jsonFileObject, objectFile);

	}
	
	public static void directoryExists(File directory) {
		if(!directory.exists()) {
			directory.mkdir();
		}
	}
	
	public static void createTextFile(File objectFile) {
		
		try {
			
			if(objectFile.createNewFile()) {
				logger.log(Level.INFO, "File created: " + objectFile.getName() + ".\n");
			} else {
				logger.log(Level.INFO, "File already exists." + "\n");
			}
			
			
		} catch(IOException e) {
			logger.log(Level.WARNING, "Something went wrong." + "\n");
			e.printStackTrace();
		}
		
	}
	
	public static void createJSONFile() {
		
		String jsonFileName = "fileInfoJSONData.json";
		String jsonFileNamePath = fileDestination + jsonFileName;
		File jsonFile = new File(jsonFileNamePath);
		
		try {
			
			if(jsonFile.createNewFile()) {
				logger.log(Level.INFO, "File created: " + jsonFile.getName() + ".\n");
			} else {
				logger.log(Level.INFO, "File already exists." + "\n");
			}
			
			
		} catch(IOException e) {
			logger.log(Level.WARNING, "Could not create json file." + "\n");
			e.printStackTrace();
		}
		
		writeToFile(jsonFileNamePath, jsonObject.toString(4));
		
	}
	
	public static void writeToFile(String fileNamePath, String fileContent) {
		
		try {
			FileWriter fileWriter = new FileWriter(fileNamePath);
			fileWriter.write(fileContent);
			fileWriter.close();
			
		} catch(IOException e) {
			logger.log(Level.WARNING, "Could not write to file." + "\n");
			e.printStackTrace();
		}
	}
	
	public static void readFile(File file) {
		try {
			Scanner fileReader = new Scanner(file);
			
			while(fileReader.hasNextLine()) {
				String fileLine = fileReader.nextLine();
				System.out.println(fileLine);
			}
			
			fileReader.close();
		} catch(IOException e) {
			logger.log(Level.WARNING, "Could not read file.\n");
			e.printStackTrace();
		}
		
	}
	
	public static JSONObject getFileInfo(File file) throws IOException {
		
		jsonObject = new JSONObject();
		
		if(file.exists()) {
			jsonObject.put("File name", file.getName());
			jsonObject.put("Writeable", file.canWrite());
			jsonObject.put("Readable", file.canRead());
			jsonObject.put("File size in bytes", file.length());
			
			logger.log(Level.INFO, jsonObject.toString(4));
		}
		
		createJSONFile();
		
		return jsonObject;
				
	}
	
	public static void updateFileInfo(JSONObject jsonFileObject, File file) {
		jsonFileObject.put("Can Execute", file.canExecute());
		
		logger.log(Level.INFO, "Updated file information.\n" + jsonFileObject.toString(4));
		
		createJSONFile();
	}

}
