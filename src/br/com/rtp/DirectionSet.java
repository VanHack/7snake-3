package br.com.rtp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class DirectionSet implements Iterable<Direction> {

    private Set<Direction> set;

    public static DirectionSet ALL = new DirectionSet(new HashSet<>(Arrays.asList(Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT)));

    public DirectionSet(Set<Direction> set) {
        this.set = set;
    }

    public DirectionSet without(Direction direction) {
        return new DirectionSet(set.stream().filter(d -> d != direction).collect(Collectors.toSet()));
    }

    public Set<Direction> getSet() {
        return set;
    }

    @Override
    public Iterator<Direction> iterator() {
        return set.iterator();
    }
}
