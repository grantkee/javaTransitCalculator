import java.util.Arrays;
import java.lang.Math;

public class TransitCalculator {
  //fields
  int numOfDays;
  int numOfRides;
  String[] rideType = {"pay-per-ride", "7-day Unlimited", "30-day Unlimited"};
  double[] rideCost = {2.75, 33.00, 127.00};

  //class constructor
  public TransitCalculator(int days, int rides){
    numOfDays = days;
    numOfRides = rides;
  }

  public double singleRidePrice(){
    double totalCost = numOfDays*numOfRides;
    System.out.println("total cost for pay-per-ride: $" + totalCost);
    return numOfRides * rideCost[0];
  }

  //cost per ride using 7-day pass
  public double unlimited7Price(){
    int numberOfPasses = numOfDays / 7 + (numOfDays % 7 == 0 ? 0 : 1);
    System.out.println("number of 7-day passes: " + numberOfPasses);
    double totalCost = rideCost[1] * numberOfPasses;
    System.out.println("total cost for 7-day option: " + totalCost);
    return totalCost / numOfRides;
  }

  public double unlimited30Price(){
    int numberOfPasses = numOfDays / 30 + (numOfDays % 30 == 0 ? 0 : 1);
    System.out.println("number of 30-day passes: " + numberOfPasses);
    double totalCost = numberOfPasses * rideCost[2];
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
    for(int i = 0; i < prices.length; i++){
      if(prices[i] < lowest){
        lowest = prices[i];
        index = i;
      }
    }
    double lowestPrice = Math.round(lowest*100.0)/100.0;
    return "You should get the "+rideType[index]+" option at $"+rideCost[index]+", which comes out to only $"+lowestPrice+" per ride.";
  }

  //main method
  public static void main(String[] args){
    TransitCalculator test = new TransitCalculator(26, 54);
    String result = test.getBestFare();
    System.out.println(result);
  }
}