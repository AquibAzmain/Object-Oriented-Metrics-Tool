package CyclomaticComplexity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import FileReader.MyFileReader;

public class CyclomaticComplexityManager {

	public void CCCounterForClass() throws FileNotFoundException {
		PrintWriter classStatsWriter = new PrintWriter("WMC_TC.csv");
		classStatsWriter.println("Class Name, Weighted Method Per Class, Total Complexity");
		
		PrintWriter methodStatsWriter = new PrintWriter("cyclomaticComplexity.csv");
		methodStatsWriter.println("Class Name, Method Name, Cyclomatic Complexity");
		
		MyFileReader fileReader = new MyFileReader();
		// System.out.println(fileReader.manageFileReader().toString());
		@SuppressWarnings("rawtypes")
		ArrayList fileList = new ArrayList<>();
		fileList = fileReader.manageFileReader();

		System.out.println();
		System.out.println();
		System.out.println("Cyclomatic Complexity");
		for (int i = 0; i <= fileList.size() - 1; i++) {
			CompilationUnit cu = JavaParser.parse(new FileInputStream(fileList.get(i).toString()));
			String fileArr[] = fileList.get(i).toString().split("\\\\");
            String javaSplitName[] = fileArr[fileArr.length-1].split("\\.");
			String classNameFromFilepath = javaSplitName[0];
			//System.out.println("ssadsdasd "+classNameFromFilepath);
			for (Node childNode : cu.getChildNodes()) {
				if (childNode.getMetaModel().getTypeName().equals("ClassOrInterfaceDeclaration")) {
					CyclomaticComplexityCounter cyclomaticComplexityCounter = new CyclomaticComplexityCounter(methodStatsWriter);
					System.out.println();
					String className = childNode.getChildNodes().get(0).toString();
					System.out.println("\nClass name: " + className);
					cyclomaticComplexityCounter.visit(cu, classNameFromFilepath);
					System.out.println("\t WMC: " + cyclomaticComplexityCounter.WMC);
					System.out.println("Total Complexity: "+ cyclomaticComplexityCounter.TotalCC);
					classStatsWriter.println(classNameFromFilepath+","+cyclomaticComplexityCounter.WMC+","+cyclomaticComplexityCounter.TotalCC);
				}
			}

		}
		methodStatsWriter.close();
		classStatsWriter.close();

	}

	public void PrintCSV() {

	}
}
