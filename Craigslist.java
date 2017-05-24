import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
public class Craigslist {
	String url;
	Document cl;
	boolean dataLoaded = false;
	String[] prices;
	String[] dates;
	String[] titles;
	String[] links;
	String[] searchCriteria;
	int[] usedIndecies;
	

	public Craigslist(String[] searchCriteria){
		//uses search data directly from Scraper.java
		this.searchCriteria = searchCriteria;
		createUrl();
		connect();
		extractData();
	}
	
	
	
	public void connect(){
		//Attempts to download search data from craigslist
		try {
			cl = Jsoup.connect(url).get();
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
			}
			
			//find urls
			Elements linksRaw = paragraphs.select("a.result-title.hdrlnk");
			Object[] linksArray = linksRaw.toArray();
			links = new String[linksArray.length];
			for(int i=0; i<linksArray.length;i++){
				links[i] = ((Element) linksArray[i]).attr("abs:href");
			}
			
			System.out.println("links " + links.length);
			System.out.println(prices.length);
			System.out.println(titles.length);
			
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
	
	public void createUrl(){
		//crafts the url to scrape using search criteria
		
		
		//array of string url variable, indexes coorespond with those of search criteria
		String[] searchCriteriaNames = new String[]{"min_price=", "max_price=", "auto_make_model=","min_engine_displacement_cc=","max_engine_displacement_cc=","min_auto_year=","max_auto_year","min_auto_miles=", "max_auto_miles="};
		
		boolean urlHasModes = true;
		
		
		//check to see how many values are null so we know how many criteria our final url will have
		int nullCount = 0;
		for(int i=0;i<this.searchCriteria.length;i++){
			
			if(searchCriteria[i] == null){
				nullCount++;
			}
		}
		
		if(nullCount == 9){
			//all values are null, default url
			urlHasModes = false;
		}
		
		else{
			//find which indecies are used and push their index into "usedIndecies"
			usedIndecies = new int[this.searchCriteria.length - nullCount];
			
			//variable to keepTrack of where we are inserting into "usedIndecies"
			int usedIndeciesIndex = 0;
			
			for(int i=0;i<this.searchCriteria.length;i++){
				if(searchCriteria[i] != null){
					//if a criteria exists, gather its index
					usedIndecies[usedIndeciesIndex] = i;
					usedIndeciesIndex++;
				}
			}
			
		}
		
		//craft the url
		String urlBase = "https://indianapolis.craigslist.org/search/mca";
		String urlMods = "?";
		if(urlHasModes){
			//calculate urlMods
			for(int i=0;i<this.searchCriteria.length - nullCount;i++){
				//loop through all usedIndexes
				
				//if its the first criteria, we do not need an & connective
				if(i==0){
					urlMods += searchCriteriaNames[usedIndecies[i]] + searchCriteria[usedIndecies[i]];
				}
				else{
					urlMods += "&" + searchCriteriaNames[usedIndecies[i]] + searchCriteria[usedIndecies[i]];
				}
			}
			
			//combine base and mods
			url = urlBase + urlMods;
			
		}
		else{
			url = urlBase;
		}
		
	}
}
