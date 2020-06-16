import java.io.*;
import java.util.*;
public class FlashCards {
    public static void printCard(boolean isFront, String front, String back) {
        if (isFront) {
            System.out.println(front);
        } else {
            System.out.println(back);
        }
    }
       
    public static void createSpace() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
    
    public static boolean inputCheck(ArrayList<String> acceptable) {
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner (System.in);
        System.out.println("Choose the pack of cards you would like to test");
        System.out.println("A: new SAT/ACT words");
        System.out.println("B: all SAT/ACT words");
        System.out.println("C: Spanish words");
        String file = input.nextLine();
        BufferedReader f;
        if (file.equals("A")) {
          f = new BufferedReader(new FileReader("sat.in"));
          System.out.println();
          System.out.println("You have chosen the 'New SAT/ACT words' list");
          System.out.println();
        } else if(file.equals("B")) {
          f = new BufferedReader(new FileReader("allSat.in"));
          System.out.println();
          System.out.println("You have chosen the 'All SAT/ACT words' list");
          System.out.println();
        } else {
        	f = new BufferedReader(new FileReader("spanish.in"));
          System.out.println();
          System.out.println("You have chosen the 'Spanish words' list");
          System.out.println();
        }
        StringTokenizer st = new StringTokenizer(f.readLine());
        int packNum = 100;
        
        Pack[] packs = new Pack[packNum];
        for (int i = 0; i < packNum; i++) {
            List<Card> temp = new ArrayList<Card>();
            Pack a = new Pack(temp);
            packs[i] = a;
        }
        
        String nextCard = st.nextToken();
        while (!nextCard.equals("END")) {
            String front = st.nextToken();
            while(front.indexOf(":") == -1) {
              front += " " + st.nextToken();
            }
            front = front.substring(0, front.length() - 1);
            String back = "";
            while (st.hasMoreTokens()) {
                back = back + st.nextToken() + " ";
            }
            back = back.substring(0, back.length() - 1);
            Card temp = new Card(front, back);
            packs[0].add(temp);
            st = new StringTokenizer(f.readLine());
            nextCard = st.nextToken();
        }
        int cardNum = packs[0].size();
        //System.out.println(Arrays.toString(packs));
        
        
        boolean isFront = true;
        System.out.println("Press 'F' to test with the front side");
        System.out.println("Press 'B' to test with the back side");
        if (input.nextLine().equals("B")) {
            isFront = false;
        }
        
        
        System.out.println("Press enter to flip");
        System.out.println("Press any key and enter to go to the next card");
        System.out.println("Press 'b' to go previous card");
        System.out.println("Press 'r' to restart the current pack");
        System.out.println("Press 'k' to mark that you got that word incorrect");
        System.out.println("You can only input letters once definition is shown.");
        System.out.println("Press enter to see definition");
        System.out.println();
        
        
        for (int i = 0; i < packNum; i++) {
            
            if (packs[i].size() <= 0) {
                packNum = i;
                break;
            }
            
            createSpace();
            System.out.println("Pack " + (i+1) + " -> " + packs[i].size() + " words");
            System.out.println();
            packs[i].removeOverlap();
            packs[i].randomize();
            System.out.println("Ready?");
            String start = input.nextLine();
            //System.out.println(Arrays.toString(packs));
           
            for (int j = 0; j < packs[i].size(); j++) {
                   
                // checks if current list has any words to review
                // if next list has no lists 
                
                String front = packs[i].get(j).getFront();
                String back = packs[i].get(j).getBack();
                // sets front and back to current card's respective parts
                
                boolean cardDone = false;
                while (!cardDone) {
                    //REMOVE
                    //createSpace();
                    createSpace();
                    //separating previous card with current
                    printCard(isFront, front, back);
                    //print first wanted face
                    System.out.println();
                    String flip = input.nextLine();
                    // useless input for flipping
                    System.out.println();
                    
                    printCard(!isFront, front, back);
                    // print answer face
                    String inputChar = input.nextLine();
                    
                    if (inputChar.equalsIgnoreCase("b")) {
                        j = j - 2;
                        cardDone = true;
                        System.out.println("BACK");
                    } else if (inputChar.equalsIgnoreCase("r")) {
                        j = -1;
                        cardDone = true;
                        System.out.println("RESTART");
                    } else if (inputChar.equalsIgnoreCase("k")) {
                        if (i < packNum - 1) {
                            Card addNew = new Card(front, back);
                            packs[i + 1].add(addNew);
                        }
                        System.out.println("INCORRECT");
                    } else if (!inputChar.equals("")) {
                        cardDone = true;
                        System.out.println("NEXT");
                    } else {
                        System.out.println("FLIP");
                    }
                }
            }
        }
                
        System.out.println();
        System.out.println("Going back up the waterfall!");
        System.out.println("Displays lists in reverse order");
        System.out.println("Must get entire list correct in order to move on");
        String a = input.nextLine();
        System.out.println();
        
        for (int i = packNum - 1; i >= 0; i--) {
            boolean hadIncorrect = false;           
            createSpace();
            System.out.println("Pack " + (i+1) + " -> " + packs[i].size() + " words");
            System.out.println();
            packs[i].removeOverlap();
            packs[i].randomize();
            System.out.println("Ready?");
            String start = input.nextLine();
            
            for (int j = 0; j < packs[i].size(); j++) {
                
                String front = packs[i].get(j).getFront();
                String back = packs[i].get(j).getBack();
                // sets front and back to current card's respective parts
                
                boolean cardDone = false;
                while (!cardDone) {
                    //createSpace();
                    createSpace();
                    //separating previous card with current
                    printCard(isFront, front, back);
                    //print first wanted face
                    System.out.println();
                    String flip = input.nextLine();
                    // useless input for flipping
                    System.out.println();
                    
                    printCard(!isFront, front, back);
                    // print answer face
                    String inputChar = input.nextLine();
                    
                    if (inputChar.equals("b")) {
                        j = j - 2;
                        cardDone = true;
                        System.out.println("BACK");
                    } else if (inputChar.equals("r")) {
                        j = -1;
                        cardDone = true;
                        System.out.println("RESTART");
                    } else if (inputChar.equals("k")) {
                        hadIncorrect = true;
                        System.out.println("INCORRECT");
                    } else if (!inputChar.equals("")) {
                        cardDone = true;
                        System.out.println("NEXT");
                    } else {
                        System.out.println("FLIP");
                    }
                }
            }
            
            if (hadIncorrect) {
                i++;
                System.out.println("Must redo pack!");
                String z = input.nextLine();
            }
        }
        createSpace();
        System.out.println();
        System.out.println();
        System.out.println("DONE!!!");
        System.out.println();
    }
}