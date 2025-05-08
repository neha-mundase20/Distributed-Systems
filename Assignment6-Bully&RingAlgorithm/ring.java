import java.util.*;
import java.io.*;

public class ring{

	static int failureID;
	static int isActive[];
	static int currentCoordinator;
	static int n;
	
	static void startElection(){
		System.out.println("Process ID "+failureID+" has started the election.");
		
		String message=""+failureID;
		
		//Processes add their IDs in the election message and forward messages to next processes
		for(int i=failureID+1;i!=failureID;i=(i+1)%n){
			
			if(isActive[i]==1){
				message=message+"->"+i;
				System.out.println(message);
			}
		}
		
		int max=-1;
		
		for(int i=n;i>=1;i--){
			if(i>=max && isActive[i]==1){
				currentCoordinator=i;
				break;
			}
		}
		
		System.out.println("New Coordinator is: "+currentCoordinator);
	}
	
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		n=sc.nextInt();
		int ch=1;
		
		isActive=new int[n+1];
		
		for(int i=1;i<=n;i++){
			isActive[i]=1;	//Initially all are set as active
		}
		
		currentCoordinator=n;
		System.out.println("Current Coordinator is: "+currentCoordinator);
		
		while(ch==1){
			System.out.println("Enter the process ID that detected failure of current coordinator:");
			failureID=sc.nextInt();
			
			isActive[currentCoordinator]=0;
			startElection();
			
			System.out.println("Do you want to continue?YES=1/NO=0");
			ch=sc.nextInt();	
		}
		
	}
}
