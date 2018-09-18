package cohesion;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import metricsDataStructure.OurClass;
import metricsDataStructure.OurMethod;

import java.util.*;

/**
 * calculates Lack of Cohesion of Methods
 */

public class LCOMCalculation {
    private List<String> fieldNames;
    private List<OurMethod> currMethods;
    private OurClass currClass;
    private List<LCOMGroup> lcomGroups;

    public LCOMCalculation(OurClass ourClass) {
        currClass = ourClass;
    }

    public int generateLCOM(){
        CompilationUnit compilationUnit = currClass.getCompilationUnit();
        populateMethods(compilationUnit);
        populateInstanceFields(compilationUnit);

        currMethods = currClass.getMethods();
        for(OurMethod method: currClass.getMethods()){
            calculateCohesion(method);
//            System.out.println(method + "\n  methods called: " + method.getCalledMethods() + "\n  vars used: " + method.getInstanceVars());
        }

        createLCOMGroups();
        return lcomGroups.size();
    }

    private void createLCOMGroups() {
        lcomGroups = new ArrayList<>();

        for(OurMethod currMethod: currMethods){
            Boolean found = false;
            for(LCOMGroup lcomGroup: lcomGroups){
                if(lcomGroup.methods.contains(currMethod)){
                    found = true;
                    break;
                }
                else if(variableMatch(lcomGroup.vars, currMethod.getInstanceVars())){
                    populateGroup(lcomGroup, currMethod);
                    found = true;
                    break;
                }
            }
            if(!found){
                LCOMGroup newLcomGroup = new LCOMGroup();
                populateGroup(newLcomGroup, currMethod);
                lcomGroups.add(newLcomGroup);
            }
        }
    }

    private void populateGroup(LCOMGroup lcomGroup, OurMethod currMethod) {
        lcomGroup.methods.add(currMethod);
        lcomGroup.vars.addAll(currMethod.getInstanceVars());

        for(OurMethod nextMethod: currMethod.getCalledMethods()){
            if(!lcomGroup.methods.contains(nextMethod))
                populateGroup(lcomGroup, nextMethod);
        }
    }

    private boolean variableMatch(Set<String> vars, Set<String> instanceVars) {
        return !Collections.disjoint(vars, instanceVars);
    }

    private void calculateCohesion(OurMethod method) {
        MethodDeclaration md = method.getMd();

        MethodCallFetcher variableFetcher = new MethodCallFetcher(currMethods);
        variableFetcher.visit(md, method);

        AssignExprFetcher assignExprFetcher = new AssignExprFetcher(fieldNames);
        assignExprFetcher.visit(md, method);

        VariableDeclarationFetcher variableDeclarationFetcher = new VariableDeclarationFetcher(fieldNames);
        variableDeclarationFetcher.visit(md, method);
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

class AssignExprFetcher extends VoidVisitorAdapter<OurMethod>{
    List<String> vars;

    public AssignExprFetcher(List<String> varList){
        vars = varList;
    }

    @Override
    public void visit(AssignExpr ae, OurMethod currMethod){
        String var = ae.getTarget().toString();
        if(vars.contains(var)){
            currMethod.addInstanceVar(var);
        }
        String operands[] = ae.getValue().toString().split(" ");
        for(int i=0; i<operands.length; i++){
            if(vars.contains(operands[i])){
                currMethod.addInstanceVar(operands[i]);
            }
        }
    }
}

class VariableDeclarationFetcher extends VoidVisitorAdapter<OurMethod>{
    List<String> vars;

    public VariableDeclarationFetcher(List<String> varList){
        vars = varList;
    }

    @Override
    public void visit(VariableDeclarationExpr vd, OurMethod currMethod){
        List<VariableDeclarator> variableDeclarators = vd.getVariables();
        for(VariableDeclarator variableDeclarator: variableDeclarators){
            String operands[] = variableDeclarator.getInitializer().toString().split(" ");
            for(int i=0; i<operands.length; i++){
                if(vars.contains(operands[i])){
                    currMethod.addInstanceVar(operands[i]);
                }
            }
        }
    }
}

class MethodCallFetcher extends VoidVisitorAdapter<OurMethod>{
    List<OurMethod> methods;

    public MethodCallFetcher(List<OurMethod> ourMethods){
        methods = ourMethods;
    }

    @Override
    public void visit(MethodCallExpr mce, OurMethod currMethod){
        if(mce.getScope().equals(Optional.empty())){
            for(OurMethod ourMethod: methods){
                if (mce.getName().asString().equals(ourMethod.getName()))
                    currMethod.addCalledMethod(ourMethod);
            }
        }
    }
}