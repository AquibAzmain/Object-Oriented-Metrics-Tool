package InheritanceRelated;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ChildrenCounter {
	public ArrayList<String[]> inheritanceInput;
	public ArrayList<ArrayList<String>> inheritanceList;
	public ArrayList <String> rootList, nonRootList;
	public BufferedWriter writer;
	public static String fileString;
	
	private void findChildren() {
		for(int i=0; i<inheritanceInput.size(); i++) {
			String parent = inheritanceInput.get(i)[0];
			 ArrayList<String> temp = new ArrayList<String>();
			 temp.add(parent);			
			for(int j=0; j<inheritanceInput.size(); j++) {
				if(inheritanceInput.get(j)[1].equals(parent)) {
					temp.add(inheritanceInput.get(j)[0]);
				}
			}
			inheritanceList.add(temp);
		}
	}
	
	public void printInheritanceList() {
		try {
			writer = new BufferedWriter(new FileWriter("InheritanceRelatedData.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    fileString = "There are total " + inheritanceList.size() + " classes in this project."
	    		+ "Those are:\n"
	    		+ " Parent: Children\n";
//		System.out.println("There are total " + inheritanceList.size() + " classes in this project.\n");
//		System.out.println("Those are:\n");
//		System.out.println(" Parent: Children");
		
		for(int i=0; i<inheritanceList.size(); i++) {
			for(int j=0; j<inheritanceList.get(i).size(); j++) {
				if(j==0) {
					if(inheritanceList.get(i).size() == 1) {
						fileString += inheritanceList.get(i).get(j)+ ": 0 children\n";
//						System.out.println(inheritanceList.get(i).get(j)+ "->\t\t0");
					}
					else {
						fileString += inheritanceList.get(i).get(j) + ": " + (inheritanceList.get(i).size()-1) + " children ( ";
//						System.out.print(inheritanceList.get(i).get(j) + " ->\t" + (inheritanceList.get(i).size()-1) + " ( ");
					}
				}
				else if( j < inheritanceList.get(i).size()-1) {
					fileString += inheritanceList.get(i).get(j) + ", ";
//					System.out.print(inheritanceList.get(i).get(j) + ", ");
				}
				else {
					fileString += inheritanceList.get(i).get(j) + " )\n";
//					System.out.println(inheritanceList.get(i).get(j) + " )");
				}
			}
		}
		
		fileString += "\n----------------------------------------------------------------";
	}

	
	public void manageChildrenCounter() {
		inheritanceList = new ArrayList<ArrayList<String>>();
		InheritanceReader ir = new InheritanceReader();
		ir.inheritanceReaderManager();
		inheritanceInput = ir.inheritanceList;
		rootList = ir.rootList;
		nonRootList = ir.nonRootList;
			
		findChildren();
	}

}
