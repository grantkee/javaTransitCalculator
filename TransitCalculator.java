import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class TransitCalculator {
  //fields
  int numOfDays;
  int numOfRides;
  int age;
  boolean discount = false;
  String[] rideType = {"pay-per-ride", "7-day Unlimited", "30-day Unlimited"};
  double[] rideCost =  {2.75, 33.00, 127.00};
  double[] discountCost = {1.35, 16.50, 63.50};
  ArrayList<Double> totalCosts = new ArrayList<Double>();

  //class constructor
  public TransitCalculator(int days, int rides, int ageOfRider){
    numOfDays = days;
    numOfRides = rides;
    age = ageOfRider;
    if(age >= 65){
      discount = true;
    }
  }

  //cost per single ride
  public double singleRidePrice(){
    double[] cost = discount ? discountCost : rideCost;
    double totalCost = cost[0]*numOfRides;
    totalCosts.add(totalCost);
    System.out.println("total cost for pay-per-ride: $" + totalCost);
    return totalCost / numOfRides;
  }

  //cost per ride using 7-day pass
  public double unlimited7Price(){
    double[] cost = discount ? discountCost : rideCost;
    int numberOfPasses = numOfDays / 7 + (numOfDays % 7 == 0 ? 0 : 1);
    System.out.println("number of 7-day passes: " + numberOfPasses);
    double totalCost = cost[1] * numberOfPasses;
    totalCosts.add(totalCost);
    System.out.println("total cost for 7-day option: " + totalCost);
    return totalCost / numOfRides;
  }

  //cost per ride using 30-day pass
  public double unlimited30Price(){
    double[] cost = discount ? discountCost : rideCost;
    int numberOfPasses = numOfDays / 30 + (numOfDays % 30 == 0 ? 0 : 1);
    System.out.println("number of 30-day passes: " + numberOfPasses);
    double totalCost = numberOfPasses * cost[2];
    totalCosts.add(totalCost);
    System.out.println("total cost for 30-day option: " + totalCost);
    return totalCost / numOfRides;
  }

  //create prices array
  public double[] getRidePrices(){
    double singlePass = singleRidePrice();
    double sevenDayPass = unlimited7Price();
    double thirtyDayPass = unlimited30Price();
    double[] prices = {singlePass, sevenDayPass, thirtyDayPass};
    return prices;
  }

  //calculate best price
  public String getBestFare(){
    double[] prices = getRidePrices();
    double lowest = prices[0];
    int index = 0;
    
    //find lowest price
    for(int i = 0; i < prices.length; i++){
      if(prices[i] < lowest){
        lowest = prices[i];
        index = i;
      }
    }

    //convert lowest to two decimal places
    double lowestPrice = Math.round(lowest*100.0)/100.0;

    return "You should get the "+rideType[index]+" option at $"+lowestPrice+" per ride, which comes out to a total of $"+totalCosts.get(index)+".";
  }

  //main method
  public static void main(String[] args){
    int days, rides, age;
    System.out.println("Hello, welcome to optimal fare calculator.");
    try (Scanner in = new Scanner(System.in)){
      System.out.println("How many days will you be using the public transit?");
      days = in.nextInt();
      System.out.println("How many rides will you take?");
      rides = in.nextInt();
      System.out.println("How old are you?");
      age = in.nextInt();
    }

    TransitCalculator test = new TransitCalculator(days, rides, age);
    String result = test.getBestFare();
    System.out.println(result);
  }
}