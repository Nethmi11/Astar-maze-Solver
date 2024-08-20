// Name- N.N.H.Gamage/Nethmi Gamage
// UoW ID- w19561510
// IIT ID- 20221447

import java.util.*;

public class AStar {
    private final char [][] maze;
    private final Pointer startPointer;
    private final Pointer finishPointer;
    private final PriorityQueue<Node> openSet;
    private final Map<Pointer, Pointer> cameFrom= new HashMap<>();

    private final Map<Pointer,Node> allNodes=new HashMap<>();

    public AStar(char[][] maze, Pointer startPointer, Pointer finishPointer){
        this.maze=maze;
        this.startPointer=startPointer;
        this.finishPointer=finishPointer;
        initialiseNodes();
        this.openSet=new PriorityQueue<>();
        openSet.add(allNodes.get(startPointer));
    }

    private void initialiseNodes(){
        for(int y=0;y<maze.length;y++){
            for(int x=0;x<maze[y].length;x++){
                Pointer p=new Pointer(x,y);
                Node node=new Node(p,Integer.MAX_VALUE,Integer.MAX_VALUE);
                allNodes.put(p,node);
            }
        }

        Node startNode=allNodes.get(startPointer);
        startNode.gScore=0;
        startNode.fScore=heuristic(startPointer,finishPointer);
    }

    private int heuristic(Pointer a, Pointer b){
        return Math.abs(a.x-b.x)+ Math.abs(a.y-b.y);
    }

    public List<Pointer> findPath(){
        while(!openSet.isEmpty()){
            Node currentNode=openSet.poll();

            if(currentNode.vertex.equals(finishPointer)){
                return reconstructPath(currentNode.vertex);
            }

            for(Pointer neighbor: getNeighbor(currentNode.vertex)) {
                Node neighborNode = allNodes.get(neighbor);
                int tentativeGScore = currentNode.gScore + 1;

                if (tentativeGScore < neighborNode.gScore) {
                    neighborNode.gScore = tentativeGScore;
                    neighborNode.fScore = tentativeGScore + heuristic(neighbor, finishPointer);
                    openSet.add(neighborNode);
                    cameFrom.put(neighbor,currentNode.vertex);
                }
            }
        }

        return Collections.emptyList(); //no path found
    }

    private List<Pointer> reconstructPath(Pointer current){
        List<Pointer> path=new ArrayList<>();
        path.add(current);
        while(cameFrom.containsKey(current)){
            current= cameFrom.get(current);
            path.add(current);
        }

        Collections.reverse(path);
        return path;
    }

    private boolean isValid(int x,int y){
        return !(x < 0 || x >= maze[0].length || y < 0 || y >= maze.length || maze[y][x] == '0');
    }
    private List<Pointer> getNeighbor(Pointer current) {
        List<Pointer> neighbors = new ArrayList<>();
        int[][] moves = {
                {-1, 0}, // up
                {1, 0},  // down
                {0, -1}, // left
                {0, 1}   // right
        };

        for (int[] move : moves) {
            int newX = current.x + move[0];
            int newY = current.y + move[1];
            if (isValid(newX, newY)) {
                neighbors.add(new Pointer(newX, newY));
            }
        }
        return neighbors;
    }



    public void printPath(List<Pointer> path){
        for (int i = 0; i < path.size(); i++) {
            Pointer pointer = path.get(i);
            if (i == 0) {
                System.out.println((i + 1) + ". Start at (" + (pointer.x + 1) + "," + (pointer.y + 1) + ")");
            } else if (i == path.size() - 1) {
                System.out.println((i + 1) + ". Done!");
            } else {
                Pointer prev = path.get(i - 1);
                if (pointer.x < prev.x) {
                    System.out.println((i + 1) + ". Move left to (" + (pointer.x + 1) + "," + (pointer.y + 1) + ")");
                } else if (pointer.x > prev.x) {
                    System.out.println((i + 1) + ". Move right to (" + (pointer.x + 1) + "," + (pointer.y + 1) + ")");
                } else if (pointer.y < prev.y) {
                    System.out.println((i + 1) + ". Move up to (" + (pointer.x + 1) + "," + (pointer.y + 1) + ")");
                } else if (pointer.y > prev.y) {
                    System.out.println((i + 1) + ". Move down to (" + (pointer.x + 1) + "," + (pointer.y + 1) + ")");
                }
            }
        }
    }


}
