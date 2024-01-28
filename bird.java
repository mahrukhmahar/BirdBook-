

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BirdInfo{
	String name;
	String physical_characteristics;
	String habitat;
	String diet;
	String behaviour;
	String geographical_range;
	String species;
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPhysical_Characteristics(String physical_characteristics) {
		this.physical_characteristics=physical_characteristics;
	}
	public void setHabitat(String habitat) {
		this.habitat=habitat;
	}
	public void setDiet(String diet) {
		this.diet=diet;
	}
	public void setBehaviour(String behaviour) {
		this.behaviour=behaviour;
	}
	public void setGeographical_Range(String geographical_range) {
		this.geographical_range=geographical_range;
	}
	public void setSpecies(String species) {
		this.species=species;
	}
	
	public String getName() {
		return name;
	}
	public String getPhysical_characteristics() {
		return physical_characteristics;
	}
	public String getHabitat() {
		return habitat;
	}
	public String getDiet() {
		return diet;
	}
	public String getGeographical_range() {
		return geographical_range;
	}
	public String getBehaviour() {
		return behaviour;
	}
	public String getSpecies() {
		return species;
	}
		
}

class BirdManager{
	static ArrayList<BirdInfo> birdList;
	
	public BirdManager() {
		
		birdList=new ArrayList<>();
	}
	
	public void addBird(BirdInfo bird){
		birdList.add(bird);
	}
	
	public ArrayList<BirdInfo> getBird() {
		
		return birdList;
	}
	
