import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Hier mag je je code scrijven voor de hoofd-opdracht
        // Numeric omzetten naar Alphabetic

        //for loop bij class Translator baseert zich op lengte intlist - dus alplist kan ik gemakkelijk uitbreiden zonder problemen. AlpList uitgebreid voor Poortwachter bonus.

        Integer[] intList = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] alpList = {"een", "twee", "drie", "vier", "vijf", "zes", "zeven", "acht", "negen", "tien", "elf", "twaalf", "dertien", "veertien", "vijftien", "zestien", "zeventien", "achttien", "negentien", "twintig"};
        boolean playGame = true;
        Scanner myScanner = new Scanner(System.in);
        Translator input = new Translator(intList, alpList);

        // Play game translator
        int inputNum = 0;
        while (playGame) {
            System.out.println("Wil je vertalen van cijfers naar alfabetische nummers? Kies voor J (= ja) of N (= nee)");
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


        //MASTERMIND bonus opdracht #1-4
        //HashSet is niet nodig voor Mastermind, omdat de getallen niet uniek hoeven te zijn. En het geeft alle waardes gesorteerd terug - dat willen we niet.
        int[] secretNumberList = randomNumberGenerator();

        String stringNumber = setToStringConverter(secretNumberList);

        System.out.println("Mastermind code: " + stringNumber); // deze moet uitgecomment worden als je het mastermind spel echt wilt spelen - anders weet je het antwoord al.
        feedback(stringNumber);

        //Bonus Poortwachter:
        checkAnswer(alpList, myScanner);


    }


    //Verder met MASTERMIND
    // Moet 4 unieke random nummers returnen - waarvan de nummers allemaal 9 of lager zijn.


    //  Niet met HashSet gewerkt - omdat dat eigenlijk niet logisch is, want daar worden nummers gesorteerd. Daarom ArrayList en array methode gemaakt - ga nu gebruik maken van array.
    public static int[] randomNumberGenerator() {
        Random rand = new Random();
//        HashSet<Integer> set = new HashSet<>(); direct een array gemaakt, zodat de nummers niet gesorteerd worden door HashSet
        int[] secretNumberList = new int[4];
        for (int i = 0; i < 4; ) {
            int randNum = rand.nextInt(0, 10); //origin included, bound excluded

            boolean unique = true;
            for (int j = 0; j < i; j++) {
                if (randNum == secretNumberList[j]) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                secretNumberList[i] = randNum;
                i++;
            }

        }

        return secretNumberList;
    }


    public static String setToStringConverter(int[] secretNumberList) {


        String stringNumber = "";

        for (int i = 0; i < secretNumberList.length; i++) {
            stringNumber = stringNumber + (secretNumberList[i]);
        }
        return stringNumber;

    }

    //
//    /*
//     Deze methode is voor de bonus opdracht.
//     */
//
    public static void feedback(String stringNumber) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder feedback = new StringBuilder();
        System.out.println("Kies een getal van 4 unieke cijfers om de mastermind code te raden.");
        boolean continueTrying = true;
        while (continueTrying) {
            String guess = scanner.nextLine();
            if (guess.length() != 4) {
                System.out.println("Je antwoord moet bestaan uit 4 cijfers, niet meer, niet minder. \n Probeer nogmaals: ");
                continue;
            } else if (Objects.equals(guess, stringNumber)) {
                System.out.println("Gefeliciteerd je hebt het goed geraden");
                continueTrying = false;
                break;
            } else {
                for (int i = 0; i < 4; i++) {
                    if (guess.substring(i, i + 1).equals(stringNumber.substring(i, i + 1))) {
                        feedback.append("+");
                    } else if (stringNumber.contains(guess.substring(i, i + 1))) {
                        feedback.append("0");
                    } else {
                        feedback.append("X");
                    }
                }
                System.out.println(feedback.toString());
                System.out.println("Helaas, verkeerd geraden. \n + betekent dat het cijfer juist is en op de juiste plek staat. \n O betekent een juist cijfer, maar verkeerde plek. \n X betekent dat het cijfer er niet in zit. \nProbeer nogmaals:");
                feedback = new StringBuilder();
                continue;

            }
        }
    }

// randomnumbergenerator gemaakt met een ArrayList: (die gebruik ik niet, hierboven maak ik gebruik van array)
//    public static ArrayList<Integer> randomNumberGenerator() {
//        Random rand = new Random();
////        HashSet<Integer> set = new HashSet<>(); direct een array gemaakt, zodat de nummers niet gesorteerd worden door HashSet
//        ArrayList<Integer> secretNumberList = new ArrayList<>();
//        while (secretNumberList.size() < 4) {
//            for (int i = 0; i < 4; i++) {
//                int randNum = rand.nextInt(0, 10); //origin included, bound excluded
//                if (secretNumberList.size() < 1) {
//                    secretNumberList.add(randNum);
//                }
//                else {
//                    for (Integer j : secretNumberList) {
//                        if (secretNumberList.contains(randNum)) {
//                            continue;
//                        } else {
//                            secretNumberList.add(randNum);
//                            break;
//                        }
//                    }
//                }
//
//            }
//        }
//            System.out.println("List: " + secretNumberList);
//        return secretNumberList;
//    }


// einde bonus MASTERMIND

// Bonus Poortwachter (extra opdracht van Paul):
//Er is een poort en daarbij een poortwachter als je hierdoorheen wilt dan zegt de poortwachter een getal en jij moet een getal terug zeggen.
//        # als jouw antwoord correct is mag je erdoorheen. Paul wilt door de poort de poortwachter zegt acht. paul antwoord met 4 en mag doorlopen.
//        # Bas wil ook door de poort de poortwachter zegt twaalf en bas zegt 6.
//        # jij wilt er ook doorheen de poortwachter zegt elf. wat moet je antwoorden?
//        # jouw antwoord zou moeten zijn 3 want elf bestaat uit 3 letters.

    public static void checkAnswer(String[] alpList, Scanner myScanner) {
        Random rand = new Random();
        int indexList = rand.nextInt(0, alpList.length);

        System.out.println("Hoi, welkom bij de Poortwachter. \nOm de poort in te kunnen moet je het goede antwoord geven. Ik zeg: " + alpList[indexList] + ".\nWat is jouw antwoord? Het moet bestaan uit een getal.");
        boolean askForAnswer = true;
        int answerNum;

        while (askForAnswer) {
            try {
                answerNum = myScanner.nextInt(); //vangt de input op, pakt alle opeenvolgende ints tot er geen int meer is (dus bijv. een spatie / .  , / of letter)
                myScanner.nextLine(); //evt resterende string / spatie / getallen / . / ,s die overzijn - vangt ie met deze scanner op, zodat die niet nog blijft hangen
                if (answerNum == alpList[indexList].length()) {
                    System.out.println("Gefeliciteerd, je hebt het goed geraden! Je mag door.");
                    askForAnswer = false;
                    break;
                } else {
                    System.out.println("Dat antwoord klopt niet. Probeer nogmaals: ");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Dat is geen juiste invoer, kies een getal. Probeer nogmaals: ");
                myScanner.nextLine();// moet weer opnieuw scanner line doen - die vangt dan de verkeerde waarde op, anders blijft ie 'try' doen met de oude waarde.
            }
        }

    }
}



