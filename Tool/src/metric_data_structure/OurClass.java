package metric_data_structure;

import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class OurClass {
    private String name;
    private String containerPackage;
    private String filePath;

    private List<OurClass> superClasses;
    private List<OurClass> childClasses;
    private Set<OurClass> coupledClasses;

    private List<OurMethod> methods;
    private List<String> importedPackages;

    private CompilationUnit compilationUnit;

    private int LCOM;

    // constructors

    public OurClass(){
        superClasses = new ArrayList<>();
        childClasses = new ArrayList<>();
        coupledClasses = new LinkedHashSet<>();

        methods = new ArrayList<>();
        importedPackages = new ArrayList<>();
    }

    public OurClass(String name, String containerPackage) {
        this();
        this.name = name;
        this.containerPackage = containerPackage;
    }

    // methods

    public void addSuperClass(OurClass superClass){
        superClasses.add(superClass);
    }

    public void addChildClass(OurClass childClass){
        childClasses.add(childClass);
    }

    public void addCoupledClass(OurClass coupledClass){
        coupledClasses.add(coupledClass);
    }

    public void addImportedPackage(String importedPackage){
        importedPackages.add(importedPackage);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof OurClass){
            OurClass class2 = (OurClass) obj;

            if(name.equals(class2.getName()) && containerPackage.equals(class2.getContainerPackage()))
                return true;
            return false;
        }

        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "OurClass{" +
                "name='" + name + '\'' +
                ", containerPackage='" + containerPackage + '\'' +
                '}';
    }

    // getters / setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OurClass> getSuperClasses() {
        return superClasses;
    }

    public void setSuperClasses(List<OurClass> superClasses) {
        this.superClasses = superClasses;
    }

    public List<OurMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<OurMethod> methods) {
        this.methods = methods;
    }

    public List<String> getImportedPackages() {
        return importedPackages;
    }

    public void setImportedPackages(List<String> importedPackages) {
        this.importedPackages = importedPackages;
    }

    public CompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

    public void setCompilationUnit(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    public String getContainerPackage() {
        return containerPackage;
    }

    public void setContainerPackage(String containerPackage) {
        this.containerPackage = containerPackage;
    }

    public List<OurClass> getChildClasses() {
        return childClasses;
    }

    public void setChildClasses(List<OurClass> childClasses) {
        this.childClasses = childClasses;
    }

    public Set<OurClass> getCoupledClasses() {
        return coupledClasses;
    }

    public void setCoupledClasses(Set<OurClass> coupledClasses) {
        this.coupledClasses = coupledClasses;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLCOM() {
        return LCOM;
    }

    public void setLCOM(int LCOM) {
        this.LCOM = LCOM;
    }
}
