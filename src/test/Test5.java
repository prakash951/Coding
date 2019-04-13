package test;

public class Test5 {
	
static int i;

public static void main(String[] args) {

	Object d;
	d=10;
	System.out.println(d.getClass().getName());
	d="A";
	System.out.println(d.getClass().getName());
	d=true;
	System.out.println(d.getClass().getName());
	int a=2;
	int b=2;
	for(int i=0;i<5;i++)
	{
		if((++a>2)&&(b++>2))a++;
	}
	System.out.println(a+" "+b);
}



static class A1
{
	
}
static class B1 extends A1
{
	
}

}
