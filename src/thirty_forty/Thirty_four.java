package thirty_forty;

import java.util.ArrayList;
import java.util.List;

public class Thirty_four {
    public static void findPath(BinaryTreeNode pRoot,int expectedSum){
        if (pRoot==null) return;
        List<Integer> list = new ArrayList<>();
        findPath(pRoot,0,expectedSum,list);
    }

    private static void findPath(BinaryTreeNode root, int curSum, int expectedSum, List<Integer> result) {
        if (root!=null){
            curSum+=root.value;
            result.add(root.value);
            if (curSum<expectedSum){
                findPath(root.left,curSum,expectedSum,result);
                findPath(root.right,curSum,expectedSum,result);
            }else if (curSum==expectedSum){
                if (root.left ==null&&root.right ==null){
                    System.out.println(result);
                }
                result.remove(result.size()-1);
            }

        }

    }

}