    public void saveBirdInfoToFile(BirdInfo b) {
    	
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/lenovo/eclipse-workspace/roughpro/src/bird.txt",true))) {
        	    writer.newLine();
                writer.write("Bird Name: "+b.getName());
                writer.newLine();
                writer.write("Bird Species: "+b.getSpecies());
                writer.newLine();
                writer.write("Bird Habitat: "+b.getHabitat());
                writer.newLine();
                writer.write("Bird Diet: "+b.getDiet());
                writer.newLine();
                writer.write("Bird Geographical Range: "+b.getGeographical_range());
                writer.newLine();
                writer.write("Bird Physical Characteristics : "+b.getPhysical_characteristics());
                writer.newLine();
                writer.write("Bird Behaviour: "+b.getBehaviour());
                writer.newLine();
                
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void factsFile(String name) {
    	 String searchTerm = name; // Bird name you want to search for
         

         try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/lenovo/eclipse-workspace/roughpro/src/birdfacts.txt"))) {
             String line;
             boolean foundBird = false;
             StringBuilder birdfacts = new StringBuilder(); // To store all information about the bird

             while ((line = reader.readLine()) != null) {
                 // Assuming each bird's data in the file is formatted as "Bird Name: [Name]"
                 if (line.startsWith("Bird Name: ")) {
                     String birdName = line.substring("Bird Name: ".length());

                     if (birdName.equalsIgnoreCase(searchTerm)) {
                         foundBird = true;
                         birdfacts.append("\n\t\t\t\t\tFacts About : ").append(birdName).append("\n\n");
                         
                         // Continue reading and storing other information about the bird
                         while ((line = reader.readLine()) != null && !line.isEmpty()) {
                             birdfacts.append(line).append("\n\n");
                         }
                     }
                 }
             }

             if (foundBird) {
                 System.out.println( birdfacts.toString());
             } else {
                 System.out.println("\t\t\t\t\tBird not found.");
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     
    	
    	
    }
    
    public void birdSearch(String name) {
        String searchTerm = name; // Bird name you want to search for
       

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/lenovo/eclipse-workspace/roughpro/src/bird.txt"))) {
            String line;
            boolean foundBird = false;
            StringBuilder birdDetails = new StringBuilder(); // To store all information about the bird

            while ((line = reader.readLine()) != null) {
                // Assuming each bird's data in the file is formatted as "Bird Name: [Name]"
                if (line.startsWith("Bird Name: ")) {
                    String birdName = line.substring("Bird Name: ".length());

                    if (birdName.equalsIgnoreCase(searchTerm)) {
                        foundBird = true;
                        
                        
                        // Continue reading and storing other information about the bird
                       birdDetails.append("\t\t\t");
                        while ((line = reader.readLine()) != null && !line.isEmpty()) {
                            birdDetails.append("\t\t"+line).append("\n\n\t\t\t");
                        }
                    }
                }
            }

            if (foundBird) {
                System.out.println("\n\t\t\t\t\t\tBird details:  "+name+"\n\n" + birdDetails.toString());
            } else {
                System.out.println("\t\t\t\t\t\tBird not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
}



class User{
	String name;
	private String password="admin123";

	public void menu() {
		System.out.println("\t\t\t\t\t1. Search Bird Details");
		System.out.println("\t\t\t\t\t2. Facts About Bird");
		System.out.println("\t\t\t\t\t3. Show All Birds List");
		System.out.println("\t\t\t\t\t4. Exit");
	}
	public String getPassword() {
		return this.password;
	}
}

class Admin extends User{
	
	public void menu() {
		System.out.println("\t\t\t\t\t1. Search Bird Details");
		System.out.println("\t\t\t\t\t2. Facts About Bird");
		System.out.println("\t\t\t\t\t3. Show All Birds List");
		System.out.println("\t\t\t\t\t4. Add Bird");
		System.out.println("\t\t\t\t\t5. Exit");
	}
	
}


class MainBird extends Thread{
	User user=new User();
	 User admin=new Admin();
	BirdManager bm=new BirdManager();
	BirdInfo b =new BirdInfo();
	Scanner scan=new Scanner(System.in);
	
	

	    public static List<String> getAllBirdNames(String filePath) {
	        List<String> birdNames = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;

	            while ((line = reader.readLine()) != null) {
	                if (line.startsWith("Bird Name: ")) {
	                    String birdName = line.substring("Bird Name: ".length());
	                    birdNames.add(birdName);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }

	        return birdNames;
	    }
	
	
	public void title() {
	String title=      "\t\t            _.-.                                                  _.-.           \n"
	           + "\t\t        .-.  `) |  .-.            _ _ _         _              .-. `) |  .-.                    \n"
	           + "\t\t    _.'`. .~./  \\.~. .`'._       | _ |_|_ _ __ | |        _.'`. .~./  \\.~. .`'._                  \n"
	           + "\t\t.-'`.'-'.'.-:    ;-.'.'-'.`'-.   | _ \\ | '_/ _\\| |    .-'`.'-'.'.-:    ;-.'.'-'.`'-.             \n"
	           + "\t\t `'`'`'`'`   \\  /   `'`'`'`'`    |_ _/_|_| \\_ _._|     `'`'`'`'`   \\  /   `'`'`'`'`                       \n"
	           + "\t\t             /||\\                                                  /||\\                   \n"
	           + "\t\t            / ^^ \\                                                / ^^ \\           \n"
	           + "\t\t            `'``'`                                                `'``'`             ";

     System.out.println(title);	
}
	public static Boolean Check_if_already_exist(String name) {
		   String searchTerm=name;	
		
			try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/lenovo/eclipse-workspace/roughpro/src/bird.txt"))) {
	            String line;
	            boolean exist = false;
	            StringBuilder birdfacts = new StringBuilder(); // To store all information about the bird

	            while ((line = reader.readLine()) != null) {
	                // Assuming each bird's data in the file is formatted as "Bird Name: [Name]"
	                if (line.startsWith("Bird Name: ")) {
	                    String birdName = line.substring("Bird Name: ".length());

	                    if (birdName.equalsIgnoreCase(searchTerm)) {
	                        exist = true;
	                        break;
	                    }
	                }
	            }
	            return exist;
	            
			}catch(IOException e) {
				e.getStackTrace();
				return false;
			}
				
		}
		
	
	
	
	public void run() {
    String name;
    int choice;
	title();
	System.out.print("\t\t\t\t\t\tEnter Your Name : ");
	user.name=scan.nextLine();
	if(user.name.equalsIgnoreCase("admin")) {
		System.out.print("\t\t\t\t\t\tEnter Password : ");
		 String password=scan.nextLine();
		if(password.equals(admin.getPassword())) {
			System.out.println("\n\t\t\t\t\t\tAdmin Log In successfully!");
			do {
			System.out.println("\n\t\t\t\t\t\tMain Menu: \n");
			admin.menu();
			System.out.println();
			System.out.print("\t\t\t\t\t\tEnter Your choice: ");
			 choice=scan.nextInt();
			 scan.nextLine();
			switch(choice){
			case 1 :  
				System.out.print("\t\t\t\t\tEnter bird name to search : ");
		        name=scan.nextLine();
		        bm.birdSearch(name);
                break;
                
			case 2:
				System.out.print("\t\t\t\t\tEnter Bird's Name to See facts: ");
		        name=scan.nextLine();
		       
		        bm.factsFile(name);
		        break;
		        
			case 3:
				List<String> birdNames = getAllBirdNames("C:/Users/lenovo/eclipse-workspace/roughpro/src/bird.txt");

		        if (birdNames != null) {
		            System.out.println("\n\t\t\t\t\t\tList of Bird :");
		            int i=1;
		            System.out.println();
		            for (String names : birdNames) {
		            	System.out.print("\t\t\t\t\t");
		                System.out.println(+i+". "+ names);
		                 i++;
		            	
		            }
		        } else {
		            System.out.println("\t\t\t\t\tAn error occurred while reading the file.");
		        }
		        break;
				
			case 4:
				
				 System.out.print("\t\t\t\t\tEnter bird name: ");
				 String bird=scan.nextLine();
				  b.setName(bird);
				  if(Check_if_already_exist(bird)) {
					  System.out.print("\t\t\t\t\tThe Bird Already Exists ");
				  }
				  else
				  {
					 
					  System.out.print("\t\t\t\t\tEnter Bird Species: ");
					  b.setSpecies(scan.nextLine());
					  
					  System.out.print("\t\t\t\t\tEnter Bird Habitat: ");
					  b.setHabitat(scan.nextLine());
					  
					  System.out.print("\t\t\t\t\tEnter Bird Diet: ");
					  b.setDiet(scan.nextLine());
					  
					  System.out.print("\t\t\t\t\tEnter Bird Geographical Range: ");
					  b.setGeographical_Range(scan.nextLine());
		
					  System.out.print("\t\t\t\t\tEnter Bird Physical Characteristics: ");
					  b.setPhysical_Characteristics(scan.nextLine());
					  
					  System.out.print("\t\t\t\t\tEnter Bird Behaviour: ");
					  b.setBehaviour(scan.nextLine());
					  
					  bm.addBird(b);
					  bm.saveBirdInfoToFile(b);
				
			       }
			case 5:
				System.out.println("\t\t\t\t\t\t\tThanks for Using!");
			
			
			
			}
			
		}while(choice!=5);
	}
		else
			System.out.println("\t\t\t\tWrong Password");
		    
	}
	else {
		System.out.println();
		System.out.println("\t\t\t\t\t\tWelcome "+user.name+"!");
	
		do {
			System.out.println("\n\t\t\t\t\t\tMain Menu : \n");
			user.menu();
			System.out.println();
			System.out.print("\t\t\t\t\t\tEnter Your choice: ");
			 choice=scan.nextInt();
			 scan.nextLine();
			switch(choice){
			case 1 :  
				System.out.print("\t\t\t\t\tEnter bird name to search:  ");
		        name=scan.nextLine();
		        bm.birdSearch(name);
                break;
                
			case 2:
				System.out.print("\t\t\t\t\tEnter Bird's Name to See facts: ");
		        name=scan.nextLine();
		        bm.factsFile(name);
		        break;
		        
			case 3:
				List<String> birdNames = getAllBirdNames("C:/Users/lenovo/eclipse-workspace/roughpro/src/bird.txt");

		        if (birdNames != null) {
		            System.out.println("\n\t\t\t\t\t\tList of Bird :\n");
		            int i=1;
		           for (String names : birdNames) {
		        	   System.out.print("\t\t\t\t\t\t");
		                System.out.println(i+". "+ names);
		                 i++;
		            	
		            }
		        } else {
		            System.out.println("\t\t\t\tAn error occurred while reading the file.");
		        }
		        break;
			case 4:
				System.out.println("\t\t\t\t\t\t\tThanks For Using");
				break;
			default:
				System.out.println("\t\t\t\t\tInvalid Input");
			
		}
      }while(choice!=4);
	}
	}
}


	
public class bird {
	public static void main(String[] args) {
		MainBird b1=new MainBird();
		b1.start();
	 	 }
     }