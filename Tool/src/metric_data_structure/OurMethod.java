package metric_data_structure;

import com.github.javaparser.ast.CompilationUnit;

import java.util.LinkedHashSet;
import java.util.Set;

public class OurMethod {
    private String name;
    private String signature;
    private OurClass parentClass;

    private CompilationUnit compilationUnit;

    private Set<OurMethod> calledMethods;

    //constructors

    public OurMethod() {
        calledMethods = new LinkedHashSet<>();
    }

    public OurMethod(String name, String signature, OurClass parentClass) {
        this.name = name;
        this.signature = signature;
        this.parentClass = parentClass;
    }

    // methods

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OurMethod){
            OurMethod method2 = (OurMethod) obj;

            if(signature.equals(method2.getSignature()) && parentClass.equals(method2.getParentClass()))
                return true;
            return false;
        }
        return super.equals(obj);
    }

    public void addCalledMethod(OurMethod method2){
        calledMethods.add(method2);
    }

    // getters / setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public OurClass getParentClass() {
        return parentClass;
    }

    public void setParentClass(OurClass parentClass) {
        this.parentClass = parentClass;
    }

    public CompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

    public void setCompilationUnit(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    public Set<OurMethod> getCalledMethods() {
        return calledMethods;
    }

    public void setCalledMethods(Set<OurMethod> calledMethods) {
        this.calledMethods = calledMethods;
    }
}
