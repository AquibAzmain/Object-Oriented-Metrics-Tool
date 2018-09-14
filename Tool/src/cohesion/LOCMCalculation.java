package cohesion;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * calculates Lack of Cohesion of Methods
 */

public class LOCMCalculation {
    List<CohesionNode> cohesionNodes;

    public void addInstanceVariables(){
        try {
            CompilationUnit compilationUnit = JavaParser.parse(new File("D:\\Studies\\Semester 8\\Metrics\\projects\\Object-Oriented-Tool\\Tool\\src\\cohesion\\TestClass.java"));

            System.out.println(compilationUnit);

            for (TypeDeclaration typeDec : compilationUnit.getTypes()) {
                List<BodyDeclaration> members = typeDec.getMembers();
                if (members != null) {
                    for (BodyDeclaration member : members) {
                        //Check just members that are FieldDeclarations
                        FieldDeclaration field = (FieldDeclaration) member;
                        //Print the field's class type
                        System.out.println(field.getType());
                        //Print the field's name
                        System.out.println(field.getVariables().get(0).getId().getName());
                        //Print the field's init value, if not null
                        Object initValue = field.getVariables().get(0).getInit();
                        if (initValue != null) {
                            System.out.println(field.getVariables().get(0).getInit().toString());
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
