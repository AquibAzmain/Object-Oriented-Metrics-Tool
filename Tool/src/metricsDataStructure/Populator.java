package metricsDataStructure;

import cbo_rfc.CBORFCCalculation;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import fileReader.MyFileReader;
import cohesion.LCOMCalculation;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Populator {
    public static List<OurClass> classes;

    public Populator(){
        classes = new ArrayList<>();
    }

    public void init(){
    	System.out.println("Lack of Cohesion of Methods(LCOM):");
    	System.out.println();
        ArrayList<String> fileList;
        MyFileReader myFileReader = new MyFileReader();
        fileList = myFileReader.manageFileReader();

        try {
            calculateMetrics(fileList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TODO: add superclass propagation
    public void calculateMetrics(List<String> filePaths) throws FileNotFoundException {
        for(String filePath: filePaths){
            String fileArr[] = filePath.split("\\\\");

            String javaSplitName[] = fileArr[fileArr.length-1].split("\\.");

            OurClass ourClass = new OurClass(javaSplitName[0], fileArr[fileArr.length-2]);
            ourClass.setFilePath(filePath);
            ourClass.setCompilationUnit(getCompilationUnitFromFile(filePath));

            LCOMCalculation lcomCalculation = new LCOMCalculation(ourClass);
            ourClass.setLCOM(lcomCalculation.generateLCOM());

            classes.add(ourClass);
        }

        populateInstanceFields();
        CBORFCCalculation cborfcCalculation = new CBORFCCalculation(classes);
        for(OurClass currClass: classes){
            cborfcCalculation.calc(currClass);
        }

        for(OurClass clazz: classes){
            System.out.println(clazz);
        }
    }

    private void populateInstanceFields() {
        for(OurClass currClass: classes){
            InstanceFieldCollector instanceFieldNameCollector = new InstanceFieldCollector(currClass);
            instanceFieldNameCollector.visit(currClass.getCompilationUnit(), currClass.getFields());
        }
    }

    private CompilationUnit getCompilationUnitFromFile(String filePath) throws FileNotFoundException {
        return JavaParser.parse(new File(filePath));
    }
}

class InstanceFieldCollector extends VoidVisitorAdapter<List<OurVariable>> {
    private OurClass currClass;

    public InstanceFieldCollector(OurClass currClass){
        this.currClass = currClass;
    }

    @Override
    public void visit(FieldDeclaration fd, List<OurVariable>collector) {
        super.visit(fd, collector);

        for(VariableDeclarator vd: fd.getVariables()){
            if(currClass.getName().equals(vd.getTypeAsString()))
                continue;

            for(OurClass aClass: Populator.classes){
                if(aClass.getName().equals(vd.getTypeAsString())){
                    OurVariable field = new OurVariable(vd.getNameAsString(), aClass);
                    collector.add(field);
                }
            }
        }
    }
}