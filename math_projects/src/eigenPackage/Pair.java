package eigenPackage;

public class Pair<S,T> {
    private final S first;
    private final T second;

    protected Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
}
