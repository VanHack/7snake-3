package br.com.rtp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CoordSet implements Iterable<Coord> {

    private Set<Coord> set;

    public CoordSet(Coord coord) {
        this(new HashSet(Arrays.asList(coord)));
    }

    public CoordSet(Set<Coord> set) {
        this.set = set;
    }

    public CoordSet add(Coord coord) {
        Set<Coord> newSet = new HashSet(set);
        newSet.add(coord);
        return new CoordSet(newSet);
    }

    public boolean contains(Coord coord) {
        return set.contains(coord);
    }

    public Set<Coord> getSet() {
        return set;
    }

    public boolean intersect(CoordSet other) {
        return set.stream().anyMatch(coord -> other.contains(coord));
    }

    @Override
    public Iterator<Coord> iterator() {
        return set.iterator();
    }

    @Override
    public String toString() {
        List<Coord> coords = new ArrayList<Coord>(set);
        Collections.sort(coords);
        return String.join(", ", coords.stream().map(c -> c.toString()).collect(Collectors.toList()));
    }
}
