package test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class DimmestStar
{
    public static void main(String[] args)throws Exception
    {
        Reader r = new Reader();
        int t = r.nextInt();
        for(int t1=0;t1<t;t1++)
        {
            int n = r.nextInt();
            ArrayList<Integer>[] adj = new ArrayList[n+1];
            for(int i=1;i<=n;i++)
            {
                adj[i] = new ArrayList<Integer>();
            }
            for(int i=0;i<n-1;i++)
            {
                int x = r.nextInt();
                int y = r.nextInt();
                adj[x].add(y);
            }
            int s = r.nextInt();
            System.out.println(bfs(adj,s));
        }
    }
    
    static int bfs(ArrayList<Integer>[] adj,int s)
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            min = Integer.MAX_VALUE;
            while(size>0)
            {
                int x = queue.poll();
                min = Math.min(x,min);
                for(int y:adj[x])
                {
                    queue.add(y);
                }
                size = size - 1;
            }
        }
        return min;
    }
    static class Reader 
    {
    	final private int BUFFER_SIZE = 1 << 16;
    	private DataInputStream din;
    	private byte[] buffer;
    	private int bufferPointer, bytesRead;
    
    	public Reader() {
    		din = new DataInputStream(System.in);
    		buffer = new byte[BUFFER_SIZE];
    		bufferPointer = bytesRead = 0;
    	}
    
    	public Reader(String file_name) throws IOException {
    		din = new DataInputStream(new FileInputStream(file_name));
    		buffer = new byte[BUFFER_SIZE];
    		bufferPointer = bytesRead = 0;
    	}
    
    	public String readLine() throws IOException {
    		byte[] buf = new byte[64]; // line length
    		int cnt = 0, c;
    		while ((c = read()) != -1) {
    			if (c == '\n')
    				break;
    			buf[cnt++] = (byte) c;
    		}
    		return new String(buf, 0, cnt);
    	}
    
    	public int nextInt() throws IOException {
    		int ret = 0;
    		byte c = read();
    		while (c <= ' ')
    			c = read();
    		boolean neg = (c == '-');
    		if (neg)
    			c = read();
    		do {
    			ret = ret * 10 + c - '0';
    		} while ((c = read()) >= '0' && c <= '9');
    
    		if (neg)
    			return -ret;
    		return ret;
    	}
    
    	public long nextLong() throws IOException {
    		long ret = 0;
    		byte c = read();
    		while (c <= ' ')
    			c = read();
    		boolean neg = (c == '-');
    		if (neg)
    			c = read();
    		do {
    			ret = ret * 10 + c - '0';
    		} while ((c = read()) >= '0' && c <= '9');
    		if (neg)
    			return -ret;
    		return ret;
    	}
    
    	public double nextDouble() throws IOException {
    		double ret = 0, div = 1;
    		byte c = read();
    		while (c <= ' ')
    			c = read();
    		boolean neg = (c == '-');
    		if (neg)
    			c = read();
    
    		do {
    			ret = ret * 10 + c - '0';
    		} while ((c = read()) >= '0' && c <= '9');
    
    		if (c == '.') {
    			while ((c = read()) >= '0' && c <= '9') {
    				ret += (c - '0') / (div *= 10);
    			}
    		}
    
    		if (neg)
    			return -ret;
    		return ret;
    	}
    
    	private void fillBuffer() throws IOException {
    		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
    		if (bytesRead == -1)
    			buffer[0] = -1;
    	}
    
    	private byte read() throws IOException {
    		if (bufferPointer == bytesRead)
    			fillBuffer();
    		return buffer[bufferPointer++];
    	}
    
    	public void close() throws IOException {
    		if (din == null)
    			return;
    		din.close();
    	}
    }

}