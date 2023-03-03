import java.util.HashMap;

public class Translator {

    HashMap<Integer, String> numAlpMap = new HashMap<>();

    public Translator(Integer[] intList, String[] alpList) {
        for (int i = 0; i < intList.length; i++) {
            numAlpMap.put(intList[i], alpList[i]);
        }

    }

    public static String translateToAlpNum(Integer inputNum, HashMap numAlpMap) {


        String alpNum = numAlpMap.get(inputNum).toString(); //toString nodig, omdat ie anders een Object teruggeeft en geen String. Waarom weten we niet precies, accepteren dat .toString toegevoegd moet worden.

        return alpNum;


    }
}
