import java.io.IOException;

public class tester {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if(args.length >= 4) {
		String heap = args[0];
		String roots = args[1];
		String pointers = args[2];
		String copy = args[3];
		
		
       markCompact c =new markCompact();
       c.start(heap,pointers,roots,copy);}
	else {
		System.out.println("you must enter 4 arguments");
		return;
	}
}
}
