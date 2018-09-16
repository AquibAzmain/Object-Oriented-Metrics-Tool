package CyclomaticComplexity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import FileReader.MyFileReader;


public class CyclomaticComplexityManager {

	public void CCCounterForClass() throws FileNotFoundException {
		MyFileReader fileReader = new MyFileReader();
		//System.out.println(fileReader.manageFileReader().toString());
		@SuppressWarnings("rawtypes")
		ArrayList fileList = new ArrayList<>();
		fileList = fileReader.manageFileReader();
		
		for (int i = 0; i< fileList.size()-1; i++) {
			CompilationUnit cu = JavaParser.parse(new FileInputStream(fileList.get(i).toString()));
			for (Node childNode : cu.getChildNodes()) {
				if(childNode.getMetaModel().getTypeName().equals("ClassOrInterfaceDeclaration")) {
					System.out.println("Class name: "+childNode.getChildNodes().get(0));
					CyclomaticComplexityCounter cyclomaticComplexityCounter = new CyclomaticComplexityCounter();
					cyclomaticComplexityCounter.visit(cu, null);
					System.out.println("Complexity: " + cyclomaticComplexityCounter.FinalComplexity+ "\n");
					
				}
			}
			
		}
		
	}
	
	public void PrintCSV() {
		
	}
}
