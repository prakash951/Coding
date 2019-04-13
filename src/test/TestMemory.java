package test;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class TestMemory {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		dox();
	}
	
	public static void dox() throws InterruptedException
	{
		List<int[]> al1 = new LinkedList<>();
		for(int i=0;i<10000;i++)
		{
			al1.add(new int[3]);
			Thread.sleep(10);
		}
		al1.clear();
		dox1();
		Thread.sleep(5000);
		dox2();
		Thread.sleep(5000);
	}

	
	public static void dox1() throws InterruptedException
	{
		List<int[]> al2 = new LinkedList<>();
		for(int i=0;i<10000;i++)
		{
			al2.add(new int[3]);
			Thread.sleep(10);
		}
		WeakReference<List<int[]>> wr = new WeakReference<>(al2);
		wr.clear();
	}
	
	public static void dox2() throws InterruptedException
	{
		List<int[]> al3 = new LinkedList<>();
		for(int i=0;i<10000;i++)
		{
			al3.add(new int[3]);
			Thread.sleep(10);
		}
		WeakReference<List<int[]>> wr = new WeakReference<>(al3);
		wr.clear();
	}
	
}
