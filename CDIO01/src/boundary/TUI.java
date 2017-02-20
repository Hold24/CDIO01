package boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TUI {

	static Scanner keyboard = new Scanner(System.in);

	public static int Menu(){
		System.out.println("VÃ¦lg en fÃ¸lgende muligheder, ved at taste nummeret foran muligheden.\n1. Opret bruger \n2. Vis brugere \n3. Opdater bruger \n4. Slet brugere. \n5. Log af.");
		String str;
		int x = 0;
		do {
			str = keyboard.nextLine();
			try{
				x = Integer.parseInt(str);
				if (x < 1 || x > 5) throw new NumberFormatException();
			} catch (NumberFormatException ne) {
				//Du er en spade!
			}
			if(x < 1 || x > 5 || str.length() > 1 || str.length() < 1)
				System.out.println("Ikke gyldig vÃ¦rdi. PrÃ¸v igen.");
		} while (x < 1 || x > 5 || str.length() > 1 || str.length() < 1);
		return x;
	}

	public static int updateMenu(){
		System.out.println("VÃ¦lg en fÃ¸lgende muligheder, ved at taste nummeret foran muligheden.\n1. Skift navn \n2. Skift CPR \n3. Opdater roller \n4. Skift initialer. \n5. Skift password \n6. GÃ¥ tilbage til forrige menu.");
		String str;
		int x;
		do {
			str = keyboard.nextLine();
			x = Integer.parseInt(str);
			if(x < 1 || x > 6)
				System.out.println("Ikke gyldig vÃ¦rdi. PrÃ¸v igen.");
		} while (x < 1 || x > 6);
		return x;
	}

	public static void showUser(String a){
		System.out.println(a);
	}

	public static int chooseUser() {
		System.out.println("VÃ¦lg en bruger ud fra deres id (mellem 11-99).");
		String str;
		int x;
		do {
			str = keyboard.nextLine();
			x = Integer.parseInt(str);
			if (x < 11 || x > 99)
				System.out.println("Ikke gyldig vÃ¦rdi. PrÃ¸v igen.");
		} while (x < 11 || x > 99);
		return x;
	}

	public static String userName() {
		System.out.println("Indskriv det fulde navn pÃ¥ brugeren.");
		String str;
		do {
			str = keyboard.nextLine();
			if (str.length() < 2 || str.length() > 20)
				System.out.println("Navnet er ikke gyldigt. PrÃ¸v igen.");
		} while (str.length() < 2 || str.length() > 20);
		return str;
	}

	public static String userCPR() {
		System.out.println("Indskriv CPR for brugeren. Indskriv pÃ¥ fÃ¸lgende form XXXXXX-XXXX.");
		String str;
		do {
			str = keyboard.nextLine();
			if (str.length() != 11)
				System.out.println("CPR er ikke gyldigt. PrÃ¸v igen.");
		} while (str.length() != 11);
		return str;
	}

	public static List<String> userRole() {
		ArrayList<String> roles = new ArrayList<String>();
		String str;
		boolean illegalRole;
		do {
			do{
				System.out.println("Indskriv rolle for brugeren. Rollerne kan vÃ¦re: Admin, Pharmacist, Foreman, Operator");
				str = keyboard.nextLine();
				if (!str.equals("Admin") && !str.equals("Pharmacist") && !str.equals("Foreman") && !str.equals("Operator")){
					System.out.println("Rolle er ikke gyldig. PrÃ¸v igen.");
					illegalRole = true;
				}
				else{
					illegalRole = false;
				}
				if (roles.contains(str))
					System.out.println("Du har allerede denne rolle");
			} while(illegalRole);
			roles.add(str);
		} while (TUI.anotherRole());
		return roles;
	}

	public static String addRole() {
		String str = keyboard.nextLine();
		boolean illegalRole;
		do {
			System.out.println("Indskriv rolle for brugeren. Rollerne kan vÃ¦re: Admin, Pharmacist, Foreman, Operator");
			str = keyboard.nextLine();
			if (!str.equals("Admin") && !str.equals("Pharmacist") && !str.equals("Foreman") && !str.equals("Operator")){
				System.out.println("Rolle er ikke gyldig. PrÃ¸v igen.");
				illegalRole = true;
			}
			else{
				illegalRole = false;
			}
		} while (illegalRole);
		return str;
	}

	public static void hasRole() {
		System.out.println("Brugeren har allerede denne rolle. PrÃ¸v igen.");
	}

	public static String userInitials(){
		System.out.println("Indskriv initialerne for brugeren.");
		String str;
		do {
			str = keyboard.nextLine();
			if (str.length() < 2 || str.length() > 4)
				System.out.println("Initialerne er ikke gyldige. PrÃ¸v igen.");
		} while (str.length() < 2 || str.length() > 4);
		return str;
	}

	public static String userPassword() {
		System.out.println("Du skal nu vælge et password. Passwordet skal indeholde tre af de fire følgende kategorier: \nSmå bogstaver. \nStore bogstaver. \nTal. \nSpecialtegn. ");
		String str = keyboard.nextLine();
		String regExp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,15}$";
		//http://regexlib.com/Search.aspx?k=password&AspxAutoDetectCookieSupport=1
		do {
			str = keyboard.nextLine();
			if (str == "0")
				break;
			if (!str.matches(regExp))
				System.out.println("Ugyldigt password. Prøv igen.");
		} while(!str.matches(regExp));
		//Password generator
		return str;
	}

	public static boolean anotherRole(){
		System.out.println("Vil du gerne tilfÃ¸je en ekstra rolle til brugeren? ja/nej");
		String str = keyboard.nextLine().toLowerCase();
		if(str.equals("ja"))
			return true;
		else
			return false;

	}

	public static int changeUserRole() {
		System.out.println("Vælg en følgende muligheder, ved at taste nummeret foran muligheden. \n1. Fjern rolle. \n2. Tilføj rolle.");
		String str;
		do {
			str = keyboard.nextLine();
			if (str != "1" && str != "2")
				System.out.println("Ugyldig værdi. Prøv igen.");
		} while (str != "1" && str != "2");
		return Integer.parseInt(str);
	}

	public static String removeRole(List<String> roles) {
		System.out.println("Hvilken rolle vil du gerne fjerne? \n" + "Brugeren har fÃ¸lgende roller: " + roles);
		String str;
		do {
			str = keyboard.nextLine();			
		} while(!roles.contains(str));
		return str;
	}

	public static void shutDown() {
		System.out.println("Du er nu logget af.");
	}

	public static void nullUser() {
		System.out.println("Der eksisterer ikke nogen bruger med denne id. PrÃ¸v igen.");
	}
	public static void newLine(){
		System.out.println("");
	}
	public static void nextLine(){
		keyboard.nextLine();
	}
	public static int userId(){
		System.out.println("Vælg en bruger id (mellem 11-99).");
		String str;
		int x;
		do {
			str = keyboard.nextLine();
			x = Integer.parseInt(str);
			if (x < 11 || x > 99)
				System.out.println("Ikke gyldig værdi. Prøv igen.");
		} while (x < 11 || x > 99);
		return x;
	}
	
	public static void idTaken(){
		System.out.println("The user id you picked is already taken");
	}

}
