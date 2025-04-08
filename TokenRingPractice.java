import java.io.*;
import java.util.*;

public class TokenRingPractice {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the number of nodes:");
        int n=sc.nextInt();

        for(int i=0;i<n;i++){
            System.out.print(i+"-");
        }
        System.out.print(0);
        System.out.println();

        int token=0;

        while(true){
            System.out.println("Enter the sender:");
            int sender=sc.nextInt();

            System.out.println("Enter the receiver:");
            int receiver=sc.nextInt();

            System.out.println("Enter the data:");
            int data=sc.nextInt();

            //Forwarding token to sender
            for(int i=token; i!=sender;i=(i+1)%n){
                System.out.println("Forwarding token from "+i+" to "+((i+1)%n));
            }

            token=sender;

            //Sender now has the token
            for(int i=sender;i!=receiver;i=(i+1)%n){
                System.out.println("Data "+data+" sent from "+i+" to "+((i+1)%n));
            }

            System.out.println("Do you want to continue?(YES=1/NO=0)");
            int choice=sc.nextInt();

            if(choice==0){
                break;
            }
        }
    }
}
