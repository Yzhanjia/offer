package leetcode;

public class MaxProfit {
    public static void main(String[] args) {
        int[]num = {1,2,4};
        System.out.println(maxProfit(num));
    }
//    public static int maxProfit(int[] prices) {
//        if (prices.length<2){//一个或零个，无利润
//            return 0;
//        }
//        int max = 0,min = 0 ,i = 0,tmp = 0;
//        int profit = max - min;
//        while (profit==0){//若利润为0，证明从未赋值过，进入循环
//            if (i<prices.length-1){
//                if (prices[i]<prices[i+1]){//寻找到第一顺序对，即后一天价格大于前一天，利润不为零
//                    max = prices[i+1];
//                    min = prices[i];
//                    tmp =min;//另一最小值
//                    profit = max - min;
//                    i ++;//进入下一个
//                }else {
//                    i++;//跳过
//                }
//            }
//
//            if (i==prices.length-1){
//                return profit;//已经达到数组最后一个
//            }
//        }
//
//        while (i<prices.length){
//
//                if (prices[i]>max){
//                    max = prices[i];//替换最大值与利润
//                    profit = max -min;
//                }else if (prices[i]<tmp){
//                    tmp = prices[i];//小于最小值，记录该值，若后面存在价格差值大于利润才替换利润，最大值与最小值
//                }
//
//
//                if (prices[i]-tmp>profit){//差值大于原先利润，替换
//                    max = prices[i];
//                    min = tmp;
//                    profit = prices[i] - tmp;
//                }
//                i++;//进入下一位置
//            }
//
//
//        return profit;
//
//
//    }
    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(prices[i], min);//找出在前面的最小值
            max = Math.max(prices[i] - min, max);//比较当前利润与原先利润，找出更大的利润
        }
        return max;
    }
}
