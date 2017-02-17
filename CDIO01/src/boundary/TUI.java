package boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TUI {

	static Scanner keyboard = new Scanner(System.in);

	public static int Menu(){
		System.out.println("Vælg en følgende muligheder, ved at taste nummeret foran muligheden.\n1. Opret bruger \n2. Vis brugere \n3. Opdater bruger \n4. Slet brugere. \n5. Log af.");
		int x;
		do {
			x = keyboard.nextInt();
			if(x < 1 || x > 5)
				System.out.println("Ikke gyldig værdi. Prøv igen.");
		} while (x < 1 || x > 5);
		return x;
	}
	
	public static int updateMenu(){
		System.out.println("Vælg en følgende muligheder, ved at taste nummeret foran muligheden.\n1. Skift navn \n2. Skift CPR \n3. Opdater roller \n4. Skift initialer. \n5. Skift password \n6. Gå tilbage til forrige menu.");
		int x;
		do {
			x = keyboard.nextInt();
			if(x < 1 || x > 6)
				System.out.println("Ikke gyldig værdi. Prøv igen.");
		} while (x < 1 || x > 6);
		return x;
	}

	public static void showUser(String a){
		System.out.println(a);
	}

	public static int chooseUser() {
		System.out.println("Vælg en bruger ud fra deres id (mellem 11-99).");
		int x;
		do {
			x = keyboard.nextInt();
			if (x < 11 || x > 99)
				System.out.println("Ikke gyldig værdi. Prøv igen.");
		} while (x < 11 || x > 99);
		return x;
	}

	public static String userName() {
		System.out.println("Indskriv det fulde navn på brugeren.");
		String str;
		str = keyboard.nextLine();
		do {
			str = keyboard.nextLine();
			if (str.length() < 2 || str.length() > 20)
				System.out.println("Navnet er ikke gyldigt. Prøv igen.");
		} while (str.length() < 2 || str.length() > 20);
		return str;
	}

	public static String userCPR() {
		System.out.println("Indskriv CPR for brugeren. Indskriv på følgende form XXXXXX-XXXX.");
		String str;
		do {
			str = keyboard.nextLine();
			if (str.length() != 11)
				System.out.println("CPR er ikke gyldigt. Prøv igen.");
		} while (str.length() != 11);
		return str;
	}

	public static List<String> userRole() {
		ArrayList<String> roles = new ArrayList<String>();
		String str;
		boolean illegalRole;
		do {
			do{
				System.out.println("Indskriv rolle for brugeren. Rollerne kan være: Admin, Pharmacist, Foreman, Operator");
				str = keyboard.nextLine();
				if (!str.equals("Admin") && !str.equals("Pharmacist") && !str.equals("Foreman") && !str.equals("Operator")){
					System.out.println("Rolle er ikke gyldig. Prøv igen.");
					illegalRole = true;
				}
				else{
					illegalRole = false;
				}
				if (roles.contains(str))
					System.out.println("Du har allerede denne rolle");
				else
					roles.add(str);
			} while(TUI.anotherRole());
		} while (illegalRole);
		return roles;
	}
	
	public static String addRole() {
		String str;
		boolean illegalRole;
		do {
			System.out.println("Indskriv rolle for brugeren. Rollerne kan være: Admin, Pharmacist, Foreman, Operator");
			str = keyboard.nextLine();
			if (!str.equals("Admin") && !str.equals("Pharmacist") && !str.equals("Foreman") && !str.equals("Operator")){
				System.out.println("Rolle er ikke gyldig. Prøv igen.");
				illegalRole = true;
			}
			else{
				illegalRole = false;
			}
		} while (illegalRole);
		return str;
	}
	
	public static void hasRole() {
		System.out.println("Brugeren har allerede denne rolle. Prøv igen.");
	}

	public static String userInitials(){
		System.out.println("Indskriv initialerne for brugeren.");
		String str;
		do {
			str = keyboard.nextLine();
			if (str.length() < 2 || str.length() > 4)
				System.out.println("Initialerne er ikke gyldige. Prøv igen.");
		} while (str.length() < 2 || str.length() > 4);
		return str;
	}

	public static String userPassword() {
		String str = keyboard.nextLine();
		return str;
		//Password generator
	}

	public static boolean anotherRole(){
		System.out.println("Vil du gerne tilføje en ekstra rolle til brugeren? ja/nej");
		String str = keyboard.nextLine().toLowerCase();
		if(str.equals("ja"))
			return true;
		else
			return false;

	}
	
	public static int changeUserRole() {
		System.out.println("Vælg en følgende muligheder, ved at taste nummeret foran muligheden. \n1. Fjern rolle. \n2. Tilføj rolle.");
		int x;
		do {
		x = keyboard.nextInt();
		if (x != 1 && x != 2)
			System.out.println("Ugyldig vÃ¦rdi. PrÃ¸v igen.");
		} while (x != 1 && x != 2);
		return x;
	}
	
	public static String removeRole(List<String> roles) {
		System.out.println("Hvilken rolle vil du gerne fjerne? \n" + "Brugeren har følgende roller: " + roles);
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
		System.out.println("Der eksisterer ikke nogen bruger med denne id. Prøv igen.");
	}
	public static void newLine(){
		System.out.println("");
	}

}
