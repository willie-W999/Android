public class Solution extends GuessGame {
    public Solution(int target) {
        super(target);
    }
    public int guessNumber(int n){
        var start = 0;
        var end = n;
        while(start <= end){
            var mid = start + (end - start) / 2;
            var ans = guess(mid);
            if(ans == 0){
                return ans;
            }else if(ans > 0){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
}
