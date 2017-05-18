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
		Document cl = null;
		
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
		
		//try to search craigslist
		try {
			cl = Jsoup.connect("https://indianapolis.craigslist.org/search/mca?max_price=" + searchAmount).get();
			System.out.println("Downloaded data from craiglist");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to connect to craiglist");
		}
		
		Elements paragraphs = cl.select("p.result-info");
		Elements titlesRaw = paragraphs.select("a.result-title");
		
		//extract the title of each listing and store it in an array
		Object[] titlesArray = titlesRaw.toArray();
		String[] titles = new String[titlesArray.length];
		for(int i=0;i<titlesArray.length;i++){
			titles[i] = ((Element) titlesArray[i]).text();
			//System.out.println(titles[i]);
		}
		
		//get prices!
		Elements pricesRaw = paragraphs.select("span.result-price");
		Object[] pricesArray = pricesRaw.toArray();
		String[] prices = new String[pricesArray.length];
		for(int i=0;i<pricesArray.length;i++){
			prices[i] = ((Element) pricesArray[i]).text();
			//System.out.println(prices[i]);
		}
		
		//find dates  by default, they are in order from most recent
		Elements datesRaw = paragraphs.select("time.result-date");
		Object[] datesArray = datesRaw.toArray();
		String[] dates = new String[datesArray.length];
		
		for(int i=0;i<datesArray.length;i++){
			dates[i] = ((Element) datesArray[i]).text();
			System.out.println(dates[i]);
		}
	
			
		
		

	}

}
