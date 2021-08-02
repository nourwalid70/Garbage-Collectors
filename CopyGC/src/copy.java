import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class copy {
	
	 void start (String h,String p,String r, String o) throws IOException {
		    int id=0;
			String line = "";  
			String splitBy = ",";  
			LinkedList<element> l = new LinkedList<element>();
			try{  
				//parsing a CSV file into BufferedReader class constructor  
			   	@SuppressWarnings("resource")
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
		
	       graph g = new graph(l.size());
	         
	       try{
	       @SuppressWarnings("resource")
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
		  LinkedList<element> l2 = new LinkedList<element>();
		int target=0;
	    try {
	        @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(r));  
			   	while ((line = br.readLine()) != null){
			   	    String root=line;
			   	    element e = search_name(root,l);
			   	        int temp = e.start;
				    	e.start=target;
				    	e.end=target + (e.end-temp);
				    	target=e.end+1;
			   	        l2.add(e);
			   	}
			   	
	    }
	    catch(IOException e)   
		{  
		e.printStackTrace();  
		}   
	
		int start=0;
		int end=l2.size();
		int i=0;
	while(start != end){
		    LinkedList<Integer> children = g.adj[l2.get(i).id];
		   
		    for(int j=0; j< children.size();j++){
		        element e= search_ID(children.get(j),l);
		        if(!l2.contains(e)) 
		        {
		        	int temp = e.start;
		            e.start=target;
		            e.end=target + (e.end-temp);
		    	    target=e.end+1;
		            l2.add(e);
		           end++;
		        }
		    }
		    
		    start++;
		    i++;
		}
	l.clear();
	    FileWriter csvWriter = new FileWriter(o);    
	    for(i=0 ; i< l2.size();i++) {   
	    	element e=l2.get(i);
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
	 element search_name(String  name, LinkedList<element> l){
		    for(int i=0; i< l.size(); i++){
		        if(name.equals(l.get(i).name)){
		            return (element) l.get(i);
		        }
		    }
		  return null;  
		}

element search_ID(int  id, LinkedList<element> l){
	    for(int i=0; i< l.size(); i++){
	        if(id==l.get(i).id){
	            return (element)l.get(i);
	        }
	    }
	  return null;  
	}
}