public class Node {
    private int id;
    private boolean isAlive;
    private boolean isCoordinator;

    public Node(int id, boolean isAlive, boolean isCoordinator){
        this.id = id;
        this.isAlive = isAlive;
        this.isCoordinator = isCoordinator;
    }

    public void nodeFailure(){
        isAlive=false;
        isCoordinator=false;
    }

    public void makeCoordinator(){
        isCoordinator=true;
    }

    static int getMaxAliveID(int n, Node[] node){
        int max=node[0].id;
        int coordinator=0;
        for(int i=1;i<n;i++){
            if(node[i].id>max && node[i].isAlive==true){
                max=node[i].id;
                coordinator=i;
            }
        }
        return coordinator;
    }

    static int startElection(int n, Node[] node, int processID){
        System.out.println("Process " + processID + " starts election...");
        int newCoordinator = processID;

        for(int i=0;i<n;i++){
            if(node[i].id>processID && node[i].isAlive==true){
                System.out.println("Election message sent to Process " + node[i].id);
                if(node[i].id>newCoordinator){
                    newCoordinator=i;
                }
            }
        }
        node[newCoordinator].makeCoordinator();
        return newCoordinator;
    }
}
