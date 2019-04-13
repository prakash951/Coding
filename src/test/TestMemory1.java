package test;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class TestMemory1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		dox();
	}

	public static void dox() throws InterruptedException {
		List<int[]> al1 = new LinkedList<>();
		for (int i = 0; i < 10000; i++) {
			al1.add(new int[3]);
			Thread.sleep(5);
		}
		Thread t1 = new Thread(new Thread1());
		Thread t2 = new Thread(new Thread2());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

	static class Thread2 implements Runnable {
		public static void dox1() throws InterruptedException {
			List<int[]> al2 = new LinkedList<>();
			for (int i = 0; i < 10000; i++) {
				al2.add(new int[3]);
				Thread.sleep(5);
			}
			WeakReference<List<int[]>> wr = new WeakReference<>(al2);
			wr.clear();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				dox1();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		protected void finalize() throws Throwable {
			// TODO Auto-generated method stub
			super.finalize();
			System.out.println("From Thread2");
		}
	}

	static class Thread1 implements Runnable {
		public void dox2() throws InterruptedException {
			List<int[]> al3 = new LinkedList<>();
			for (int i = 0; i < 10000; i++) {
				al3.add(new int[3]);
				Thread.sleep(5);
			}
			WeakReference<List<int[]>> wr = new WeakReference<>(al3);
			wr.clear();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				dox2();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		protected void finalize() throws Throwable {
			// TODO Auto-generated method stub
			super.finalize();
			System.out.println("From Thread1");
		}
	}

}
