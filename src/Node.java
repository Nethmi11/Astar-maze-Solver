// Name- N.N.H.Gamage/Nethmi Gamage
// UoW ID- w19561510
// IIT ID- 20221447

import java.util.Objects;

public class Node implements Comparable<Node>{
    Pointer vertex;
    int gScore;
    int fScore;

    public Node(Pointer vertex, int gScore, int fScore){
        this.vertex=vertex;
        this.gScore=gScore;
        this.fScore=fScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return gScore == node.gScore && fScore == node.fScore && Objects.equals(vertex, node.vertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, gScore, fScore);
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.fScore, other.fScore);
    }

}
