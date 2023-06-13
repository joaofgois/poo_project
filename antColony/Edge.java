package antColony;

import java.util.Objects;

public class Edge<T> {

    protected T v1;
    protected T v2;

    public Edge(T v1, T v2){
        this.v1 = v1;
        this.v2 = v2;
    }

	@Override
	public int hashCode() {
		return Objects.hash(10);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge<T> other = (Edge<T>) obj;
		return (Objects.equals(v1, other.v1) && Objects.equals(v2, other.v2) || Objects.equals(v1, other.v2) && Objects.equals(v1, other.v2));
	}

    

}
