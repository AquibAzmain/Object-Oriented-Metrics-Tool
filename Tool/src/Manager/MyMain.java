package Manager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import CodeAnalyser.CommentMetricsGenerator;
import CyclomaticComplexity.CyclomaticComplexityManager;
import InheritanceRelated.ChildrenCounter;
import InheritanceRelated.TreeDepthFinder;

public class MyMain {

	public static void main(String[] args) throws IOException{
		long startTime = System.nanoTime();
		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		System.setOut(out);
		
		CyclomaticComplexityManager cycloManager = new CyclomaticComplexityManager();
		cycloManager.CCCounterForClass();

		ChildrenCounter childrenCounter = new ChildrenCounter();
		childrenCounter.manageChildrenCounter();
		childrenCounter.printInheritanceList();
		
		
		TreeDepthFinder treeDepthFinder = new TreeDepthFinder();
		treeDepthFinder.treeDepthFinderManager();
		childrenCounter.writer.write(childrenCounter.fileString);
		childrenCounter.writer.close();
		
		CommentMetricsGenerator myCommentMetricsGenerator = new CommentMetricsGenerator();
		myCommentMetricsGenerator.printMetrics();
		
		long endTime = System.nanoTime();
//
		long duration = (endTime - startTime);
		System.out.println("duration to execute(in ms): " + (duration/1000000));
//		
	}

}
