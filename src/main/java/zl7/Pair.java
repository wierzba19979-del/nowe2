package zl7;

import java.util.function.Function;

public class Pair <L,R> {
    private final R right;
    private final L left;

    public Pair(R right, L left) {
        this.right = right;
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public L getLeft() {
        return left;
    }

    public <NR> Pair<NR,L> mapRight (Function<R,NR> mapper){
        return new Pair<>(left, mapper.apply(right));
    }

    public <NL> Pair<R,NL> mapLeft(Function<L, NL>mapper){
        return new Pair<>(mapper.apply(left), right);
    }
}
