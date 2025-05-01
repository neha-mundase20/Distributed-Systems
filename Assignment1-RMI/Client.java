import java.rmi.*;

import java.util.*;

public class Client{
	public static void main(String args[]){
		try{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the Server Address:");
			String address=sc.nextLine();
			ServerInterface si = (ServerInterface) Naming.lookup("rmi://"+address+"/Server");
			System.out.println("Enter the first string");
			String s1=sc.nextLine();
			System.out.println("Enter the second string");
			String s2=sc.nextLine();
			System.out.println("Concatenated String : " + si.concat(s1, s2));
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
