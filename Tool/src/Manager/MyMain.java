package Manager;

import InheritanceRelated.ChildrenCounter;
import InheritanceRelated.TreeDepthFinder;

public class MyMain {

	public static void main(String[] args) {
		
		ChildrenCounter childrenCounter = new ChildrenCounter();
		childrenCounter.manageChildrenCounter();
		childrenCounter.printInheritanceList();
		
		TreeDepthFinder treeDepthFinder = new TreeDepthFinder();
		treeDepthFinder.treeDepthFinderManager();
		
		
	}

}
