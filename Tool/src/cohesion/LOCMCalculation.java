package cohesion;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * calculates Lack of Cohesion of Methods
 */

public class LOCMCalculation {
    List<CohesionNode> cohesionNodes;
    private List<String> fieldNames;

    public void addCohesionNodes(){
        cohesionNodes = new ArrayList<>();
        CompilationUnit compilationUnit;
        try {
            compilationUnit = JavaParser.parse(new File("D:\\Studies\\Semester 8\\Metrics\\projects\\Object-Oriented-Tool\\Tool\\src\\cohesion\\TestClass.java"));

            populateMethods(compilationUnit);
            populateInstanceFields(compilationUnit);

//            exploreMethods(compilationUnit);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exploreMethods(CompilationUnit compilationUnit) {
        MethodVariableCollector methodVariableCollector = new MethodVariableCollector();
        methodVariableCollector.visit(compilationUnit, null);
    }

    private void populateInstanceFields(CompilationUnit compilationUnit) {
        fieldNames = new ArrayList<>();
        InstanceFieldNameCollector instanceFieldNameCollector = new InstanceFieldNameCollector();
        instanceFieldNameCollector.visit(compilationUnit, fieldNames);

        for(String field: fieldNames){
            cohesionNodes.add(new CohesionNode(field, false, null));
        }
    }

    private void populateMethods(CompilationUnit compilationUnit) {
        List<String> methodNames = new ArrayList<>();

        MethodNameCollector methodNameCollector = new MethodNameCollector();
        methodNameCollector.visit(compilationUnit, methodNames);

        for(String method: methodNames){
            cohesionNodes.add(new CohesionNode(method, true));
        }
    }
}

class MethodNameCollector extends VoidVisitorAdapter<List<String>>{

    @Override
    public void visit(MethodDeclaration md, List<String> collector) {
        super.visit(md, collector);
        System.out.println("\n" + md.getName());
        collector.add(md.getDeclarationAsString());

        MethodVariableCollector methodVariableCollector = new MethodVariableCollector();
        methodVariableCollector.visit(md, null);

        VariableFetcher variableFetcher = new VariableFetcher();
        variableFetcher.visit(md, null);

        AssignExprFetcher assignExprFetcher = new AssignExprFetcher();
        assignExprFetcher.visit(md, null);

        VariableDeclarationFetcher variableDeclarationFetcher = new VariableDeclarationFetcher();
        variableDeclarationFetcher.visit(md, null);
    }
}

class AssignExprFetcher extends VoidVisitorAdapter<Void>{

    public void visit(AssignExpr ae, Void v){
        System.out.println("ae " + ae.toString() + " ");
    }
}

class VariableDeclarationFetcher extends VoidVisitorAdapter<Void>{

    public void visit(VariableDeclarationExpr vd, Void v){
        System.out.println("vd " + vd.toString());
        List<VariableDeclarator> variableDeclarators = vd.getVariables();
        for(VariableDeclarator variableDeclarator: variableDeclarators){
            System.out.println("    " + variableDeclarator.getName());
        }
    }
}

class VariableFetcher extends VoidVisitorAdapter<Void>{

    public void visit(MethodCallExpr mce, Void v){
        System.out.println("mce " + mce.toString() + mce.getScope());
    }
}

class InstanceFieldNameCollector extends VoidVisitorAdapter<List<String>>{

    @Override
    public void visit(FieldDeclaration fd, List<String> collector) {
        super.visit(fd, collector);

        for(VariableDeclarator vd: fd.getVariables()){
            collector.add(vd.toString());
        }
    }
}

class MethodVariableCollector extends VoidVisitorAdapter<Void>{

    @Override
    public void visit(MethodDeclaration md, Void v) {
        super.visit(md, v);

        List<Parameter> parameters = md.getParameters();
        if(parameters != null){
            for(Parameter parameter: parameters){
                System.out.println(md.getName() + ": " + parameter.toString());
            }
        }
    }
}