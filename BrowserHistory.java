import java.util.ArrayList;
public class BrowserHistory {
    ArrayList<String> list = new ArrayList<>();
    int point = 0;
    public BrowserHistory(String homepage){
        list.add(homepage);
    }
    public void visit(String url){
        int n = list.size();
        if(point + 1 < n){
            while(point + 1 != n){
                list.remove(n - 1);
                n--;
            }
        }
        point += 1;
        list.add(url);
    }
    public String back(int steps){
        if(point - steps <= 0){
            point = 0;
            return list.get(0);
        }
        point -= steps;
        return list.get(point);
    }
    public String forward(int steps){
        if(steps + point >= list.size()){
            point = list.size() - 1;
            return list.get(point);
        }
        point += steps;
        return list.get(point);
    }
}
