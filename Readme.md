# Hangman Game

## Documentation

This document provides an overview of the Hangman game project, including design choices, challenges, algorithms, data structures, improvements, and file usage.

### Project Description

The Hangman Game is a classic word-guessing game implemented in Java. The player must guess a hidden word by suggesting letters within a limited number of attempts. The game provides feedback on correct and incorrect guesses and visually represents the remaining attempts using a hangman drawing.

### Design Choices

*   **Console Interface:** The game uses a command-line interface for simplicity and ease of development.
*   **Word List File:** The words to be guessed are read from an external file, allowing for easy modification and expansion of the word list.
*   **Case-Insensitivity:** The game is case-insensitive, allowing players to guess letters in either upper or lower case.
*   **Error Handling:** The game includes error handling for file operations (e.g., file not found, empty file) and invalid input.
*   **Modular Design:** The code is structured into methods to improve readability and maintainability.

### Algorithms and Data Structures

*   **Random Word Selection:** The `chooseRandomWord()` method uses the `Random` class to randomly select a word from the list of words read from the file.
*   **Hidden Word Representation:** The `guessedLetters` array (of type `char[]`) is used to represent the hidden word, with underscores (`_`) initially replacing unguessed letters. As the player guesses letters correctly, the corresponding underscores are replaced with the correct letters.
*   **Letter Guessing:** The `processLetterGuess()` method iterates through the `secretWord` and updates the `guessedLetters` array if a correct guess is made.
*   **Incorrect Guesses:** The `incorrectGuesses` list (of type `ArrayList<Character>`) stores the letters that the player has guessed incorrectly. This prevents duplicate penalties and provides feedback to the player.
*   **Hangman Drawing:** The `drawHangman()` method uses a `switch` statement to draw the hangman figure based on the number of remaining attempts.

### Challenges Encountered

*   **File Handling:**  Ensuring that the game handles cases where the word list file is missing, empty, or contains invalid data.
*   **Input Validation:**  Validating user input to ensure that only letters are accepted as guesses and handling the case where the user enters the same letter multiple times.
*   **Case-Insensitivity:** Implementing case-insensitive comparisons to make the game more user-friendly.

### Improvements Made

*   **Robust Error Handling:** Added comprehensive error handling to gracefully handle file-related errors.
*   **Input Validation:** Implemented input validation to prevent non-letter input and repeated incorrect guesses from affecting game state inappropriately.
*   **Case-Insensitivity:** Converted all input to uppercase for consistent comparisons, making the game case-insensitive.
*   **Clearer Output:**  Improved the display of game information, including the hidden word, incorrect guesses, and remaining attempts.
*   **Hangman Drawing:** Added a visual representation of the hangman to enhance the user experience.
*   **Modular Design:** Improved code organization by breaking the game logic into smaller, well-defined methods.
*   **Use of Constants:** Using constants (e.g., `MAX_TRIES`, `WORD_LIST_FILE`) to improve readability and maintainability.

### File Usage

*   **Input:**
    *   `words.txt`: This file contains the list of words that the game will randomly select from. Each word should be on a separate line.

*   **Output:**
    *   The game's output is displayed in the console, providing feedback to the player about their guesses, remaining attempts, and the current state of the hidden word.

### Additional Explanations

*   The game uses a `while` loop to continue playing until the player either guesses the word or runs out of attempts.
*   The `isWordGuessed()` method efficiently checks if the player has guessed the entire word.
*   The `getRandomWordListFile()` method (if you implemented multiple word list files) is responsible for randomly selecting the word list file to be used in the current game session.

This documentation provides a comprehensive overview of the Hangman game project.  It highlights the key design choices, algorithms, challenges, and improvements made during development.  It also explains the role of the external file (`words.txt`) and the overall structure of the code.
