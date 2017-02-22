package boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TUI {

	static Scanner keyboard = new Scanner(System.in);

	public static int whichProgram() {
		System.out.println("Which of the following programs do you want to run? \n1. Regular program without saving to disk. \n2. Program which reads/writes users to a txt file. "
				+ "\n3. Program which stores users in database.");
		String str;
		int x = 0;
		do {
			str = keyboard.nextLine();
			try{
				x = Integer.parseInt(str);
				if (x < 1 || x > 4) throw new NumberFormatException();
			} catch (NumberFormatException ne) {
				//Du er en spade!
			}
			if(x < 1 || x > 4 || str.length() > 1 || str.length() < 1)
				System.out.println("Not a valid input. Try again.");
		} while (x < 1 || x > 4 || str.length() > 1 || str.length() < 1);
		return x;
	}
	
	public static int Menu(){
		System.out.println("Choose one of the following options: \n1. Create user. \n2. Show users. \n3. Update user. \n4. Delete user. \n5. Log off.");
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
				System.out.println("Not a valid input. Try again.");
		} while (x < 1 || x > 5 || str.length() > 1 || str.length() < 1);
		return x;
	}

	public static int updateMenu(){
		System.out.println("Choose one of the following options: \n1. Change name. \n2. Change CPR. \n3. Update roles. \n4. Change initials. \n5. Change password \n6. Return to the previous menu.");
		String str;
		int x = 0;
		do {
			str = keyboard.nextLine();
			try{
				x = Integer.parseInt(str);
				if (x < 1 || x > 6) throw new NumberFormatException();
			} catch (NumberFormatException ne) {
				//Du er en spade!
			}
			if(x < 1 || x > 6)
				System.out.println("Not a valid input. Try again.");
		} while (x < 1 || x > 6);
		return x;
	}

	public static void showUser(String a){
		System.out.println(a);
	}

	public static int chooseUser() {
		System.out.println("Choose a user based on their id. (between 11-99).");
		String str;
		int x = 0;
		do {
			str = keyboard.nextLine();
			try{
				x = Integer.parseInt(str);
				if (x < 11 || x > 99) throw new NumberFormatException();
			} catch (NumberFormatException ne) {
				//Du er en spade!
			}
			if (x < 11 || x > 99)
				System.out.println("Not a valid input. Try again.");
		} while (x < 11 || x > 99);
		return x;
	}

	public static String userName() {
		System.out.println("Write the name of this user.");
		String str;
		do {
			str = keyboard.nextLine();
			if (str.length() < 2 || str.length() > 20)
				System.out.println("The name is not valid. Try again.");
		} while (str.length() < 2 || str.length() > 20);
		return str;
	}

	public static String userCPR() {
		System.out.println("Write the CPR for this user. Write it on this form: XXXXXX-XXXX.");
		String str;
		do {
			str = keyboard.nextLine();
			if (str.length() != 11)
				System.out.println("CPR is not valid. Try again.");
		} while (str.length() != 11);
		return str;
	}

	public static List<String> userRole() {
		ArrayList<String> roles = new ArrayList<String>();
		String str;
		boolean illegalRole;
		do {
			do{
				System.out.println("Write the roles for the user. The roles can be: Admin, Pharmacist, Foreman, Operator");
				str = keyboard.nextLine();
				if (!str.equals("Admin") && !str.equals("Pharmacist") && !str.equals("Foreman") && !str.equals("Operator")){
					System.out.println("Role is not valid. Try again.");
					illegalRole = true;
				}
				else{
					illegalRole = false;
				}
				if (roles.contains(str))
					System.out.println("You already have this role.");
			} while(illegalRole);
			roles.add(str);
		} while (TUI.anotherRole());
		return roles;
	}

	public static String addRole() {
		String str;
		boolean illegalRole;
		do {
			System.out.println("Write the roles for the user. The roles can be: Admin, Pharmacist, Foreman, Operator");
			str = keyboard.nextLine();
			if (!str.equals("Admin") && !str.equals("Pharmacist") && !str.equals("Foreman") && !str.equals("Operator")){
				System.out.println("Role is not valid. Try again.");
				illegalRole = true;
			}
			else{
				illegalRole = false;
			}
		} while (illegalRole);
		return str;
	}

	public static void hasRole() {
		System.out.println("The user already has this role. Try again.");
	}

	public static String userInitials(){
		System.out.println("Write the initials for this user.");
		String str;
		do {
			str = keyboard.nextLine();
			if (str.length() < 2 || str.length() > 4)
				System.out.println("Initials are not valid. Try again.");
		} while (str.length() < 2 || str.length() > 4);
		return str;
	}

	public static String userPassword() {
		System.out.println("You have to choose a password. The password must contain three of the following four criteria: \nSmall leters. \nBig letters. \nNumbers. \nSpecial characters. ");
		String str;
		String regExp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,15}$";
		//http://regexlib.com/Search.aspx?k=password&AspxAutoDetectCookieSupport=1
		do {
			str = keyboard.nextLine();
			if (str == "0")
				break;
			if (!str.matches(regExp))
				System.out.println("Invalid password. Try again.");
		} while(!str.matches(regExp));
		//Password generator
		return str;
	}

	public static boolean anotherRole(){
		System.out.println("Do you want to add an extra role for this user? yes/no?");
		String str = keyboard.nextLine().toLowerCase();
		if(str.equals("yes"))
			return true;
		else
			return false;

	}

	public static int changeUserRole() {
		System.out.println("Choose one of the following options: \n1. Remove role. \n2. Add role.");
		String str;
		int x = 0;
		do {
			str = keyboard.nextLine();
			try{
				x = Integer.parseInt(str);
				if (x != 1 && x != 2) throw new NumberFormatException();
			} catch (NumberFormatException ne) {
				//Du er en spade!
			}
			if (x != 1 && x != 2)
				System.out.println("Not a valid input. Try again.");
		} while (x != 1 && x != 2);
		return x;
	}

	public static String removeRole(List<String> roles) {
		System.out.println("Which role would you like to remove? \n" + "The user has the following roles: " + roles);
		String str;
		do {
			str = keyboard.nextLine();	
		} while(!roles.contains(str));
		return str;
	}

	public static void shutDown() {
		System.out.println("You are now logged off.");
	}

	public static void nullUser() {
		System.out.println("There doesn't exist any user with this id. Try again.");
	}
	public static void newLine(){
		System.out.println("");
	}
	public static void nextLine(){
		keyboard.nextLine();
	}
	public static int userId(){
		System.out.println("Choose a user id (between 11-99).");
		String str;
		int x = 0;
		do {
			str = keyboard.nextLine();
			try{
				x = Integer.parseInt(str);
				if (x < 11 || x > 99) throw new NumberFormatException();
			} catch (NumberFormatException ne) {
				//Du er en spade!
			}
			if (x < 11 || x > 99)
				System.out.println("Not a valid input. Try again.");
		} while (x < 11 || x > 99);
		return x;
	}

	public static void idTaken(){
		System.out.println("The user id you picked is already taken");
	}

}