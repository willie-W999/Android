import java.util.Queue;
import java.util.LinkedList;
public class MyStack {
    Queue<Integer> q;
    public MyStack(){
        this.q = new LinkedList<>();
    }
    public void push(int x){
        q.add(x);
        for(int i = 1;i < q.size();i++){
            q.add(q.remove());
        }
    }
    public int pop(){
        return q.remove();
    }
    public int top(){
        return q.peek();
    }
    public boolean empty(){
        return q.isEmpty();
    }
}
