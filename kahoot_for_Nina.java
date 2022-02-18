import java.util.*;

public class kahoot_for_Nina {

    String[] words = new String[] { "Dog", "Cat", "Bird", "Green", "Blue", "Red" };

    String[] definitions = new String[] { "🐕", "🐈", "🐦", "💚", "💙", "🟥" };

    // {"have a ball", "have a ball", "has a ball", "has a ball", "has a ball",
    // "have a ball", "have a ball", "have a ball"};
    private void makeEmptyLines(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println(" ");
        }
    }

    private void setUpArrays() {
        int switchIndex;
        String tempString = "";
        for (int i = words.length - 1; i > 0; i--) {
            switchIndex = (int) (Math.random() * i);

            tempString = words[i];
            words[i] = words[switchIndex];
            words[switchIndex] = tempString;

            tempString = definitions[i];
            definitions[i] = definitions[switchIndex];
            definitions[switchIndex] = tempString;
        }
    }

    public void makeQuestions(int numOptions, int numQuestions) {

        Scanner sc = new Scanner(System.in); // Scanner
        String word = ""; // The word the person will be quizzed on
        String rightDef = ""; // The right definition
        boolean rightDefAlreadyChosen = false; // If the right definition is randomly chosen by the algorithm, this
                                               // becomes true
        char rightLetter = ' '; // Stores the right letter the user needs to input
        int rightOptionIndex; // Stores the position of the right option
        String[] options = new String[numOptions]; // Constains the options for the question
        char choice;
        String userChoice = "";
        String[] definitionsCopy;
        int score = 0;
        int numQuestionsStore = numQuestions;
        int definitionsCopyIndex;

        do {
            setUpArrays();
            definitionsCopy = definitions.clone();
            for (int j = 0; j < words.length && numQuestions > 0; j++) {

                makeEmptyLines(30);

                word = words[j]; // The loop has the right word be every word in the words[] array at some point

                rightDef = definitions[j]; // Since definitons is unshuffled here, a specific index in the words[] and
                                           // definitions[] array are corresponding

                // Assigns definitions to the options[] array, which are random since
                // definitions[] has been shuffled
                for (int i = 0; i < numOptions; i++) {
                    definitionsCopyIndex = (int) (Math.random() * definitionsCopy.length);
                    options[i] = definitionsCopy[definitionsCopyIndex];
                    definitionsCopy[definitionsCopyIndex] = "0";
                    if (options[i].equals("0"))
                        i--;
                    if (rightDefAlreadyChosen == false && options[i].equals(rightDef)) { // If the loop choses the right
                                                                                         // definition by chance, the
                                                                                         // option
                        // it has been assigned to will be recorded
                        rightDefAlreadyChosen = true;
                        rightLetter = (char) (97 + i);
                    }
                }

                // If the rught definition wasnt chosen by chance, one of the options will be
                // overwritten with the right definition and be recorded
                if (rightDefAlreadyChosen == false) {
                    rightOptionIndex = (int) (Math.random() * options.length);
                    options[rightOptionIndex] = rightDef;
                    rightLetter = (char) (97 + rightOptionIndex);
                }

                choice = 'a';
                System.out.println(word + "\n");
                for (int i = 0; i < options.length; i++) {
                    System.out.println(choice + ". " + options[i]);
                    choice++;
                }

                System.out.print("\nEnter your choice: ");
                userChoice = sc.nextLine();
                System.out.println(" ");

                if (userChoice.length() != 1) {
                    System.out.println("Sorry, the correct answer is " + rightLetter);
                } else if (userChoice.charAt(0) == rightLetter || userChoice.charAt(0) == rightLetter - 33) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Sorry, the correct answer is: " + rightLetter + ". " + rightDef);
                }
                System.out.print("Enter anything to continue: ");
                sc.nextLine();
                numQuestions--;
                rightDefAlreadyChosen = false;
                definitionsCopy = definitions.clone();
            }
        } while (numQuestions > 0);
        makeEmptyLines(30);
        System.out.println("Your score is " + score + "/" + numQuestionsStore);
    }

    public static void main(String[] args) {

        kahoot_for_Nina k = new kahoot_for_Nina();
        k.makeQuestions(3, 8);
    }
}
