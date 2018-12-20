public class Pair<S, T> {

    private final S first;
    private final T second;


    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S first(){
        return first;
    }

    public T second(){
        return second;
    }

    @Override
    public String toString(){
        return "(" + first + ", " + second + ")";
    }

}
