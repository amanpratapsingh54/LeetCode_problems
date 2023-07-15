package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class heightOfTree {
    static int sum=0;
    public static void main(String[] args) {
        binaryTree.Node root=new binaryTree.Node(1);
        root.left=new binaryTree.Node(2);
        root.right=new binaryTree.Node(3);
        root.left.left=new binaryTree.Node(4);
        root.left.right=new binaryTree.Node(5);
        root.right.left=new binaryTree.Node(6);
        root.right.right=new binaryTree.Node(7);
//        klevel(root);
//        int k=4;
//        int level=1;
//        klevelRecursion(root,level,k);
//        topView(root);
//        int n1=4,n2=7;

//        System.out.println(lowestCommonAncestor2(root,n1,n2).data);

//        System.out.println(lowestCommonAncestor(root,n1,n2));
//        System.out.println(minDistanceBetweenNodes(root,n1,n2));
            int k=2;
            int n=5;
//        kAncestor(root,n,k);
        System.out.println(sumTree(root).data);
        sumTree2(root);
//            System.out.println(kAncestor(root,n,k));
//        binaryTree.Node subRoot=new binaryTree.Node(2);
//        subRoot.left=new binaryTree.Node(4);
//        subRoot.right=new binaryTree.Node(5);

//        System.out.println(subTree(root,subRoot));
//        System.out.println(height(root));
//        System.out.println(countNode(root));
//        System.out.println(sumOfNodes(root));
//        System.out.println(diameter1(root));
//        System.out.println(diameter2(root).dia);

    }

    private static int sumTree2(binaryTree.Node root) {
        if(root==null)
        {
            return 0;
        }
        int leftchild=sumTree2(root.left);
        int rightchild=sumTree2(root.right);
        int data= root.data;
        int newLeft=root.left== null ? 0 : root.left.data;
        int newRight=root.right== null ? 0 : root.right.data;

        root.data=newLeft+leftchild+newRight+rightchild;
        return data;
    }

    private static binaryTree.Node sumTree(binaryTree.Node root) {
        if (root == null) {
            return null;
        }
        int l=sumNodes(root.left);
        int r=sumNodes(root.right);
        root.data=l+r;
        sumTree(root.left);
        sumTree(root.right);
        return root;
    }

    private static int sumNodes(binaryTree.Node root) {
        if (root == null) {
            return 0;
        }
        int l=sumOfNodes(root.left);
        int r=sumOfNodes(root.right);
        return l+r+root.data;
    }

    private static int kAncestor(binaryTree.Node root,int n, int k) {
        if(root==null)
        {
            return -1;
        }
        if(root.data==n)
        {
            return 0;

        }
        int l=kAncestor(root.left,n,k);
        int r=kAncestor(root.right,n,k);
        if(l==-1 && r==-1)
        {
            return -1;
        }
        int max=Math.max(l,r);
        if(max+1==k)
        {
            System.out.println(root.data);
        }
        return max+1;
    }

    private static binaryTree.Node lowestCommonAncestor2(binaryTree.Node root, int n1, int n2) {
        if(root==null || root.data==n1 || root.data==n2)
        {
            return root;
        }
        binaryTree.Node lNode=lowestCommonAncestor2(root.left,n1,n2);
        binaryTree.Node rNode=lowestCommonAncestor2(root.right,n1,n2);
        if(rNode==null)
        {
            return lNode;
        }
        if (lNode==null)
        {
            return rNode;
        }
        return root;

    }

    private static int  minDistanceBetweenNodes(binaryTree.Node root, int n1, int n2) {
        binaryTree.Node lca=lowestCommonAncestor2(root,n1,n2);
        int d1=dist(lca,n1);
        int d2=dist(lca,n2);
        return d1+d2;
    }

    private static int dist(binaryTree.Node root, int n) {

        if (root == null) {
            return -1;
        }
        if(root.data==n)
        {
            return 0;
        }
        int l=dist(root.left,n);
        int r=dist(root.right,n);
        if(l==-1 && r==-1)
        {
            return -1;
        }
        else if(l==-1)
        {
            return r+1;
        }
        else
        {
            return l+1;
        }
    }


    private static int lowestCommonAncestor(binaryTree.Node root, int n1, int n2) {
        ArrayList<binaryTree.Node> path1=new ArrayList<>();
        ArrayList<binaryTree.Node> path2=new ArrayList<>();
        getpath(root,n1,path1);
        getpath(root,n2,path2);
        int k=0;
        for(int i=0;i<path1.size() && i<path2.size();i++)
        {
            if(path1.get(i)==path2.get(i))
            {
                k=i;
            }
        }
        binaryTree.Node lca=path1.get(k);
        return lca.data;
    }
    private static boolean getpath(binaryTree.Node root, int n, ArrayList<binaryTree.Node> path) {
        if (root == null) {
            return false;
        }
        path.add(root);
        if (root.data == n) {
            return true;
        }
        boolean left = getpath(root.left, n, path);
        boolean right = getpath(root.right, n, path);
        if (left || right) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
    private static void klevelRecursion(binaryTree.Node root, int level, int k) {
        if(root==null)
        {
            return;
        }
        if(level==k)
        {
            System.out.print(root.data+" ");
            return;
        }
        klevelRecursion(root.left,level+1,k);
        klevelRecursion(root.right,level+1,k);
    }

    private static void klevel(binaryTree.Node root) {
        Queue<binaryTree.Node> q=new LinkedList<>();
        q.add(root);
        q.add(null);
        int count=1;
        int k=2;
        while (!q.isEmpty())
        {
            binaryTree.Node cNode=q.remove();
            if(cNode == null)
            {
                count++;
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
                if(count==k)
                {
                    System.out.print(cNode.data +" ");
                }
                if (cNode.left!=null)
                {
                    q.add(cNode.left);
                }
                if (cNode.right!=null)
                {
                    q.add(cNode.right);
                }
            }
//            count++;
        }
    }

    static class infoTopView{
        int hd;
        binaryTree.Node node;
        public infoTopView(binaryTree.Node node,int hd)
        {
            this.hd=hd;
            this.node=node;
        }
    }

    private static void topView(binaryTree.Node root) {
        Queue<infoTopView> q=new LinkedList<>();
        HashMap<Integer, binaryTree.Node> map=new HashMap<>();
        q.add(new infoTopView(root,0));
        q.add(null);
        int min=0;
        int max=0;
        while (!q.isEmpty())
        {
            infoTopView cNode=q.remove();
            if(cNode == null)
            {
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
                if(!map.containsKey(cNode.hd))
                {
                    map.put(cNode.hd,cNode.node);
                }
                if (cNode.node.left!=null)
                {
                    q.add(new infoTopView(cNode.node.left, cNode.hd-1));
                    min=Math.min(min,cNode.hd-1);
                }
                if (cNode.node.right!=null)
                {
                    q.add(new infoTopView(cNode.node.right, cNode.hd+1));
                    max=Math.max(max,cNode.hd+1);
                }
            }
        }
        for(int i=min;i<=max;i++)
        {
            System.out.print(map.get(i).data+ " ");
        }
        System.out.println();
    }

    private static boolean subTree(binaryTree.Node root, binaryTree.Node subRoot) {
        if( root==null)
        {
            return false;
        }
        if(root.data==subRoot.data)
        {
            if(isIdentical(root,subRoot))
            {
                return true;
            }
        }
        boolean left=subTree(root.left,subRoot);
        boolean right=subTree(root.right,subRoot);
        return left || right;
    }

    private static boolean isIdentical(binaryTree.Node root, binaryTree.Node subRoot) {
        if(root==null && subRoot ==null)
        {
            return true;
        }
        else if(root==null || subRoot==null || root.data!=subRoot.data)
        {
            return false;
        }
        if(!isIdentical(root.left,subRoot.left))
        {
            return false;
        }
        if(!isIdentical(root.right,subRoot.right))
        {
            return false;
        }
        return true;
    }

    static class info{
        int dia;
        int ht;
        public info(int dia,int ht)
        {
            this.dia=dia;
            this.ht=ht;
        }
    }
    private static info diameter2(binaryTree.Node root) {
        if(root==null)
        {
            return new info(0,0);
        }
        info leftInfo=diameter2(root.left);
        info righInfo=diameter2(root.right);

        int dia=Math.max(Math.max(leftInfo.dia,righInfo.dia),leftInfo.ht+righInfo.ht+1);
        int ht=Math.max(leftInfo.ht,righInfo.ht)+1;

        return new info(dia,ht);
    }


    //approach 1
    private static int diameter1(binaryTree.Node root) {
        if (root == null) {
            return 0;
        }
        int ldiameter=diameter1(root.left);
        int rdiameter=diameter1(root.right);
        int l=diameter1(root.left);
        int r=diameter1(root.right);
        int selfDiameter= l+r+1;
        return Math.max(selfDiameter,Math.max(rdiameter,ldiameter));
    }

    private static int sumOfNodes(binaryTree.Node root) {
        if (root == null) {
            return 0;
        }
        int l=sumOfNodes(root.left);
        int r=sumOfNodes(root.right);
        return l+r+root.data;

    }

    private static int countNode(binaryTree.Node root) {
        if (root == null) {
            return 0;
        }
        int l=countNode(root.left);
        int r=countNode(root.right);
        return l+r+1;

    }

    private static int height(binaryTree.Node root) {
        if (root == null) {
            return 0;
        }
        int l=height(root.left);
        int r=height(root.right);
        return Math.max(l,r)+1;
    }

}
