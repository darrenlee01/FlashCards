import java.util.*;
public class Pack {
    private List<Card> pack;
    
    public Pack(List<Card> pack) {
        this.pack = pack;
    }
    
    public void add(Card card) {
        pack.add(card);
    }
    
    public Card get(int index) {
       return pack.get(index);
    }
    
    public void set(int index, Card card) {
        pack.set(index, card);
    }
    
    public int size() {
        return pack.size();
    }
    
    public void removeOverlap() {
        
        for (int i = 0; i < pack.size(); i++) {
            
            String compareCard = pack.get(i).getFront();
            
            for (int j = i + 1; j < pack.size(); j++) {
                if (compareCard.equals(pack.get(j).getFront())){
                    pack.remove(j);
                    j--;
                }
            }
        }
        
        
    }
    
    public void randomize() {
        int[] indexes = new int[pack.size()];
        List<Integer> usedNum = new ArrayList<Integer>();
        for (int i = 0; i < pack.size(); i++) {
            int rand = (int) (Math.random() * (pack.size()));
            while (usedNum.contains(rand)) {
                rand = (int) (Math.random() * (pack.size()));
            }
            indexes[i] = rand;
            usedNum.add(rand);
        }
        List<Card> temp = new ArrayList<Card>();
        for (int i = 0; i < pack.size(); i++) {
            temp.add(pack.get(indexes[i]));
        }
        
        for (int i = 0; i < pack.size(); i++) {
            pack.set(i, temp.get(i));
        }
    }
    
    public String toString() {
        String ret = "{";
        for (int i = 0; i < pack.size(); i++) {
            ret = ret + "[" + pack.get(i).getFront() + ": " 
                + pack.get(i).getBack() + "]";
            if (i != pack.size() - 1) {
                ret = ret + ", ";
            }
        }
        ret = ret + "}";
        return ret;
    }
            
}