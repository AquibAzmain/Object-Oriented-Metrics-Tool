package InheritanceRelated;

import java.util.ArrayList;

public class ChildrenCounter {
	public ArrayList<String[]> inheritanceInput;
	public ArrayList<ArrayList<String>> inheritanceList;
	public ArrayList <String> rootList, nonRootList;
	
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
		System.out.println("There are total " + inheritanceList.size() + " classes in this project.");
		System.out.println("Those are:\n");
		System.out.println(" Parent         ->         Children No");
		
		for(int i=0; i<inheritanceList.size(); i++) {
			for(int j=0; j<inheritanceList.get(i).size(); j++) {
				if(j==0) {
					if(inheritanceList.get(i).size() == 1)
						System.out.println(inheritanceList.get(i).get(j)+ "->          0");
					else System.out.print(inheritanceList.get(i).get(j) + " ->         " + (inheritanceList.get(i).size()-1) + " ( ");
				}
				else if( j < inheritanceList.get(i).size()-1) System.out.print(inheritanceList.get(i).get(j) + ", ");
				else System.out.println(inheritanceList.get(i).get(j) + " )");
			}
		}
		
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
