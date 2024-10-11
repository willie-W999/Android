public class GN extends GuessGame{
    public GN(int target){
        super(target);
    }
    public int guessNumber(int n){
        var left = 0;
        var right = n;
        while(left <= right){
            var mid = left + (right - left) / 2;
            var res = guess(mid);
            if(res == 0){
                return mid;
            }else if(res == -1){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
