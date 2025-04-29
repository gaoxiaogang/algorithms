package org.gxg.collection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FixedCapacityStackOfStringsTests {
    /**
     * Returns an empty {@link FixedCapacityStackOfStrings}.
     *
     * @return an empty stack
     */
    public FixedCapacityStackOfStrings createObj() {
        return new FixedCapacityStackOfStrings(5);
    }

    @Disabled
    void popWhenEmptyIsNull() {
        FixedCapacityStackOfStrings collection = createObj();
        assertNull(collection.pop());
    }

    @Test
    void pushWhenStackIsFull() {
        FixedCapacityStackOfStrings collection = createObj();
        collection.push("a");
        collection.push("b");
        collection.push("c");
        collection.push("d");
        collection.push("e");
        ArrayIndexOutOfBoundsException exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            collection.push("f"); // 调用可能抛异常的方法
        });
        // 验证异常消息内容
        assertEquals("capacity is full", exception.getMessage());
    }
}
