package metricsDataStructure;

import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.LinkedHashSet;
import java.util.Set;

public class OurMethod {
    private String name;
    private String signature;
    private OurClass parentClass;

    private MethodDeclaration md;

    private Set<OurMethod> calledMethods;
    private Set<String> instanceVars;

    //constructors

    public OurMethod() {
        calledMethods = new LinkedHashSet<>();
        instanceVars = new LinkedHashSet<>();
    }

    public OurMethod(String name, String signature, OurClass parentClass) {
        this();
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

    @Override
    public String toString() {
        return name + " [" + signature + "]";
    }

    public void addCalledMethod(OurMethod method2){
        calledMethods.add(method2);
    }

    public void addInstanceVar(String var){
        instanceVars.add(var);
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

    public MethodDeclaration getMd() {
        return md;
    }

    public void setMd(MethodDeclaration md) {
        this.md = md;
    }

    public Set<OurMethod> getCalledMethods() {
        return calledMethods;
    }

    public void setCalledMethods(Set<OurMethod> calledMethods) {
        this.calledMethods = calledMethods;
    }

    public Set<String> getInstanceVars() {
        return instanceVars;
    }

    public void setInstanceVars(Set<String> instanceVars) {
        this.instanceVars = instanceVars;
    }
}
