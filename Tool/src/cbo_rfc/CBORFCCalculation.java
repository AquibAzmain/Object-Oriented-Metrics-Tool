package cbo_rfc;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import metricsDataStructure.OurClass;
import metricsDataStructure.OurMethod;
import metricsDataStructure.OurVariable;

import java.util.List;

public class CBORFCCalculation {

    private List<OurClass> allClasses;

    public CBORFCCalculation(List<OurClass> allClasses){
        this.allClasses = allClasses;
    }

    public void calc(OurClass currClass){
        for(OurMethod method: currClass.getMethods()){
            VariableFetcher variableFetcher = new VariableFetcher(method, allClasses);
            variableFetcher.visit(method.getMd(), method.getVariables());

            MethodCallFetcher methodCallFetcher = new MethodCallFetcher(allClasses);
            methodCallFetcher.visit(method.getMd(), method);
        }
    }
}



class VariableFetcher extends VoidVisitorAdapter<List<OurVariable>> {
    OurMethod currMethod;
    List<OurClass> allClasses;

    public VariableFetcher(OurMethod currMethod, List<OurClass> allClasses){
        this.currMethod = currMethod;
        this.allClasses = allClasses;
    }

    @Override
    public void visit(VariableDeclarator vd, List<OurVariable> collector){
        super.visit(vd, collector);

        if(currMethod.getParentClass().getName().equals(vd.getTypeAsString()))
            return;

        for(OurClass aClass: allClasses){
            if(aClass.getName().equals(vd.getTypeAsString())){
                OurVariable var = new OurVariable(vd.getNameAsString(), aClass);
                collector.add(var);
            }
        }
    }
}

class MethodCallFetcher extends VoidVisitorAdapter<OurMethod>{
    List<OurClass> allClasses;

    public MethodCallFetcher(List<OurClass> allClasses) {
        this.allClasses = allClasses;
    }

    @Override
    public void visit(MethodCallExpr mce, OurMethod currMethod){
        OurClass currClass = currMethod.getParentClass();

        if(mce.getScope().isPresent()){
            String scope = mce.getScope().get().toString();
            String var = scope.split("\\.")[0];

            for(OurVariable variable: currMethod.getVariables()){
                if(var.equals(variable.getName())){
                    currClass.addCoupledClass(variable.getType());
                    variable.getType().addCoupledClass(currClass);

                    currClass.addExternalMethod(getExternalMethod(variable.getType(), mce.getName().asString()));
                }
            }
        }
    }

    private OurMethod getExternalMethod(OurClass ourClass, String methodName) {
        for(OurMethod method: ourClass.getMethods()){
            if(method.getName().equals(methodName))
                return method;
        }

        System.out.println("~~~ PAAY NAI WTF");
        return null;
    }
}