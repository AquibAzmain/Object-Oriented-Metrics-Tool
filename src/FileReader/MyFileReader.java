package FileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFileReader {
	private ArrayList<String> fileList;
	
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
