import java.util.Stack;
public class MinStack {
    Stack<Integer> stack;
    int min;
    public MinStack(){
        this.stack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }
    public void push(int val){
        if(val <= min){
            stack.push(min);
            min = val;
        }
    }
    public void pop(){
        if(stack.pop() == min){
            min = stack.pop();
        }
    }
    public int top(){
        return stack.peek();
    }
    public int getMin(){
        return min;
    }
    public static void main(String[] args) {
        
    }
}
