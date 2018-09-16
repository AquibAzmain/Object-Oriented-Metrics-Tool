package CyclomaticComplexity;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import static com.github.javaparser.JavaParser.parseBodyDeclaration;
import java.io.FileInputStream;


public class MethodNameExtractor {

	private static final String FILE_PATH = "G:\\Software Metrics\\workspace submission 1\\ASTPractice\\Test\\ReversePolishNotation.java";

	public static void main(String[] args) throws Exception {

		CompilationUnit cu = JavaParser.parse(new FileInputStream(FILE_PATH));

		VoidVisitor<?> cyclomaticComplexityCounter = new CyclomaticComplexityCounter();
		cyclomaticComplexityCounter.visit(cu, null);
		//System.out.println(cu.toString());

		BodyDeclaration<?> method1 = parseBodyDeclaration("void x(int n) {"
				+ "if (n < 0) ; else if (n > 0) ; else ; "
				+ "name = ((city.getName() == null) ? \"N/A\" : city.getName());}");
		BodyDeclaration<?> method2 = parseBodyDeclaration(
				"void y(char grade) {switch (grade){case 'B' : case 'C' :System.out.println(\"Well done\");break; case 'D' :System.out.println(\"Well done\");break; default :System.out.println(\"Invalid grade\");} }");
		BodyDeclaration<?> method3 = parseBodyDeclaration("void x(int n){ "
				+ "for(int i=1;i<=10;i++){System.out.println(i); }  "
				+ "for(int i:arr){  System.out.println(i);} "
				+ "for(;;){  System.out.println(\"infinitive loop\"); }"
				+ "int i=1;  do{  System.out.println(i); i++;}while(i<=10);"
				+ "int i=1;  while(i<=10){  System.out.println(i);  i++;  }  }");
		//System.out.println("If : " + processCondition(method1));
		//System.out.println("Switch : " + processSwitch(method2));
		//System.out.println("Loop : " + processLoop(method3));
		
		

	}

	private static class CyclomaticComplexityCounter extends VoidVisitorAdapter<Void> {

		@Override
		public void visit(MethodDeclaration md, Void arg) {
			super.visit(md, arg);
			String methodBody = md.getChildNodes().get(md.getChildNodes().size()-1).toString();
			System.out.println("Method Name : " + md.getNameAsString());
			BodyDeclaration<?> methodToParse = parseBodyDeclaration(methodBody);
			System.out.println(processSwitch(methodToParse)+processCondition(methodToParse)+processLoop(methodToParse)+1);

		}
	}

	@SuppressWarnings("deprecation")
	static int processCondition(Node node) {
		int complexity = 0;
		for (IfStmt ifStmt : node.getChildNodesByType(IfStmt.class)) {
			// We found an "if" - cool, add one.
			complexity++;
			if (ifStmt.getElseStmt().isPresent()) {
				// This "if" has an "else"
				if (ifStmt.getElseStmt().get() instanceof IfStmt) {
					// it's an "else-if". We already count that by counting the
					// "if" above.
				} else {
					// it's an "else-something". Add it.
					complexity++;
				}
			}
		}
		return complexity;
	}

	@SuppressWarnings("deprecation")
	static int processSwitch(Node node) {
		int complexity = 0;
		for (SwitchStmt switchStmt : node.getChildNodesByType(SwitchStmt.class)) {
			if (switchStmt.isSwitchStmt()) {
				//complexity++;
				complexity = complexity + switchStmt.getChildNodes().size()-1;
				//System.out.println(switchStmt.getChildNodes().get(1).getChildNodes().size());
			}
		}
		for (SwitchStmt switchStmt : node.getChildNodesByType(SwitchStmt.class)) {
			//System.out.println(switchStmt.getChildNodes().size()-1);
		}
		return complexity;
	}
	
	@SuppressWarnings("deprecation")
	static int processLoop(Node node) {
		int complexity = 0;
		for (ForStmt forStmt : node.getChildNodesByType(ForStmt.class)) {
			if (forStmt.isForStmt()) {
				complexity++;
			}
		}
		for (ForeachStmt foreachStmt : node.getChildNodesByType(ForeachStmt.class)) {
			if (foreachStmt.isForeachStmt()) {
				complexity++;
			}
		}
		for (WhileStmt whileStmt : node.getChildNodesByType(WhileStmt.class)) {
			if (whileStmt.isWhileStmt()) {
				complexity++;
			}
		}
		for (DoStmt doStmt : node.getChildNodesByType(DoStmt.class)) {
			if (doStmt.isDoStmt()) {
				complexity++;
			}
		}
	
		return complexity;
	}

}