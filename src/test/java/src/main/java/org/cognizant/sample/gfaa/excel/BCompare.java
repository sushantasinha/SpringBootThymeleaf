package org.cognizant.sample.gfaa.excel;

public class BCompare {

	public static void main(String[] args) {
		
		String left = "C:\\Users\\Sushanta\\Downloads\\diffkit-0.9.0\\eg\\test9.lhs.csv";
		String right = "C:\\Users\\Sushanta\\Downloads\\diffkit-0.9.0\\eg\\test9.rhs.csv";


		   try{
		    Process process = new ProcessBuilder("C:\\Program Files\\Beyond Compare 4\\BCompare", "@C:\\Users\\Sushanta\\Downloads\\diffkit-0.9.0\\1\\myscript.txt",left,right,"C:\\Users\\Sushanta\\Downloads\\diffkit-0.9.0\\1\\check.html").start();
		   }catch(Exception e){
			   e.printStackTrace();
		   }

	}

}
