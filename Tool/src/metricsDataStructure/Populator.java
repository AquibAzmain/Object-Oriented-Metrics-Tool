package metricsDataStructure;

import fileReader.MyFileReader;
import cohesion.LCOMCalculation;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Populator {
    List<OurClass> classes;

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
            populateClasses(fileList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void populateClasses(List<String> filePaths) throws FileNotFoundException {
        for(String filePath: filePaths){
            String fileArr[] = filePath.split("\\\\");

            OurClass ourClass = new OurClass(fileArr[fileArr.length-1], fileArr[fileArr.length-2]);
            ourClass.setFilePath(filePath);
            ourClass.setCompilationUnit(getCompilationUnitFromFile(filePath));

            LCOMCalculation lcomCalculation = new LCOMCalculation(ourClass);
            ourClass.setLCOM(lcomCalculation.generateLCOM());

            classes.add(ourClass);
        }

        for(OurClass clazz: classes){
            System.out.println(clazz);
        }
    }

    private CompilationUnit getCompilationUnitFromFile(String filePath) throws FileNotFoundException {
        return JavaParser.parse(new File(filePath));
    }
}
