package generics;

public class Pair<T, U> {

    protected T t;
    protected U u;

    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T getFirstElement() {
        return this.t;
    }

    public U getSecondElement() {
        return this.u;
    }

    public Pair<U, T> swap() {
        return new Pair<U, T>(this.u, this.t);
    }
}
