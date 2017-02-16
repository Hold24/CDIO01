package boundary;

import java.util.Scanner;

public class TUI {

	static Scanner keyboard = new Scanner(System.in);

	public static int Menu(){
		System.out.println("Vælg en følgende muligheder, ved at taste nummeret foran muligheden.\n1. Opret bruger \n2. Vis brugere \n3. Opdater bruger \n4. Slet brugere.");
		int x;
		do {
			x = keyboard.nextInt();
			if(x < 1 || x > 4)
				System.out.println("Ikke gyldig værdi. Prøv igen.");
		} while (x < 1 || x > 4);
		return x;
	}

	public static void showUser(String a){
		System.out.println(a);
	}

	public static int updateUser() {
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

	public static String userRole() {
		System.out.println("Indskriv rolle for brugeren. Rollerne kan være: Admin, Pharmacist, Foreman, Operator");
		String str;
		boolean illegalRole;
		do {
			str = keyboard.nextLine();
			if (!str.equals("Admin") && !str.equals("Pharmacist") && !str.equals("Foreman") && !str.equals("Operator")){
				System.out.println("Rolle er ikke gyldig. Prøv igen.");
				illegalRole = true;
			}
			else
				illegalRole = false;
		} while (illegalRole);
		return str;
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
		return null;
		//Password generator
	}

	

}
