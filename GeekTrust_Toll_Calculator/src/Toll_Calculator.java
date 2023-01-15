
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Toll_Calculator {
	
	public static int total_toll_collection = 0 ;
	public static int totalDiscount = 0 ;
	public static int fastTagBalance = 0 ;
	public static int cashBalance = 0 ;
	public static HashMap<String ,Integer> vehicleFastagData = new HashMap<>() ;   //Vehicle Information ,fastag money ;
	public static HashMap<String , Integer> vehicleJourneyData = new HashMap<>();  // Vehicle number , crosses;
	public static HashMap<String ,Integer> vehicleCrosses = new HashMap<>() ;       // Type of vehicle ,corsses ;
	public static HashMap<String ,Integer > vehicleMoneyCollection = new HashMap<>();   // Type of vehicle ,money collection;
	
	
    public static void main(String[] args)  {
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]); 
            Scanner sc = new Scanner(fis); // file to be scanned 
        	
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
        	
            		
               String inputCommand = sc.nextLine();
               
               //Add your code here to process input commands.
               String[] input = inputCommand.trim().split(" ") ;
              
             
               if(input[0].equals("FASTAG")) {
            	  
            	    
            	   int fastagBalance = Integer.parseInt(input[2]) ;
            	  
            	   vehicleFastagData.put(input[1], fastagBalance);
            	   
            	   
               }else if(input[0].equals("COLLECT_TOLL") ) {
            	   
            	   if(vehicleCrosses.containsKey(input[1])) {
         			  
            		   vehicleCrosses.put(input[1], vehicleCrosses.get(input[1])+1) ;
         			 
         		   }
            	   else {
         			  vehicleCrosses.put(input[1], 1);
         		   }
            	 int amount ;
            	  if(vehicleFastagData.get(input[2])==null) {
            		  amount = 0 ;
            	  }
            	  else {
            		  amount = vehicleFastagData.get(input[2]) ;
            	  }
            	  
            	   final int flat_fee=40;
            	   int balanceLeft = 0 ;
            	   
            	   
            	   
            	   if(input[1].equals("CAR") || input[1].equals("VAN") || input[1].equals("RICKSHAW")) {
            		   
            		
            		   
		            		  if(vehicleJourneyData.containsKey(input[2])) {
		            			   
		            			   vehicleJourneyData.remove(input[2]);
		            			   
		            			   if(amount >=50) {
		            				   
		            				   vehicleFastagData.put(input[2], amount-50) ;  
		            				   total_toll_collection += 50 ;
		            				   fastTagBalance += 50 ;
		            				   
		            				   totalDiscount += 50 ;
		            				   
		            				   if(vehicleMoneyCollection.containsKey(input[1])) {
		            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+50);
		            				   }else {
		            					   vehicleMoneyCollection.put(input[1], 50);
		            				   }
		            				   
		            			   }else {
		            				   
		            				   balanceLeft = 50+flat_fee - amount ;
		            				   total_toll_collection += 50+flat_fee ;
		            				   fastTagBalance += amount ;
		            				   cashBalance += balanceLeft  ;
		            				   totalDiscount += 50 ;
		            				   
		            				   if(vehicleMoneyCollection.containsKey(input[1])) {
		            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+flat_fee);
		            				   }else {
		            					   vehicleMoneyCollection.put(input[1], flat_fee);
		            				   }
		            			   }
		            			   
		            			   
		            		   }else {
		            			   vehicleJourneyData.put(input[2],1) ;
		            			   
		            			   
		            			   if(amount >=100) {
		            				   vehicleFastagData.put(input[2], amount -100) ;
		            				   total_toll_collection += 100 ;
		            				   fastTagBalance +=100 ;
		            				   
		            				   if(vehicleMoneyCollection.containsKey(input[1])) {
		            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+100);
		            				   }else {
		            					   vehicleMoneyCollection.put(input[1], 100);
		            				   }
		            				   
		            			   }else {
		            				    balanceLeft = 140 - amount ;
		            				   vehicleFastagData.put(input[2], 0) ;
		            				   total_toll_collection += 100 + flat_fee ;
		            				   fastTagBalance += amount ;
		            				   cashBalance += balanceLeft  ;
		            				   
		            				   if(vehicleMoneyCollection.containsKey(input[1])) {
		            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+100+flat_fee);
		            				   }else {
		            					   vehicleMoneyCollection.put(input[1], 100+flat_fee);
		            				   }
		            			   }
		            			   
		            		   }
		            		
            		   
            	    
            	   }else if(input[1].equals("SCOOTER")||input[1].equals("MOTORBIKE")) {
            		   
		                           if(vehicleJourneyData.containsKey(input[2])) {
		            			   
		                        	   vehicleJourneyData.remove(input[2]);
				            			   
				            			   if(amount >=25) {
				            				   
				            				   vehicleFastagData.put(input[2], amount-25) ;  
				            				   total_toll_collection += 25 ;
				            				   fastTagBalance += 25 ;
				            				   totalDiscount += 25 ;
				            				   
				            				   if(vehicleMoneyCollection.containsKey(input[1])) {
				            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+25);
				            				   }else {
				            					   vehicleMoneyCollection.put(input[1], 25);
				            				   }
				            				   
				            			   }else {
				            				   
				            				   balanceLeft = 75 - amount ;
				            				   total_toll_collection += 75 ;
				            				   fastTagBalance += amount ;
				            				   cashBalance += balanceLeft  ;
				            				   totalDiscount += 25 ;
				            				   
				            				   if(vehicleMoneyCollection.containsKey(input[1])) {
				            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+75);
				            				   }else {
				            					   vehicleMoneyCollection.put(input[1], 75);
				            				   }
				            			   }
		            			   
            			   
			            		      }else {
					            			   vehicleJourneyData.put(input[2],1) ;
					            			   
					            			   
					            			   if(amount>=50) {
					            				   vehicleFastagData.put(input[2], amount -50) ;
					            				   total_toll_collection += 50 ;
					            				   fastTagBalance +=50 ;
					            				   
					            				   if(vehicleMoneyCollection.containsKey(input[1])) {
					            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+50);
					            				   }else {
					            					   vehicleMoneyCollection.put(input[1], 50);
					            				   }
					            			   }else {
					            				    balanceLeft = 50+flat_fee - amount ;
					            				   vehicleFastagData.put(input[2], 0) ;
					            				   total_toll_collection += 50+flat_fee ;
					            				   fastTagBalance += amount ;
					            				   cashBalance += balanceLeft  ;
					            				   
					            				   if(vehicleMoneyCollection.containsKey(input[1])) {
					            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+50+flat_fee);
					            				   }else {
					            					   vehicleMoneyCollection.put(input[1], 50+flat_fee);
					            				   }
					            			   }
			            			   
			            		            }
			            		   
			            	   }else if(input[1].equals("TRUCK")||input[1].equals("BUS")) {
			            		   
		                            if(vehicleJourneyData.containsKey(input[2])) {
		            			   
		                            	vehicleJourneyData.remove(input[2]);
				            			   
				            			   if(amount >=100) {
				            				   
				            				   vehicleFastagData.put(input[2], amount-100) ;  
				            				   total_toll_collection += 100 ;
				            				   fastTagBalance += 100 ;
				            				   totalDiscount += 100 ;
				            				   
				            				   if(vehicleMoneyCollection.containsKey(input[1])) {
				            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+100);
				            				   }else {
				            					   vehicleMoneyCollection.put(input[1], 100);
				            				   }
				            				   
				            			   }else {
				            				   
				            				   balanceLeft = 100+flat_fee - amount ;
				            				   total_toll_collection += 100+flat_fee ;
				            				   fastTagBalance += amount ;
				            				   cashBalance += balanceLeft  ;
				            				   totalDiscount += 100 ;
				            				   
				            				   if(vehicleMoneyCollection.containsKey(input[1])) {
				            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+100+flat_fee);
				            				   }else {
				            					   vehicleMoneyCollection.put(input[1], 100+flat_fee);
				            				   }
				            			   }
		            			   
		            			   
		            		   }else {
			            			   vehicleJourneyData.put(input[2],1) ;
			            			   
			            			   
			            			   if(amount>=200) {
			            				   vehicleFastagData.put(input[2], amount -200) ;
			            				   total_toll_collection += 200 ;
			            				   fastTagBalance +=200 ;
			            				   
			            				   if(vehicleMoneyCollection.containsKey(input[1])) {
			            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+200);
			            				   }else {
			            					   vehicleMoneyCollection.put(input[1], 200);
			            				   }
			            				   
			            			   }else {
			            				    balanceLeft = 200+flat_fee - amount ;
			            				   vehicleFastagData.put(input[2], 0) ;
			            				   total_toll_collection += 200+flat_fee  ;
			            				   fastTagBalance += amount ;
			            				   cashBalance += balanceLeft  ;
			            				   if(vehicleMoneyCollection.containsKey(input[1])) {
			            					   vehicleMoneyCollection.put(input[1], vehicleMoneyCollection.get(input[1])+200+flat_fee);
			            				   }else {
			            					   vehicleMoneyCollection.put(input[1], 200+flat_fee);
			            				   }
			            			   }
		            			   
		            		   }
		            		   
		            	
			            	   }  
            	   
               }
               else { 
            	   
            	System.out.println("PLEASE PROVIDE PROPER INPUT");
            	
            	   
               }
            }
            	
            	
                  System.out.println("TOTAL_COLLECTION"+" "+total_toll_collection +" "+ totalDiscount);
            	  
            	  System.out.println("PAYMENT_SUMMARY"+" "+fastTagBalance + " "+cashBalance);
            	  System.out.println("VEHICLE_TYPE_SUMMARY");
            	  
            	  List<Map.Entry<String,Integer>> listOfVehicleType = new LinkedList<Map.Entry<String , Integer> >(vehicleMoneyCollection.entrySet());
            	  
            	  Collections.sort( listOfVehicleType , new Comparator<Map.Entry<String , Integer>>(){
            		  public int compare(Map.Entry<String , Integer> obj1 , Map.Entry<String , Integer> obj2) {
            			  return (obj2.getValue()).compareTo(obj1.getValue());
            		  }
            	  });
            	 
            	  for(Map.Entry<String ,Integer> entry : listOfVehicleType) {
            		
            		  System.out.println(entry.getKey() + " " + vehicleCrosses.get(entry.getKey()) ) ;
            	  }
            sc.close(); 
        } catch (Exception e) {
        	System.out.println(e);
        }
	}
}
