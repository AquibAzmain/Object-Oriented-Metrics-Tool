package Manager;

import FileReader.MyFileReader;
import cohesion.LOCMCalculation;
import metric_data_structure.Populator;

import java.io.File;
import java.util.ArrayList;

public class MyMain {

	public static void main(String[] args) {
		Populator populator = new Populator();
		populator.init();
	}

}
