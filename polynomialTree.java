package polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

public class polynomialTree {
	String[] src;
	Node root;
	public class Node{
		String value;
		Node left;
		
		Node right;
		boolean isNum;
		public Node(){
			this(null,null,null,false);
		}
		public Node(String s,Node l,Node r,boolean f) {
			left = l;
			right = r;
			value = s;
			isNum = f;
		}
		public int toInt() {
			int tmp=0;
			for(int i=0;i<value.length();i++) {
				tmp=tmp*10+Character.getNumericValue(value.charAt(i));
			}
			return tmp;
		}
	}
	public polynomialTree(String a) {
		src = a.split(" ");
		
		String num = null;
		List<Node> table = new ArrayList<>();
		List<Node> opera = new ArrayList<>();
		for(int i=0;i<src.length;i++) {
			if(src[i].equals("/")||src[i].equals("*")||src[i].equals("+")||src[i].equals("-")) {
				opera.add(new Node(src[i],null,null,false));
			}else {
				table.add(new Node(src[i],null,null,true));
			}
		}
		
		while(opera.size()>0) {
			Node left = table.remove(0);
			Node right = table.remove(0);
			Node op = opera.remove(0);
			op.left= left;
			op.right = right;
			table.add(0, op);
		}
		root= table.remove(0);
	}
	
	public void middleS() {
		middleSearch(root);
	}
	
	private void middleSearch(Node x) {
		if(x == null) return;
		
		middleSearch(x.left);
		if(x.isNum)
			System.out.print(x.toInt());
		else
			System.out.print(x.value);
		middleSearch(x.right);
	}
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入表达式（以空格分割）:");
		String src = in.nextLine();
		new polynomialTree(src).middleS();
		
	}
}
