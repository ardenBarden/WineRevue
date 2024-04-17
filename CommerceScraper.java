package org.apache.maven.jsoup;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CommerceScraper {
	public static void main(String[] args) throws IOException {
		String url = "https://brazosport.org/member-directory/"; 
		Document doc = getDoc(url); 
		
		//the street addresses are organized by class into "column2" within the html file. 
		Elements test = doc.getElementsByClass("column2"); //hypothetically this will return an HTMLCollection of appropriate elements
		//System.out.println(test.toString()); //debugging
		
		//Result is a 2d array. Each column corresponds to an address--
		// Each element in test must have the owner's name, the street address, and town. 
		String[][] result = new String[test.size()][3];
		
		//I now currently have test, an Arraylist of elements each containing the information I need,
		// alongside plenty of information I don't. 
		
		//The street addresses for each business is what I want to copy into the csv file ideally.
		// it looks like test.toString is the only thing that seems to have what I need. I'll just scan that. 
		
		int count = 0; // keeping up with the indices of the test arraylist
		for (Element e : test) {
			Scanner scan = new Scanner(e.toString());
			while(scan.hasNextLine()) {
				String curr = scan.nextLine(); 
				if(curr.contains("class=\"column2\">")) {
					result[count][0] = scan.nextLine(); //owner name
				}
				else if(curr.contains("Address:</strong>")) {
					result[count][1] = scan.nextLine().substring(5); //substring command removes some html fluff
					result[count][2] = scan.nextLine().substring(5); 
				}
			}
			count++; 
		}
		
		//for (int i = 0; i < result.length; i++) {
		//	System.out.println(i + ": " + Arrays.toString(result[i]));
		//	} //debugging
		
		//now, I have an array result[][] filled with the values I need! put them in a csv file :)
		File file = new File("result.csv"); 
		if (file.createNewFile()) System.out.println("ok cool"); //so where is it....
		PrintWriter pw = new PrintWriter(file); 
		for (int i = 0; i < result.length; i++) {
			for(int j = 0; j < result[i].length; j++) {
				pw.write(result[i][j] + ", "); 
			}
			pw.write("\n"); 
		} //We did it!!!!
		pw.close();
	}
	
	/**
	 * Getter method that throws an exception for an invalid URl
	 * @param url a String representing the website to be scraped
	 * @return doc the Jsoup instance of the url information
	 */
	public static Document getDoc(String url) {
		Connection con = Jsoup.connect(url); 
		Document doc = null; 
		 try {
		       doc = con.get();
		    } catch (IOException e) {
		        e.printStackTrace();
		        System.out.println("Invalid URL connection"); 
		    }
		return doc; 
	}
	
}
