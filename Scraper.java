import java.io.IOException;

import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Scraper {

	public static void main(String[] args) {
		int searchAmount = 1;
		String price;
		Scanner scanny = new Scanner(System.in);
		
		System.out.println("Welcom to CycleScraper! \n");
		System.out.println("Enter your max price (USD): ");
		
		boolean keepGoing = true;
		
		
		//ask user their price range
		while(keepGoing){
		price = scanny.nextLine();
		try{
			searchAmount = Integer.parseInt(price);
			keepGoing = false;
			}catch(Exception e){
				System.out.println("Invalid Amount");
			}
		}
		scanny.close();
		
		//try to search craiglist
		try {
			Document craigsList = Jsoup.connect("https://indianapolis.craigslist.org/search/mca?max_price=" + searchAmount).get();
			System.out.println("Downloaded data from craiglist");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to connect to craiglist");
		}
		

	}

}
