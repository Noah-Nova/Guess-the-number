import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	/* Variables globales de classes */
	private static int toGuess;
	private static int numberOfTries;
	private static Scanner sc;

	public static void main(String[] args) {
		initialisation();
		if (menu()) {
			do {
				jeu();
			} while (again());
		}
	}

	private static void initialisation() {
		toGuess = (int) (Math.random() * 100); // aléatoire entre 0 - 100
		numberOfTries = 0;
		sc = new Scanner(System.in);
	}

	private static boolean menu() {
		System.out.println("Bonjour Utilisateur, veux-tu jouer avec moi à \"Plus grand ou plus petit\"? [Y/N]");
		String answer = sc.next();

		switch (answer.toUpperCase()) {
		case "Y":
		case "O":
			return true;
		case "N":
			return false;
		default:
			return false;
		}
	}

	private static boolean jeu() {
		// boucle dans le cas où on a pas return de résultat juste
		while (true) {
			// check si ton scanner prend bien un int
			System.out.println("Tentez de trouver le chiffre entre 0 & 100 : [X pour sortir]");
			int guess;
			try {
				guess = sc.nextInt();

				// check si le guess est le bon
				switch (checkResult(guess)) {
				case 1:
					System.out.println("Votre nombre est trop grand.");
					break;
				case 0:
					System.out.printf("Vous avez trouvé le bon nombre en %s essais !\n", numberOfTries);
					return true;
				case -1:
					System.out.println("Votre nombre est trop petit.");
					break;
				}
			} catch (InputMismatchException e) {
				if (sc.next().toUpperCase().equals("X"))
					return false;

				System.out.println("Veuillez mettre un nombre !");
				if (sc.hasNext()) // nettoie le scanner
					sc = new Scanner(System.in);
			}
		}
	}

	private static int checkResult(int guess) {
		numberOfTries++;
		return guess < toGuess ? -1 : guess > toGuess ? 1 : 0; // return (condition) ? "if true" : "if false";

		// if(guess < toGuess) // ?
		// return -1; // <- du :
		// else if(guess > toGuess) // -> du :
		// return 1;
		// else
		// return 0;
	}

	private static boolean again() {
		System.out.println("Voulez-vous rejouer? [Y/N]");
		String answer = sc.next().toUpperCase();

		toGuess = (int) (Math.random() * 100);
		numberOfTries = 0;

		return answer.equals("Y") || answer.equals("O") ? true : false;
	}
}