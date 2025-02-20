import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    private static final String WORD_LIST_FILE = "words.txt"; // File containing the list of words
    private static final int MAX_TRIES = 6; // Number of allowed incorrect guesses

    private String secretWord;
    private char[] guessedLetters;
    private int remainingTries;
    private List<Character> incorrectGuesses;

    public Hangman() {
        this.secretWord = chooseRandomWord();
        this.guessedLetters = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            guessedLetters[i] = '_';
        }
        this.remainingTries = MAX_TRIES;
        this.incorrectGuesses = new ArrayList<>();
    }

    private String chooseRandomWord() {
        List<String> words = new ArrayList<>();
        try {
            File file = new File(WORD_LIST_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine().trim().toUpperCase()); // Convert to uppercase for consistency
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: Word list file not found: " + WORD_LIST_FILE);
            System.exit(1); // Exit if the file is not found (critical error)
        }

        if (words.isEmpty()) {
            System.err.println("Error: Word list file is empty.");
            System.exit(1);  // Exit if the file is empty (critical error)
        }

        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (remainingTries > 0 && !isWordGuessed()) {
            displayGameState();

            System.out.print("Enter your guess (a letter or the entire word): ");
            String guess = scanner.nextLine().trim().toUpperCase(); // Convert to uppercase for consistency

            if (guess.length() == 1) {
                char guessedChar = guess.charAt(0);
                if (!Character.isLetter(guessedChar)) {
                    System.out.println("Invalid input. Please enter a letter.");
                    continue; // Skip to the next iteration
                }
                processLetterGuess(guessedChar);
            } else if (guess.length() > 1) {
                processWordGuess(guess);
            } else {
                System.out.println("Invalid input. Please enter a letter or the entire word.");
            }
        }

        if (isWordGuessed()) {
            System.out.println("Congratulations! You guessed the word: " + secretWord);
        } else {
            System.out.println("You ran out of tries. The word was: " + secretWord);
        }

        scanner.close();
    }

    private void displayGameState() {
        System.out.println("\nWord: " + String.valueOf(guessedLetters));
        System.out.println("Incorrect guesses: " + incorrectGuesses);
        System.out.println("Remaining tries: " + remainingTries);
        System.out.println(drawHangman());
    }


    private void processLetterGuess(char guessedChar) {
        boolean correctGuess = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guessedChar) {
                guessedLetters[i] = guessedChar;
                correctGuess = true;
            }
        }

        if (correctGuess) {
            System.out.println("Correct guess!");
        } else {
            System.out.println("Incorrect guess.");
            if (!incorrectGuesses.contains(guessedChar)) {  //avoid duplicate incorrect guesses
                 incorrectGuesses.add(guessedChar);
                 remainingTries--;
            }
        }
    }

    private void processWordGuess(String guessedWord) {
        if (secretWord.equals(guessedWord)) {
            // Player guessed the entire word correctly
            for(int i = 0; i < secretWord.length(); i++){
                guessedLetters[i] = secretWord.charAt(i);
            }
        } else {
            System.out.println("Incorrect word guess.");
            remainingTries--; // or a larger penalty if desired
        }
    }

    private boolean isWordGuessed() {
        return String.valueOf(guessedLetters).equals(secretWord);
    }

    private String drawHangman() {
        switch (MAX_TRIES - remainingTries) {
            case 0:
                return "  +---+\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 1:
                return "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 2:
                return "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 3:
                return "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 4:
                return "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========";
            case 5:
                return "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "=========";
            case 6:
            default: // Game over
                return "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "=========";
        }
    }


    public static void main(String[] args) {
        Hangman game = new Hangman();
        game.play();
    }
}