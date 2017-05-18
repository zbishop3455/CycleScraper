import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
public class Craigslist {
	int searchMin;
	int searchMax;
	Document cl;
	boolean dataLoaded = false;
	String[] prices;
	String[] dates;
	String[] titles;
	
	public Craigslist(int searchMin, int searchMax){
		this.searchMin = searchMin;
		this.searchMax = searchMax;
	}
	
	
	
	public void connect(){
		//Attempts to download search data from craigslist
		try {
			cl = Jsoup.connect("https://indianapolis.craigslist.org/search/mca?max_price=" + searchMax).get();
			System.out.println("Downloaded data from craiglist");
			dataLoaded = true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to connect to craiglist");
		}
	}
	
	
	public void extractData(){
		
		if(dataLoaded){
			Elements paragraphs = cl.select("p.result-info");
			Elements titlesRaw = paragraphs.select("a.result-title");
			
			//extract the title
			Object[] titlesArray = titlesRaw.toArray();
			titles = new String[titlesArray.length];
			for(int i=0;i<titlesArray.length;i++){
				titles[i] = ((Element) titlesArray[i]).text();
				//System.out.println(titles[i]);
			}
			
			//get prices!
			Elements pricesRaw = paragraphs.select("span.result-price");
			Object[] pricesArray = pricesRaw.toArray();
			prices = new String[pricesArray.length];
			for(int i=0;i<pricesArray.length;i++){
				prices[i] = ((Element) pricesArray[i]).text();
				//System.out.println(prices[i]);
			}
			
			//find dates  by default, they are in order from most recent
			Elements datesRaw = paragraphs.select("time.result-date");
			Object[] datesArray = datesRaw.toArray();
			dates = new String[datesArray.length];
			
			for(int i=0;i<datesArray.length;i++){
				dates[i] = ((Element) datesArray[i]).text();
				System.out.println(dates[i]);
			}
		}
		else{
			System.out.println("Cannot extract data");
		}
		
	}
	
	
	public String[] getTitles(){
		return this.titles;
	}
	
	public String[] getPrices(){
		return this.prices;
	}
	
	public String[] getDates(){
		return this.dates;
	}
}
