public class Card {
    private String front;
    private String back;
    
    public Card(String front, String back) {
        this.front = front;
        this.back = back;
    }
    
    public void setFront(String newFront) {
        front = newFront;
    }
    
    public void setBack(String newBack) {
        back = newBack;
    }
    
    public String toString() {
        return front + ": " + back;
    }
    
    public String getFront() {
        return front;
    }
    
    public String getBack() {
        return back;
    }
    
}
    
    