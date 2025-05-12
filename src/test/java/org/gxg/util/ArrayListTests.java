package org.gxg.util;

import org.gxg.collection.FixedCapacityStackOfStrings;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ArrayListTests {
    /**
     * Returns an empty {@link FixedCapacityStackOfStrings}.
     *
     * @return an empty stack
     */
    public <E> ArrayList<E> createObj() {
        return new ArrayList<>();
    }

    @Test
    void getWhenListIsEmpty() {
        ArrayList<Integer> list = createObj();
        assertNull(list.get(0));
        assertNull(list.get(3));
    }

    @Test
    void usingAdd() {
        ArrayList<Integer> list = createObj();
        list.add(1);
        list.add(2);
        list.add(0, 3);
        list.add(list.size(), 4);
        list.add(2, 5);

        assertEquals(3, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(2, list.get(3));
        assertEquals(4, list.get(4));
    }

}
