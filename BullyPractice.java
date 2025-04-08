import java.util.*;
import java.io.*;

public class BullyPractice {
    static int isActive[];  //Array keeping track of whether the process is active or not
    static int failedPr;    //Process ID that detects the failure of the current leader
    static int n;
    static int currentCoordinator;

    static int startElection(){
        for(int i=failedPr;i<=n && i!=currentCoordinator;i++){
                if(isActive[i]==1){
                    System.out.println("Node ID "+i+" sending messages to its higher node IDs.");
                    for(int j=i+1;j<=n;j++){
                    if(isActive[j]==1){
                        System.out.println("Message sent from "+i+" to "+j);
                    }
                }
            }
        }

        for(int i=n;i>=1;i--){
            if(isActive[i]==1){
                currentCoordinator=i;
                break;
            }
        }
        return currentCoordinator;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        n=sc.nextInt();

        isActive=new int[n+1];

        for(int i=1;i<=n;i++){
            isActive[i]=1;
        }

        currentCoordinator=n;
        System.out.println("Current coordinator is process with ID: "+n);

        while(true){
            System.out.println("Enter the process ID that detects failure of current coordinator:");
            failedPr=sc.nextInt();

            isActive[currentCoordinator]=0;   //Now this process becomes inactive

            //Now failedPr will start new election process
            currentCoordinator=startElection();
            System.out.println("Current coordinator is process with ID: "+currentCoordinator);

            System.out.println("Do you want to continue?(YES=1/NO=0)");
            int choice=sc.nextInt();

            if(choice==0){
                break;
            }
        }

    }

}
