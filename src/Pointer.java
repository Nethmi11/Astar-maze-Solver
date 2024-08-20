// Name- N.N.H.Gamage/Nethmi Gamage
// UoW ID- w19561510
// IIT ID- 20221447

import java.util.Objects;

public class Pointer {
    int x,y;

    Pointer(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pointer point = (Pointer) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
