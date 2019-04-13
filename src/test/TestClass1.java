package test;

/* IMPORTANT: Multiple classes and nested static classes are supported */


import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

public class TestClass1 {
    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        ArrayList<Integer>[] adj = new ArrayList[n+1];
        ArrayList<Integer>[] cost = new ArrayList[n+1];
        for(int i=0;i<=n;i++)
        {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for(int i=0;i<m;i++)
        {
            int[] x = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            adj[x[0]].add(x[1]);
            adj[x[1]].add(x[0]);
            cost[x[0]].add(x[2]);
            cost[x[1]].add(x[2]);
        }
        int[] s = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(dfs(adj,cost,a,s[0],s[1],k));
    }
    
    public static long dfs(ArrayList<Integer>[] adj,ArrayList<Integer>[] cost,int[] a,int s,int d,int k)
    {
        boolean[] visited = new boolean[adj.length];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s,0));
        System.out.println(adj.length);
        long[] dist = new long[adj.length];
        int[] parent = new int[adj.length];
        int[] x1 = new int[adj.length];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[s]=0;
        parent[s]=0;
        Node node = null;
        boolean flag = false;
        while(!pq.isEmpty())
        {
            node = pq.poll();
            int s1 = node.x;
            //System.out.println(s1);
            if(s1==d){flag=true;break;}
            for(int i=0;i<adj[s1].size();i++)
            {
                int x = adj[s1].get(i);
                if(!visited[x])
                {
                    if(node.d + cost[s1].get(i) < dist[x])
                    {
                        dist[x] = node.d + cost[s1].get(i);
                        pq.add(new Node(x,dist[x]));
                        parent[x] = s1;
                        x1[x] = cost[s1].get(i);
                    }
                }
            }
            visited[s1]=true;
        }
        if(flag)
        {
            int x = d;
            while(parent[x]!=0)
            {
                if(x1[x]>k)
                    return -1;
                x = parent[x];
            }
            return node.d;
        }
        return -1;
    }
    
    static class Node implements Comparable<Node>
    {
        int x;
        long d;
        
        public Node(int x,long d)
        {
            this.x=x;
            this.d=d;
        }
        
        public int compareTo(Node n)
        {
            if(this.d > n.d)
            	return 1;
            else if(this.d < n.d)
            	return -1;
            return 0;
        }
        
    }
}
