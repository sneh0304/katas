package kata;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Kata {
	static LinkedHashMap<String, User> user = new LinkedHashMap<String, User>();
    public static String someKataMethod() {
        return "hello, world!";
    }
    public static void main(String[] args) {
    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    	try {
	    	System.out.println("Enter number of Users: ");
	    	int n = Integer.parseInt(input.readLine());
	    	
	    	while (n > 0) {
	    		System.out.println("Enter name of User: ");
	    		String name = input.readLine();
	    		user.put(name, new User(name));
	    		n--;
	    	}
	    	while (true) {
	    		System.out.println("Enter your name: ");
	    		String name = input.readLine();
	    		System.out.println("Enter Operation (0 to publish message, 1 to view timeline, 2 to follow, 3 to view wall): ");
	    		int op = Integer.parseInt(input.readLine());
	    		if (op == 0) {
	    			System.out.println("Enter message: ");
		    		String msg = input.readLine();
		    		user.get(name).publishMessage(msg);
	    		} else if (op == 1) {
	    			System.out.println("Enter name of the person whose timeline to view: ");
		    		String usr = input.readLine();
		    		if (usr.toLowerCase().equals("own"))
		    			usr = name;
		    		System.out.println(usr);
		    		user.get(name).viewTimeline(user.get(usr));
	    		} else if (op == 2) {
	    			System.out.println("Whom do you want to follow: ");
		    		String usr = input.readLine();
		    		user.get(name).follow(user.get(usr));
	    		} else if (op == 3) {
		    		user.get(name).viewWall();
	    		}
	    		System.out.println("Want to continue? (y/n)");
	    		String s = input.readLine();
	    		if (s.toLowerCase().equals("n"))
	    			break;
	    	}
    	} catch (Exception e) {
    		System.out.println("Enter a valid input!");
    		e.printStackTrace();
    	}
    }
}
