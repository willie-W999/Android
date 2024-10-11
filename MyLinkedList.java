import java.util.ArrayList;
public class MyLinkedList {
    ArrayList<Integer> list;
    public MyLinkedList(){
        list = new ArrayList<Integer>();
    }
    public int get(int index){
        if(!(index >= 0 && index < list.size()))return -1;
        return list.get(index);
    }
    public void addAtHead(int val){
        list.add(0, val);
    }
    public void addAtTail(int val){
        list.add(list.size(), val);
    }
    public void addAtIndex(int index, int val){
        if(index <= list.size()){
            list.add(index, val);
        }
    }
    public void deleteAtIndex(int index){
        if(index < list.size()){
            list.remove(index);
        }
    }
    public static void main(String[] args) {
        
    }
}
