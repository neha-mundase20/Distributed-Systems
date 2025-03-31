import java.io.*;
import java.util.*;

class Bully{

    public static void main(String[] args) {

        // Node n1=new Node(1,true,false);
        // Node n2=new Node(2,true,false);
        // Node n3=new Node(3,true,false);
        // Node n=new Node(4,true,true);

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of nodes:");
        int n=sc.nextInt();

        Node[] node = new Node[n];

        for(int  i=0;i<n;i++){
            System.out.println("Enter the priority of node: "+i);
            int priority=sc.nextInt();
            node[i]=new Node(priority,true,false);
        }
        
        //Initially highest process ID is the coordinator and will start the election
        int coordinator = Node.getMaxAliveID(n,node);
        node[coordinator].makeCoordinator();
        System.out.println("Coordinator is: "+coordinator);

        while(true){
            System.out.println("1. Start Election");
            System.out.println("2. Exit");
            int ch=sc.nextInt();

            if(ch==1){
                System.out.println("Enter the process ID that detects failure:");
                node[coordinator].nodeFailure();
                int id=sc.nextInt();
                //Now this 'id' process will start the election
                int newCoordinator=Node.startElection(n,node,id);
                System.out.println("New coordinator is: "+newCoordinator);
            }
            else{
                break;
            }
        }
    }
}