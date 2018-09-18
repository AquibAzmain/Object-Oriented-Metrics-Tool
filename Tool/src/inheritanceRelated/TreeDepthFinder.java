package inheritanceRelated;

import java.util.ArrayList;

public class TreeDepthFinder {
	public ArrayList <String> rootList, nonRootList;
	public ArrayList<ArrayList<String>> inheritanceList;
	public ArrayList<String[]> inheritanceInput;
	private ArrayList<Node<String>> nodelist;
	ChildrenCounter childrenCounter = new ChildrenCounter();
	
	 private Node<String> createTree(String rootName) {
		 Node<String> root = new Node<>(rootName, null);
		 nodelist.add(root);
		 for(int j=0; j<inheritanceList.size(); j++) {
				if(inheritanceList.get(j).get(0).equals(root.getData())) {
					if(inheritanceList.get(j).size()>1) {
						for(int k=0; k<inheritanceList.get(j).size()-1; k++) {
							root.addChild(new Node<String>(inheritanceList.get(j).get(k+1), root));
							nodelist.add(new Node<String>(inheritanceList.get(j).get(k+1), root));
						}
					}
				}
			}
		  
		 return root;
	}
	 
	 private <T> void printTree(Node<T> node, String appender) {
		 if(node.getParent() == null) {
			 childrenCounter.fileString += node.getData() + "\n";
//			 System.out.println(node.getData());
		 }
		 else{
			 childrenCounter.fileString += appender + node.getData() + "\n";
//			 System.out.println(appender + node.getData());
		 }
	   node.getChildren().forEach(each ->  printTree(each, appender + appender));
	}
	 
	 private void completeTree() {
		while(nodelist.size() != inheritanceInput.size()) {
			for(int i=0; i<inheritanceInput.size(); i++) {
				boolean flag = false;
				for(int j=0; j < nodelist.size(); j++) {
					if(inheritanceInput.get(i)[0].equals(nodelist.get(j).getData())) {
						flag = true;
					}
				}
				if(!flag) {
					for(int j=0; j<nodelist.size(); j++) {
						if(inheritanceInput.get(i)[1].equals(nodelist.get(j).getData())) {
							Node <String> x_parent = nodelist.get(j).getParent();
							for(int m=0; m< x_parent.getChildren().size(); m++) {
								if(nodelist.get(j).getData().equals(x_parent.getChildren().get(m).getData())) {
									x_parent.getChildren().remove(x_parent.getChildren().get(m));
//									if(rem)System.out.println("removed");
								}
							}
							
							nodelist.get(j).addChild(new Node<String>(inheritanceInput.get(i)[0],nodelist.get(j)));
							nodelist.add(new Node<String>(inheritanceInput.get(i)[0],nodelist.get(j)));
							x_parent.addChild(nodelist.get(j));
						}
					}
				}
			}
		}
	}

	
	public void treeDepthFinderManager() {
//		System.out.println("\n\nProject Tree:");
		
		childrenCounter.manageChildrenCounter();
		childrenCounter.fileString += "\n\nProject Tree:\n"; 
		rootList = childrenCounter.rootList;
		nonRootList = childrenCounter.nonRootList;
		inheritanceList = childrenCounter.inheritanceList;

		inheritanceInput = childrenCounter.inheritanceInput;
		nodelist = new ArrayList<>();
		ArrayList<Node<String>> rootNodeList = new ArrayList<>();
		for(int i=0; i<rootList.size(); i++) {
			Node<String> root = createTree(rootList.get(i));
			rootNodeList.add(root);
		 }
		 
		completeTree();
		childrenCounter.fileString += "Total number of trees: " + rootList.size() + "\n";
//		System.out.println("Total number of trees: " + rootList.size());

		for(int i=0; i<rootNodeList.size(); i++) {
			childrenCounter.fileString += "\nTree no: " + (i+1) + "\n";
//			System.out.println("\nTree no: " + (i+1));
			printTree(rootNodeList.get(i), "-");
		 }
		int maxDepth = -1;
		for(int i=0; i<nodelist.size(); i++) {
			if(nodelist.get(i).depth > maxDepth) {
				maxDepth = nodelist.get(i).depth;
			}
		 }
		childrenCounter.fileString += "Tree having maximum depth in this forest is: " + maxDepth + "\n";
//		System.out.println("Tree having maximum depth in this forest is: " + maxDepth);
		
		
		
	}

}