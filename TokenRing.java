
import java.util.*;
import java.io.*;

class TokenRing{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of nodes:");
		int n=sc.nextInt();
		int token=0;	//Initially node 0 has token
		
		System.out.println("Ring Topology");
		for(int i=0;i<n;i++){
			System.out.print(i+" ");
		}
		System.out.println("0");
		
		while(true){
			System.out.println("Enter Sender");
			int sender=sc.nextInt();
		
			System.out.println("Enter Receiver");
			int receiver=sc.nextInt();
			
			System.out.println("Enter Data");
			int data=sc.nextInt();
			
			//Token moves to the sender
			System.out.println("Passing Token");
			for(int i=token;i!=sender;i=(i+1)%n){
				token=(i+1)%n;
				System.out.println(i+"->"+token);
			}
			System.out.println();
			
			//Now sender has the token
			System.out.println("Sender "+sender+" sending data "+data);
			
			for(int i=sender+1;i!=receiver;i=(i+1)%n){
				System.out.println("Data "+data+" forwarded by "+i);
			}
			
			System.out.println("Receiver "+receiver+" receives data "+data);
			
			token=sender;
			
			System.out.println("Do you want to continue? (0=NO 1=YES)");
			int ch=sc.nextInt();
			
			if(ch==0){
				break;
			}
		}
		
	}
}
