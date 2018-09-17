package CyclomaticComplexity;

import static com.github.javaparser.JavaParser.parseBodyDeclaration;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CyclomaticComplexityCounter extends VoidVisitorAdapter<Void> {

	public int FinalComplexity = 0;
	public int WMC = 0;
	public int TotalCC = 0;

	public void visit(MethodDeclaration md, Void arg) {
		super.visit(md, arg);
		if (!md.isAbstract()) {
			WMC++;
			String methodBody = md.getChildNodes().get(md.getChildNodes().size() - 1).toString();
			if (methodBody.startsWith("{")) {
				BodyDeclaration<?> methodToParse = parseBodyDeclaration(methodBody);
				// System.out.println(processSwitch(methodToParse) +
				// processCondition(methodToParse) + processLoop(methodToParse) +
				// 1);
				FinalComplexity = processSwitch(methodToParse) + processCondition(methodToParse)
						+ processLoop(methodToParse) + 1;
				System.out.println("Method Name : " + md.getNameAsString() + "-> Complexity: " + FinalComplexity);
				TotalCC = TotalCC + FinalComplexity;
			} else {
				WMC++;
				FinalComplexity = 0;
				System.out.println("Method Name : " + md.getNameAsString() + "-> Complexity: " + FinalComplexity);

			}
		}
	}

	@SuppressWarnings("deprecation")
	static int processCondition(Node node) {
		int complexity = 0;
		for (IfStmt ifStmt : node.getChildNodesByType(IfStmt.class)) {
			complexity++;
			if (ifStmt.getElseStmt().isPresent()) {
				if (ifStmt.getElseStmt().get() instanceof IfStmt) {
				} else {
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
				complexity = complexity + switchStmt.getChildNodes().size() - 1;
			}
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
