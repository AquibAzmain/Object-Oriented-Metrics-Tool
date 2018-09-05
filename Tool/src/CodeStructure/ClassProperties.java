package CodeStructure;

public class ClassProperties {
	
	public Integer lineOfCode;
	public String rawCode;
	public String packageName;
	public String className;
	
	// Author: Shuvo Saha
	public ClassProperties (Integer lineOfCode, String rawCode, String packageName, String className) {
		this.lineOfCode = lineOfCode;
		this.rawCode = rawCode;
		this.packageName = packageName;
		this.className = className;
	}
	
	// Author: Shuvo Saha
	public void printAllClassProperties () {
		System.out.println("Package Name: "+packageName);
		System.out.println("Class Name: "+className);
		System.out.println("LOC: "+lineOfCode);
	}

}
