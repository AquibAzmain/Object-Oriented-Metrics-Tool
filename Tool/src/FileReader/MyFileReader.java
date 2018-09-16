package FileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFileReader {
	private static ArrayList<String> fileList;
	
	private void listFilesForFolder(File folder) {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	fileList.add(fileEntry.getAbsolutePath());
	        }
	    }
	    
//	    for(String filename: fileList) {
//	    	System.out.println(filename);
//	    }
	}
	
	public ArrayList<String> manageFileReader() {
		if(fileList==null) {
			String projectName = "";
			//System.out.println("Enter folder name: ");
			//Scanner input = new Scanner(System.in);
			projectName = "JDeodorant-master";
			fileList = new ArrayList<>();
			String workingDir = System.getProperty("user.dir");
			File file = new File(workingDir);
			String parentDir = file.getParent();
			File folder = new File(parentDir+ "/" + projectName + "/src");
			//System.out.println(folder.getAbsolutePath());
			listFilesForFolder(folder);
			//input.close();
		}
		
		return fileList;
	}

}