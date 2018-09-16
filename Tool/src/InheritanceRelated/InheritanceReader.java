package InheritanceRelated;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import FileReader.MyFileReader;

public class InheritanceReader {
	private BufferedReader brInput;
	public ArrayList<String[]> inheritanceList; //[child, parent]
	public ArrayList <String> rootList, nonRootList;
	 
	private void readFile(String filePath){
		try {
			FileInputStream fstream = new FileInputStream(filePath);
			brInput = new BufferedReader(new InputStreamReader(fstream));
			String input="";
			while((input = brInput.readLine()) != null) {
//				System.out.println(input);
				String [] info = input.split("\\s");

				String temp[] = new String[2];
				for(int i=0; i<info.length; i++) {
//					System.out.println(info[i]);
					if(info[i].equals("class")) {
						temp[0] = info[i+1];
						if(temp[0].contains("{")) {
							temp[0] = temp[0].substring(0, temp[0].length()-1);
						}
						temp[1] = "no parent";
						

					}
					
					if(info[i].equals("extends")) {
						temp[1] = info[i+1];
						if(temp[1].contains("{")) {
							temp[1] = temp[1].substring(0, temp[1].length()-1);
						}
					}
				}

				if(temp[0] != null) {
					inheritanceList.add(temp);
				}
			}
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void getInheritanceList(){		
		ArrayList<String> fileList;
		inheritanceList = new ArrayList<String[]>();
		rootList = new ArrayList<String>();
		nonRootList = new ArrayList<String>();
		
		MyFileReader myFileReader = new MyFileReader();
		fileList = myFileReader.manageFileReader();
		
		for (String filepath: fileList) {
			readFile(filepath);
		}
		
//		for(int i=0; i<inheritanceList.size(); i++) {
//			System.out.println(inheritanceList.get(i)[0] + " -> " + inheritanceList.get(i)[1]);	
//		}
			
	}
	
	private void refineInheritanceList() {
		for(int i=0; i<inheritanceList.size(); i++) {
			boolean flag = false;
			for(int j=0; j<inheritanceList.size(); j++) {
				if(inheritanceList.get(i)[1].equals(inheritanceList.get(j)[0])) {
					flag = true;
				}
			}
			if(!flag) {
				inheritanceList.get(i)[1] = "no parent";
			}
		}
		
		for(int i=0; i<inheritanceList.size(); i++) {
			if(inheritanceList.get(i)[1].equals("no parent")) {
				rootList.add(inheritanceList.get(i)[0]);
			}
			else nonRootList.add(inheritanceList.get(i)[0]);
		}
	}
	
	public void inheritanceReaderManager() {
		getInheritanceList();
		refineInheritanceList();
	}
}
