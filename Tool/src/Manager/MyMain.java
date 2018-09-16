package Manager;

import java.io.IOException;

import CodeAnalyser.CommentMetricsGenerator;
import FileReader.MyFileReader;

public class MyMain {

	public static void main(String[] args) {
		
		MyFileReader fileReader = new MyFileReader();
		CommentMetricsGenerator myCommentMetricsGenerator = new CommentMetricsGenerator(fileReader.manageFileReader());
		try {
			myCommentMetricsGenerator.printMetrics();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
