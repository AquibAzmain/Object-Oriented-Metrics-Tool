package Manager;

import java.io.File;
import java.util.ArrayList;

import CodeStructure.ProjectProperties;
import FileReader.MyFileReader;

public class MyMain {

	public static void main(String[] args) {
		
		//ArrayList<String> fileList;
		ProjectProperties mySceneryProperties = new ProjectProperties();
		
		//Please change project directory before running this
		MyFileReader myFileReader = new MyFileReader("E:\\Object-Oriented-Metrics-Tool\\MyScenery");
		
		mySceneryProperties.packages = myFileReader.listOfPackageFolders();
		for (String packageName : mySceneryProperties.packages) {
			mySceneryProperties.classProperties.addAll(myFileReader.getClassPropertiesOfPackageFiles(packageName));
		}
		mySceneryProperties.printAllProjectProperties();
		
		//fileList = myFileReader.manageFileReader();
	}

}
