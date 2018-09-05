package CodeAnalyser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CodeFileAnalyser {

	public static int getLineOfCode(String codeToBeAnalysed) { 
		InputStream codeStream = new ByteArrayInputStream(codeToBeAnalysed.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(codeStream)); 
		int i=0;
		boolean isEOF=false;
		do{
			String t = "";
			try {
				t = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(t!=null){
				isEOF=true;
				t=t.replaceAll("\\n|\\t|\\s", "");
				if((!t.equals("")) && (!t.startsWith("//"))) {
					i = i + 1;
				}
			}
			else {
				isEOF=false;
			}
		}while(isEOF);
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
