package cyclomaticComplexity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import fileReader.MyFileReader;

public class CyclomaticComplexityManager {

	public void CCCounterForClass() throws FileNotFoundException {
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
			for (Node childNode : cu.getChildNodes()) {
				if (childNode.getMetaModel().getTypeName().equals("ClassOrInterfaceDeclaration")) {
					CyclomaticComplexityCounter cyclomaticComplexityCounter = new CyclomaticComplexityCounter();
					System.out.println();
					System.out.println("\nClass name: " + childNode.getChildNodes().get(0));
					cyclomaticComplexityCounter.visit(cu, null);
					System.out.println("\t WMC: " + cyclomaticComplexityCounter.WMC);
					System.out.println("Total Complexity: "+ cyclomaticComplexityCounter.TotalCC);
				}
			}

		}

	}

	public void PrintCSV() {

	}
}
