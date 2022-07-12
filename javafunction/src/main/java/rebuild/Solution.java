package rebuild;

/**
 * @Author wangjiaxing
 * @Date 2022/4/28
 */
public class Solution {
    public static void main(String[] args) {
        reachNumber(5);
    }

    public static int reachNumber(int target) {
        if(target<0) {
            target = -target;
        }
        double sqrt = Math.sqrt(2 * target + 0.0 + 0.25);
        double v = sqrt - 0.5;
        int n = (int) v;
        int sum = 0;
        for (int i = 1; i < n+1; i++) {
            sum += i;
        }
        if(sum==target) {
            return n;
        }

        int sum2 = sum+n+1;
        if((sum2-target)%2==0) {
            return n+1;
        }else {
            return n + 2;
        }

    }
}
