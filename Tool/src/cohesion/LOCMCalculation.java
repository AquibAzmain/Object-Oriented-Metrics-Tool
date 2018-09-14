package cohesion;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
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

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        collector.add(md.getName());
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