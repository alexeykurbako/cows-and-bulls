import java.util.Random;
import java.math.*;
import java.util.Scanner;

public class Game {
	final static Random random = new Random();

	static char checkRepeat(int i,char[] arr) {//generate unique number
		char symbol = (char)(random.nextInt(10) + 48);
		for(int j = 0; j < i; j++)
			if(symbol == arr[j]) {
				symbol = checkRepeat(i, arr);
			}
		return symbol;
	}
	
	static char[] randomize() {//generate array
		char[] array = new char[4];
		for(int i = 0; i < 4; i++) {
			array[i] = checkRepeat(i, array);
		}
		return array;
	}
	
	static int endgame(char[] user_number, char[] secret_number, int count) {
		if(user_number[0] == secret_number[0] && user_number[1] == secret_number[1] && user_number[2] == secret_number[2] && user_number[3] == secret_number[3]) {
			System.out.println("You won");
			System.out.println("Tries:" + ++count);
			return 0;
		} else { 
			System.out.println("Try number: " + ++count);
			System.out.println("");
			return count;
		}	
	}
	
	static int checkBulls(char[] user_number, char[] secret_number) {
		int bulls = 0;
		for(int i = 0; i < 4; i++) {
				if(user_number[i] == secret_number[i])
					bulls++;
		}
		return bulls;
	}
	
	static int checkCows(char[] user_number, char[] secret_number) {
		int cows = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) 
				if(user_number[i] == secret_number[j] && i != j)
					cows++;
		}
		return cows;
	}
	
	static boolean checkUserNumber(char[] user_number) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(user_number[i] == user_number[j] && i != j) {
					System.out.println("Wrong input");
					return true;
				}
				
			}
			if(user_number[i] < 48 || user_number[i] > 57){
				System.out.println("Wrong input");
				return true;
			}
		}
		return false;
	}
	
	static char[] returnArray(char[] user_number, int number) {
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("Input your number:");
			number = input.nextInt();
			if(number / 1000 != 0 && number / 10000 == 0 ) {
				user_number[0] = (char)(number / 1000 + 48);
				user_number[1] = (char)(number / 100 - (user_number[0] - 48) * 10 + 48);
				user_number[2] = (char)(number / 10 - (user_number[0] - 48) * 100 - (user_number[1] - 48) * 10 + 48);
				user_number[3] = (char)(number % 10 + 48);	
			} else continue;
		} while(checkUserNumber(user_number));
		return user_number;
	}
	
	public static void main(String[] args) {
		int cows = 0, bulls = 0, count = 0,number = 0;
		char[] user_number = new char[4], secret_number = randomize();
		do {
			user_number = returnArray(user_number, number);
			bulls = checkBulls(user_number, secret_number);
			cows = checkCows(user_number, secret_number);
			if(bulls != 4)
				System.out.println("Bulls:" + bulls + "  Cows:" + cows );
			count = endgame(user_number, secret_number, count);
		} while(count != 0);
	}
}
