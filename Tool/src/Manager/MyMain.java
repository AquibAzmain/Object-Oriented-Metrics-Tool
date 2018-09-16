package Manager;

import java.io.FileNotFoundException;
import java.io.IOException;

import CodeAnalyser.CommentMetricsGenerator;
import CyclomaticComplexity.CyclomaticComplexityManager;
import InheritanceRelated.ChildrenCounter;
import InheritanceRelated.TreeDepthFinder;

public class MyMain {

	public static void main(String[] args) throws IOException{
		long startTime = System.nanoTime();
		
		CyclomaticComplexityManager cycloManager = new CyclomaticComplexityManager();
		cycloManager.CCCounterForClass();

		ChildrenCounter childrenCounter = new ChildrenCounter();
		childrenCounter.manageChildrenCounter();
		childrenCounter.printInheritanceList();
		
		TreeDepthFinder treeDepthFinder = new TreeDepthFinder();
		treeDepthFinder.treeDepthFinderManager();
		long endTime = System.nanoTime();
		
		CommentMetricsGenerator myCommentMetricsGenerator = new CommentMetricsGenerator();
		myCommentMetricsGenerator.printMetrics();

		long duration = (endTime - startTime);
		System.out.println("duration to execute(in ms): " + (duration/1000000));
		
	}

}
