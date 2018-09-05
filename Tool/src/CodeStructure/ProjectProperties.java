package CodeStructure;

import java.util.ArrayList;

public class ProjectProperties {
	
	public ArrayList<String> packages;
	public ArrayList<ClassProperties> classProperties = new ArrayList<ClassProperties>();
	
	// Author: Shuvo Saha
	public void printAllProjectProperties () {
		System.out.println("Number of packages: "+packages.size());
		System.out.println("List of packages: ");
		for (String packageName : packages) {
			System.out.println(packageName);
		}
		System.out.println("Number of classes: "+ classProperties.size());
		for (ClassProperties classProperty : classProperties) {
			classProperty.printAllClassProperties();
		}
	}

}
