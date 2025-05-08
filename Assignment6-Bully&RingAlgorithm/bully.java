import java.util.*;
import java.io.*;

public class bully{

	static int failureID;
	static int isActive[];
	static int currentCoordinator;
	static int n;
	
	static void startElection(){
		System.out.println("Process ID "+failureID+" has started the election.");
		
		//Processes send election message to their higher IDs
		for(int i=failureID;i<=n && isActive[i]==1;i++){
		System.out.println("------------------------------------------------------");
			for(int j=i+1;j<=n && isActive[j]==1;j++){
				System.out.println("Election message sent from "+i+" -> "+j);
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
