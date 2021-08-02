import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class markCompact {
	
	  void start (String h,String p,String r, String o) throws IOException {
	    int id=0;
		String line = "";  
		String splitBy = ",";  
		LinkedList<element> l = new LinkedList<element>();
		try{  
			//parsing a CSV file into BufferedReader class constructor  
		   	BufferedReader br = new BufferedReader(new FileReader(h));  
		   	while ((line = br.readLine()) != null){   //returns a Boolean value  
		   
				String[] attr = line.split(splitBy);    // use comma as separator  
				element e =new element();
				e.name = attr[0].replaceFirst("﻿", "");
				e.start = Integer.parseInt(attr[1]);
				e.end = Integer.parseInt(attr[2]);
				e.id=id;
			    l.add(e);
			    id++;
		     	}  
	}   
	catch (IOException e)   
	{  
	e.printStackTrace();  
	} 
	
		boolean[] visited = new boolean[l.size()];
       graph g = new graph(l.size());
       
       
       try{
       BufferedReader br = new BufferedReader(new FileReader(p)); 
       	while ((line = br.readLine()) != null) {   //returns a Boolean value  
	  
	    	String[] attr = line.split(splitBy); 
	    	element first = search_name(attr[0].replaceFirst("﻿", ""),l);
	    	element second = search_name(attr[1],l);
	    	g.addEdge(first.id,second.id);
	    	
	    
	}
       	}
       catch (IOException e)   
	{  
	e.printStackTrace();  
	}
    try {
        BufferedReader br = new BufferedReader(new FileReader(r));  
		   	while ((line = br.readLine()) != null){
		   	    String root=line;
		   	    element e = search_name(root,l);
		   	    g.Mark(e.id,visited);
   
		   	}
		   	
    }
    catch(IOException e)   
	{  
	e.printStackTrace();  
	}   
    sweep(visited,l);
    compact(l);
    
    FileWriter csvWriter = new FileWriter(o);
    
    for(int i=0 ; i< l.size();i++) {   
    	element e=l.get(i);
    	csvWriter.append(e.name);
		csvWriter.append(",");
		csvWriter.append(Integer.toString(e.start));
		csvWriter.append(",");
		csvWriter.append(Integer.toString(e.end));
		csvWriter.append("\n");
    
    }
	csvWriter.flush();
	csvWriter.close();
    
       
   }
	  
	  
	  void compact(LinkedList<element> l){
		    int target=0;
		    for(int i=0;  i < l.size(); i++ ){
		    	if(l.get(i) != null) {
					   element e= new element();
				    	e.name=l.get(i).name;
				    	e.start=target;
				    	e.end=target + (l.get(i).end-l.get(i).start);
				    	target=e.end+1;
				    	l.set(i,e);
				   }
		    	else {
		    	
		    		 l.remove(i);
		    		 i--;
		    }}
		}


		void sweep(boolean[] visited, LinkedList<element> l){
		    for(int i=0;  i < visited.length; i++ ){
		    
		        if(visited[i] == false){
		            l.set(i, null);
		        }
		    } 
		}
	  
	  
	element search_name(String  name, LinkedList<element> l){
	    for(int i=0; i< l.size(); i++){
	        if(name.equals(l.get(i).name)){
	            return (element) l.get(i);
	        }
	    }
	  return null;  
	}
	
}