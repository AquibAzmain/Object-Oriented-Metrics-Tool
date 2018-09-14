package Manager;

import cohesion.LOCMCalculation;

public class MyMain {

	public static void main(String[] args) {
//		ArrayList<String> fileList;
//		MyFileReader myFileReader = new MyFileReader();
//		fileList = myFileReader.manageFileReader();

		LOCMCalculation locmCalculation = new LOCMCalculation();
		locmCalculation.addCohesionNodes();
	}

}
