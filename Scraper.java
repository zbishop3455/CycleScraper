import java.io.IOException;

import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Scraper {

	public static void main(String[] args) {
		
		boolean keepGoing = true;
		int searchAmount;
		String menuOption;
		String criteriaOption;
		boolean keepSelecting;
		Scanner scanny = new Scanner(System.in);
		
		//search variable
		String minPrice;
		String maxPrice;
		String makeModel;
		String engineSizeMin;
		String engineSizeMax;
		String yearMin;
		String yearMax;
		String minMiles;
		String maxMiles;
		
		System.out.println("Welcome to CycleScraper, here to help you find a motercycle! \n");
		
		//main menu

		
		//main loop
		while(keepGoing){
			System.out.println("1) Enter search criteria");
			System.out.println("2) Submit Search");
			System.out.println("3) Show results");
			System.out.println("4) Exit");
			
			System.out.print("Enter your selection: ");
			
			menuOption = scanny.nextLine();
			
			if(menuOption.equals("1")){
				//get search criteria, use another menu
				
				keepSelecting = true;
				
				while(keepSelecting){
					System.out.println("Select an search parameter to change (not required)\n");
					System.out.println("1) Minimum price");
					System.out.println("2) Maximum price");
					System.out.println("3) Make or model");
					System.out.println("4) Minimum engine displacement");
					System.out.println("5) Maximum engine displacement");
					System.out.println("6) Minimum year");
					System.out.println("7) Maximum year");
					System.out.println("8) Minimum miles");
					System.out.println("9) Maximum miles");
					System.out.print("Enter your selection: ");
					
					criteriaOption = scanny.nextLine();
					
					switch(criteriaOption){
					case "1":
						System.out.println("kappa");
						continue;
					case "2":
					case "3":
					case "4":
					case "5":
					case "6":
					case "7":
					case "8":
					case "9":
					case "0":
						keepSelecting = false;
					default:
						System.out.println("");
					}
				}
			}
			
			if(menuOption.equals("2")){
				//scrape data from web
			}
			
			if(menuOption.equals("3")){
				//print results, perhaps more printing options and sorting options later
			}
			
			if(menuOption.equals("4")){
				//exit program
				System.out.println("Goodbye!");
				keepGoing = false;
			}
			
			
			
			
//			
//		}
//		price = scanny.nextLine();
//		try{
//			searchAmount = Integer.parseInt(price);
//			keepGoing = false;
//			}catch(Exception e){
//				System.out.println("Invalid Amount");
//
		}
		scanny.close();
		
		
		
		

	}

}
