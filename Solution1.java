public class Solution1 extends VersionControl {
    
    public int firstBadVersion(int n) {
        var start = 0;
        var end = n;
        while(start < end){
            var mid = start + (end - start) / 2;
            if(isBadVersion(mid)){
                end = mid;
            }else{
                start = mid + 1;
            }
        }  
        return start;
    }
}
