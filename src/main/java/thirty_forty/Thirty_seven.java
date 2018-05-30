package thirty_forty;

import java.util.LinkedList;
import java.util.List;

public class Thirty_seven {
    public static void serialize(BinaryTreeNode root,List<Integer> result){
        List<BinaryTreeNode> list = new LinkedList<>();
        list.add(root);
        BinaryTreeNode node ;
        while (list.size()>0){
            node = list.remove(0);
            if (node==null){
                result.add(null);
            }else {
                result.add(node.value);
                list.add(node.left);
                list.add(node.right);
            }

        }
    }

    public static BinaryTreeNode deserialize(List<Integer>result,int index){
        if (result.size()<1||index<0||result.size()<=index||result.get(index)==null){
            return null;
        }else {
            BinaryTreeNode root = new BinaryTreeNode();
            root.value = result.get(index);
            root.left = deserialize(result,index*2+1);
            root.right = deserialize(result,index*2+2);
            return root;
        }
    }
}
