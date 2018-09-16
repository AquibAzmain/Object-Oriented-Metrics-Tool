package cohesion;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import metric_data_structure.OurClass;
import metric_data_structure.OurMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * calculates Lack of Cohesion of Methods
 */

public class LCOMCalculation {
    private List<String> fieldNames;
    private OurClass currClass;

    public LCOMCalculation(OurClass ourClass) {
        currClass = ourClass;
    }

    public void generateLCOM(){
        CompilationUnit compilationUnit = currClass.getCompilationUnit();
        populateMethods(compilationUnit);
        populateInstanceFields(compilationUnit);
    }

    private void populateInstanceFields(CompilationUnit compilationUnit) {
        fieldNames = new ArrayList<>();
        InstanceFieldNameCollector instanceFieldNameCollector = new InstanceFieldNameCollector();
        instanceFieldNameCollector.visit(compilationUnit, fieldNames);
    }

    private void populateMethods(CompilationUnit compilationUnit) {
        List<OurMethod> methods = new ArrayList<>();

        MethodNameCollector methodNameCollector = new MethodNameCollector(currClass);
        methodNameCollector.visit(compilationUnit, methods);

        currClass.setMethods(methods);
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

class MethodNameCollector extends VoidVisitorAdapter<List<OurMethod>>{
    OurClass currClass;

    public MethodNameCollector(OurClass ourClass){
        currClass = ourClass;
    }

    @Override
    public void visit(MethodDeclaration md, List<OurMethod> collector) {
        super.visit(md, collector);
        OurMethod currMethod = new OurMethod(md.getName().asString(), md.getDeclarationAsString(), currClass);
        currMethod.setMd(md);
        collector.add(currMethod);
    }
}

/*
        VariableFetcher variableFetcher = new VariableFetcher();
        variableFetcher.visit(md, null);

        AssignExprFetcher assignExprFetcher = new AssignExprFetcher();
        assignExprFetcher.visit(md, null);

        VariableDeclarationFetcher variableDeclarationFetcher = new VariableDeclarationFetcher();
        variableDeclarationFetcher.visit(md, null);
 */

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