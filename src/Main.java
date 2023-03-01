import java.util.HashMap;
import java.util.Random;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Hier mag je je code scrijven voor de hoofd-opdracht


        Integer[] intList = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] alpList = {"een", "twee", "drie", "vier", "vijf", "zes", "zeven", "acht", "negen"};
        boolean playGame = true;
        Scanner myScanner = new Scanner(System.in);
        Translator input = new Translator(intList, alpList);

        int inputNum = 0;
        while (playGame) {
            System.out.println("Wil je spelen? Typ in J of N");
            String playAgain = myScanner.nextLine();
            if (playAgain.charAt(0) == ('N') || playAgain.charAt(0) == ('n')) {
                System.out.println("Dankjewel voor het spelen");
                playGame = false;
                break;
            } else if (playAgain.charAt(0) == ('J') || playAgain.charAt(0) == ('j')) {
                System.out.println("Kies een nummer tussen de 0 en 9:");
                boolean askForInput = true;
                while (askForInput) {
                    try {
                        inputNum = myScanner.nextInt(); //vangt de input op, pakt alle opeenvolgende ints tot er geen int meer is (dus bijv. een spatie / .  , / of letter)
                        myScanner.nextLine(); //evt resterende string / spatie / getallen / . / ,s die overzijn - vangt ie met deze scanner op, zodat die niet nog blijft hangen
                        if (inputNum >= 0 && inputNum <= 9) {
                            System.out.println("Je hebt nummer " + Translator.translateToAlpNum(inputNum, input.numAlpMap) + " gekozen.");
                            askForInput = false;
                            break;
                        } else {
                            System.out.println("Dat is geen valide nummer, kies een nummer tussen 0 en 9.");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Dat is geen getal, kies een nummer tussen 0 en 9.");
                        myScanner.nextLine();// moet weer opnieuw scanner line doen - die vangt dan de verkeerde waarde op, anders blijft ie 'try' doen met de oude waarde.
                    }
                }
            } else {
                System.out.println("Dit is geen geldige keuze.");
            }
        }
    }


//MASTERMIND
        Random rand = new Random();

        HashSet<Integer> secretNumber = randomNumberGenerator(rand, secretNumber);
        String stringNumber =  setToStringConverter(secretNumber);
//
//        System.out.println(stringNumber);
//        feedback();



        public static HashSet<Integer> randomNumberGenerator(Random rand, HashSet<Integer> secretNumber){

            while (secretNumber.size() <4) {
                int randNum = rand.nextInt(0, 9);

                secretNumber.add(randNum);
            }

            return secretNumber;

        }

        public static String setToStringConverter(HashSet<Integer> secretNumber){

            String[] secretNumerArray = secretNumber.toArray(new String[secretNumber.size()]);

            for (int i = 0; i < secretNumber.size(); i++){
                String stringNumber = "";
                System.out.println(stringNumber);
                stringNumber = stringNumber + (secretNumerArray[i].toString());
                System.out.println(stringNumber);
            }

        }



    /*
     Deze methode is voor de bonus opdracht.
     */
    public static void feedback(String stringnumber) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder feedback = new StringBuilder();
        System.out.println("take a guess");
        String guess = scanner.nextLine();
        if (Objects.equals(guess, stringnumber)) {
            System.out.println("gefeliciteerd je hebt het goed");
        } else {
            for (int i = 0; i < 4; i++) {
                if (guess.substring(i, i + 1).equals(stringnumber.substring(i, i + 1))) {
                    feedback.append("+");
                } else if (stringnumber.contains(guess.substring(i, i + 1))) {
                    feedback.append("0");
                } else {
                    feedback.append("X");
                }
            }
        }
        System.out.println(feedback.toString());
    }
}

