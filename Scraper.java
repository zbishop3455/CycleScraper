import java.io.IOException;

import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.Vector;


@SuppressWarnings("unused")
public class Scraper {


	public static void main(String[] args) {
		boolean keepGoing = true;
		int searchAmount;
		String menuOption;
		String criteriaOption;
		boolean keepSelecting;
		boolean keepInputing;
		Craigslist cl;
		MainMenu menu;
		Scanner scanny = new Scanner(System.in);

		//2D array, each row is a motercycle listing
		Object[][] allData = null;
		//array of the column names for displaying search results
		String[] columnNames = {"Name", "Price","Date Posted", "Location", "Link"};


		//search variable
		String minPrice = null;
		String maxPrice = null;
		String makeModel = null;
		String minEngineSize = null;
		String maxEngineSize = null;
		String minYear = null;
		String maxYear = null;
		String minMiles = null;
		String maxMiles = null;
		String[] searchCriteria;


		System.out.println("Welcome to CycleScraper, here to help you find a motercycle! \n");

		//main menu


		//main loop
		while(keepGoing){


			System.out.println("1) Enter search criteria");
			System.out.println("2) Submit Search");
			System.out.println("3) Show results");
			System.out.println("4 Run periodically");
			System.out.println("5) Exit");

			System.out.print("Enter your selection: ");

			menuOption = scanny.nextLine();
			System.out.println("\n");

			if(menuOption.equals("1")){
				//get search criteria, use another menu

				keepSelecting = true;

				while(keepSelecting){
					System.out.println("Select an search parameter to change (not required, if no preference enter 0)\n");
					System.out.println("1) Minimum price");
					System.out.println("2) Maximum price");
					System.out.println("3) Make or model");
					System.out.println("4) Minimum engine displacement");
					System.out.println("5) Maximum engine displacement");
					System.out.println("6) Minimum year");
					System.out.println("7) Maximum year");
					System.out.println("8) Minimum miles");
					System.out.println("9) Maximum miles");
					System.out.println("0) Back");
					System.out.print("Enter your selection: ");

					criteriaOption = scanny.nextLine();

					if(criteriaOption.equals("1")){
						//set min price;
						keepInputing = true;
						while(keepInputing){
							//set min price
							System.out.print("Enter your min price: ");
							minPrice = scanny.nextLine();


							//validate the input and check if 0
							if(validateInt(minPrice)){
								//they entered a valid int
								keepInputing = false;
								continue;
							}
							if(minPrice.equals("0")){

								minPrice = null;
								keepInputing = false;
								continue;
							}
							else{
								System.out.println("Invalid input; whole numbers only");
								minPrice = null;
							}
						}
					}

					if(criteriaOption.equals("2")){
						// set max price
						keepInputing = true;
						while(keepInputing){
							//set min price
							System.out.print("Enter your max"+ " price: ");
							maxPrice = scanny.nextLine();

							//validate the input and check if 0
							if(validateInt(maxPrice)){
								//they entered a valid int
								keepInputing = false;
								continue;

							}
							if(maxPrice.equals("0")){
								maxPrice = null;
								keepInputing = false;
								continue;
							}
							else{
								System.out.println("Invalid input; whole numbers only");
								maxPrice = null;
							}
						}
					}

					if(criteriaOption.equals("3")){
						//make and model
						System.out.print("Enter make or model:");
						makeModel = scanny.nextLine();

						if(makeModel.equals("0")){
							makeModel = null;
						}
					}

					if(criteriaOption.equals("4")){
						// set max price
						keepInputing = true;
						while(keepInputing){
							//engine min size
							System.out.print("Enter your min engine displacement: ");
							minEngineSize = scanny.nextLine();


							//validate the input and check if 0
							if(validateInt(minEngineSize)){
								//they entered a valid int
								keepInputing = false;
								continue;

							}
							if(minEngineSize.equals("0")){
								minEngineSize = null;
								keepInputing = false;
								continue;
							}
							else{
								System.out.println("Invalid input; whole numbers only");
								minEngineSize = null;
							}
						}
					}

					if(criteriaOption.equals("5")){
						//engine size max
						keepInputing = true;
						while(keepInputing){
							//engine min size
							System.out.print("Enter your max engine displacement: ");
							maxEngineSize = scanny.nextLine();

							//validate the input and check if 0
							if(validateInt(maxEngineSize)){
								//they entered a valid int
								keepInputing = false;
								continue;
							}
							if(maxEngineSize.equals("0")){
								maxEngineSize = null;
								keepInputing = false;
								continue;
							}
							else{
								System.out.println("Invalid input; whole numbers only");
								maxEngineSize = null;
							}
						}
					}



					if(criteriaOption.equals("6")){
						//min year
						keepInputing = true;
						while(keepInputing){
							//engine min size
							System.out.print("Enter your min year: ");
							minYear = scanny.nextLine();

							//validate the input and check if 0
							if(validateInt(minYear) && minYear.length() == 4){
								//they entered a valid int
								keepInputing = false;
								continue;
							}
							if(minYear.equals("0")){
								minYear = null;
								keepInputing = false;
								continue;
							}
							else{
								System.out.println("Invalid input; 4 numbers only");
								minYear = null;
							}
						}
					}

					if(criteriaOption.equals("7")){
						//max year
						keepInputing = true;
						while(keepInputing){
							//engine min size
							System.out.print("Enter your max year ");
							maxYear = scanny.nextLine();

							//validate the input and check if 0
							if(validateInt(maxYear) && maxYear.length() == 4){
								//they entered a valid int
								keepInputing = false;
								continue;
							}
							if(maxYear.equals("0")){
								maxYear = null;
								keepInputing = false;
								continue;
							}
							else{
								System.out.println("Invalid input; 4 numbers only");
								maxYear = null;
							}
						}
					}

					if(criteriaOption.equals("8")){
						//min miles
						keepInputing = true;
						while(keepInputing){
							//engine min size
							System.out.print("Enter your min miles: ");
							minMiles = scanny.nextLine();

							//validate the input and check if 0
							if(validateInt(minMiles)){
								//they entered a valid int
								keepInputing = false;
								continue;
							}
							if(minMiles.equals("0")){
								minMiles = null;
								keepInputing = false;
								continue;
							}
							else{
								System.out.println("Invalid input; whole numbers only");
								minMiles = null;
							}
						}
					}

					if(criteriaOption.equals("9")){
						//max miles
						keepInputing = true;
						while(keepInputing){
							//engine min size
							System.out.print("Enter your max miles: ");
							maxMiles = scanny.nextLine();

							//validate the input and check if 0
							if(validateInt(maxMiles)){
								//they entered a valid int
								keepInputing = false;
								continue;
							}
							if(maxMiles.equals("0")){
								maxMiles = null;
								keepInputing = false;
								continue;

							}
							else{
								System.out.println("Invalid input; whole numbers only");
								maxMiles = null;
							}
						}
					}

					if(criteriaOption.equals("0")){
						//exit

						keepSelecting = false;

					}
				}
			}

			if(menuOption.equals("2")){
				//scrape data from web and add store results
				searchCriteria = new String[]{minPrice,maxPrice,makeModel,minEngineSize,maxEngineSize,minYear,maxYear,minMiles,maxMiles};
				cl = new Craigslist(searchCriteria);

				//set the size off "allData" to the sum of listing from each site
				int numberOfTitles = cl.titles.length;
				allData = new Object[numberOfTitles][columnNames.length];

				//loop through each columnName
				for(int i=0; i <columnNames.length;i++){
					 for(int j=0;j<numberOfTitles;j++){
						 if(i==0){
							 //title
							 allData[j][i] = cl.titles[j];
						 }
						 if(i==1){
							 //price
							 allData[j][i] = cl.prices[j];
						 }
						 if(i==2){
							 //date
							 allData[j][i] = cl.dates[j];
						 }
						 if(i==3){
							 //location
							 allData[j][i] = cl.locations[j];
						 }
						 if(i==4){
							 //link
							 allData[j][i] = cl.links[j];
						 }
					 }
				}
			}

			if(menuOption.equals("3")){
				//print results, perhaps more printing options and sorting options later
				ResultsWindow results = new ResultsWindow(allData,columnNames);

			}

			if(menuOption.equals("4)")){
				int speed;
				Timer timer = new Timer();
				}

			if(menuOption.equals("5")){
				//exit program
				System.out.println("Goodbye!");
				keepGoing = false;
				scanny.close();
			}


		}

	}

	public static boolean validateInt(String x){
		//try to convert string to int, if we can, return true
		int number;
		boolean valid = false;
		try{
			number = Integer.parseInt(x);
			if(number > 0){
				valid = true;
			}
		}
		catch(Exception e){
			valid = false;
		}
		return valid;
	}

}

