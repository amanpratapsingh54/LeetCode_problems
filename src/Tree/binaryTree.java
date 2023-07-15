package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class binaryTree {

    static int idx=-1;
    static class Node
    {
        int data;
        Node left;
        Node right;

        Node(int data)
        {
           this.data=data;
           this.left=null;
           this.right=null;
        }
    }
    public static void main(String[] args) {
        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        Node root=binaryTree(nodes);
//        preOrder(root);
//        postOrder(root);
//        inOrder(root);
        levelOrder(root);
//        System.out.println();
//        System.out.println(root.data);

    }

    private static void levelOrder(Node root) {


        Queue<Node> q=new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty())
        {
            Node cNode=q.remove();
            if(cNode == null)
            {

                System.out.println();
                if (q.isEmpty())
                {
                    break;
                }
                else
                {
                    q.add(null);
                }

            }
            else
            {
                System.out.print(cNode.data +" ");
                if (cNode.left!=null)
                {
                    q.add(cNode.left);
                }
                if (cNode.right!=null)
                {
                    q.add(cNode.right);
                }

            }
        }

    }

    private static void inOrder(Node root) {
        if(root==null)
        {
            System.out.print(-1+" ");
            return ;
        }
        postOrder(root.left);
        System.out.print(root.data+" ");
        postOrder(root.right);

    }

    private static void postOrder(Node root) {
        if(root==null)
        {
            System.out.print(-1+" ");
            return ;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }

    private static void preOrder(Node root) {
        if(root==null)
        {
            System.out.print(-1+" ");
            return ;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }


    private static Node binaryTree(int[] nodes) {
        idx++;
        if(nodes[idx]==-1)
        {
            return null;
        }
        Node newNode=new Node(nodes[idx]);
        newNode.left=binaryTree(nodes);
        newNode.right=binaryTree(nodes);
        return newNode;

    }
}
