import java.io.*;
import java.util.*;

public class TokenRing{
	public static void main(String[] args){
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter the number of nodes:");
			int n=sc.nextInt();
			
			System.out.println("The Ring Topology is:");
			for(int i=0;i<n;i++){
				System.out.print(i+"->");
			}
			System.out.print("0");
			System.out.println();
			
			int token=0;
			int ch=1;
			
			while(ch==1){
				System.out.println("Enter the sender:");
				int sender=sc.nextInt();
				
				System.out.println("Enter the receiver:");
				int receiver=sc.nextInt();
				
				System.out.println("Enter the data to be sent:");
				int data=sc.nextInt();
				
				
				//Sender doesn't have token so transferring token to sender
				if(token!=sender){
					System.out.println("Token Passing:");
					for(int i=token;i!=sender;i=(i+1)%n){
						System.out.print(i+"->");
					}
					System.out.print(sender);
					token=sender;
					System.out.println();
				}
				
				System.out.println("Current token holder:"+token);
				
				//Start sending the data from sender to receiver
				for(int i=sender;i!=receiver;i=(i+1)%n){
					System.out.println("Sending data "+data+" from "+i+"->"+(i+1)%n);
				}
				
				System.out.println("Do you  want to continue?(YES=1/NO=0)");
				ch=sc.nextInt();	
			}		
	}
}
