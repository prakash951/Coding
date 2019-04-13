package test;

import java.util.Stack;

public class Th {

	public static void main(String[] args) {
		int v = 9;
		int s = ++v + ++v + --v + v--;
		System.out.println(s);
		
		int a = 100;
		double b = 290.9;
		byte c = (byte)b;
		byte d = (byte)a;
		System.out.println(c+" "+d);
		
		B b1 = new B();
		b1.i = 22;
		b1.j = 33;
		b1.display();
		Stack st = new Stack();
		st.add("a");
		System.out.println(st.search("x"));

	}

}

class A
{
	public int i;
	protected int j;
}

class B extends A
{
	int j;
	public void display()
	{
		super.i=10;
		super.j=20;
		System.out.println(i + " " + j);
	}
}
