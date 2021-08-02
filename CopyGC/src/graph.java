import java.util.Iterator;
import java.util.LinkedList;

public class graph {
	
	    private int V; 
	 
	    public LinkedList<Integer> adj[];
	 
	    // Constructor
	    @SuppressWarnings("unchecked")
	    graph(int v){
	        V = v;
	        adj = new LinkedList[v];
	        for (int i = 0; i < v; ++i)
	            adj[i] = new LinkedList();
	    }
	 
	    void addEdge(int v, int w)
	    {
	        adj[v].add(w); 
	    }	

}