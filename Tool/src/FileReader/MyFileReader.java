package FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import CodeStructure.ClassProperties;

public class MyFileReader {
	
	private ArrayList<String> fileList;
	
	private String projectDirectory;
	
	// Author: Shuvo Saha
	public MyFileReader (String currentProjectDirectory) {
		this.projectDirectory = currentProjectDirectory;
	}
	
	// Author: Shuvo Saha
	public ArrayList<String> listOfPackageFolders (){
		String projectSource = projectDirectory+"\\src";
		ArrayList<String> packageFolders = new ArrayList<String>();
		File folder = new File(projectSource);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if(file.isDirectory()) {
				packageFolders.add(file.getName());
			}
		}
		return packageFolders;
	}
	
	// Author: Shuvo Saha
	private boolean isJavaFile (String fileName) {
		
		String extension = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		
		return extension.equals("java");
	}
	
	// Author: Shuvo Saha
	public ClassProperties readJavaFile (String packageName,String fileName) {
		
		String rawCode = "";
		try {
			rawCode = new String(Files.readAllBytes(Paths.get(projectDirectory+"\\src\\"+packageName+"\\"+fileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Eguli ektao successfully white space remove kore nai
		rawCode.replaceAll("\\s+","");
		rawCode.trim();
		//////////////////////////////////////////////////////
		
		Integer loc = (int) rawCode.chars().filter(ch -> ch ==';').count();
		String className = fileName.split("\\.")[0];
		return new ClassProperties(loc,rawCode,packageName,className);
	}
	
	// Author: Shuvo Saha
	public ArrayList<ClassProperties> getClassPropertiesOfPackageFiles(String packageName){
		ArrayList<ClassProperties> classPropertiesOfFilesInPackage = new ArrayList<ClassProperties>();
		
		String packageSource = projectDirectory+"\\src\\"+packageName;
		File folder = new File(packageSource);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if(!file.isDirectory()) {
				if(isJavaFile(file.getName())) {
					classPropertiesOfFilesInPackage.add(readJavaFile(packageName, file.getName()));
				}
			}
		}
		return classPropertiesOfFilesInPackage;
	}
	
	// Author: Aquib Azmain
	private void listFilesForFolder(File folder) {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	fileList.add(fileEntry.getAbsolutePath());
	        }
	    }
	    
	    for(String filename: fileList) {
	    	System.out.println(filename);
	    }
	}
	
	// Author: Aquib Azmain
	public ArrayList<String> manageFileReader() {
		String projectName = "";
		System.out.println("Enter folder name: ");
		Scanner input = new Scanner(System.in);
		projectName = input.nextLine();
		fileList = new ArrayList<>();
		String workingDir = System.getProperty("user.dir");
		File file = new File(workingDir);
		String parentDir = file.getParent();
		File folder = new File(parentDir+ "/" + projectName + "/src");
		listFilesForFolder(folder);
		input.close();
		
		return fileList;
	}

}
