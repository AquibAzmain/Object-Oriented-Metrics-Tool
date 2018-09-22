package CodeAnalyser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;

import FileReader.*;

public class CommentMetricsGenerator {
	
	private ArrayList<String> listOfFilePath;
	
	public CommentMetricsGenerator () {
		MyFileReader fileReader = new MyFileReader();
		this.listOfFilePath = fileReader.manageFileReader();
	}
	
	public void printMetrics() throws IOException{
		
		PrintWriter csvFileWriter = new PrintWriter("comments.csv");
		csvFileWriter.println("File Name,LOC,Number of Comments,Comment %");
		
		System.out.println("LOC and Comments of all the files:");
		
		for (String filePath : listOfFilePath) {
			
			String contentOfFile = new String(Files.readAllBytes(Paths.get(filePath)));
			System.out.println();
			System.out.println("File Name: "+filePath);
			
			JavaParser.getStaticConfiguration().setAttributeComments(true);
			CompilationUnit compilationUnit = JavaParser.parse(contentOfFile);
			List<Comment> comments = compilationUnit.getComments();
			Integer numberOfComments = comments.size();
			
			for (Comment comment : comments) {
				System.out.print("Comment: "+comment.toString());
				System.out.println("Comment Type: "+detectTypeOfComment(comment));
			}
			
			//Reset parser config to remove comments
			JavaParser.getStaticConfiguration().setAttributeComments(false);
			compilationUnit = JavaParser.parse(contentOfFile);
			Integer lineOfCode = getLineOfCode(compilationUnit.toString());
			
	        System.out.println("File LOC: "+ lineOfCode);
	        System.out.println("Number of Comments: "+numberOfComments);
	        System.out.println("Comment Percentage: "+((numberOfComments*100)/lineOfCode)+"%");
	        
	        csvFileWriter.println(filePath+","+lineOfCode+","+numberOfComments+","+
	        		((numberOfComments*100)/lineOfCode));

		}
		csvFileWriter.close();
	}
	
	private static String detectTypeOfComment (Comment comment) {
		if(comment.isBlockComment()) return "Block Comment";
		if(comment.isJavadocComment()) return "Java Doc Comment";
		if(comment.isLineComment()) return "Line Comment";
		return "Cannot Identify Comment Type";
	}
	
	private int getLineOfCode(String codeToBeAnalysed) { 
		InputStream codeStream = new ByteArrayInputStream(codeToBeAnalysed.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(codeStream)); 
		int i=0;
		boolean isEOF=false;
		do{
			String t = "";
			try {
				t = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(t!=null){
				isEOF=true;
				t=t.replaceAll("\\n|\\t|\\s", "");
				if((!t.equals("")) && (!t.startsWith("//"))) {
					i = i + 1;
				}
			}
			else {
				isEOF=false;
			}
		}while(isEOF);
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
