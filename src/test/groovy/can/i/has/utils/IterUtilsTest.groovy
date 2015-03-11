package can.i.has.utils


class IterUtilsTest extends GroovyTestCase {
    void testCombinationsIterator() {
        assertEquals(IterUtils.combinationsIterator(
                new OrderedMap<List>(
                    ["a", "b", "c", "d"],
                    [
                        a: [1, 2],
                        b: ["3", "4", "5"],
                        c: [6],
                        d: ["7", "8"]
                    ]
                )
            ).collect(), [
                new OrderedMap<>(["a", "b", "c", "d"], [a: 1, b: "3", c: 6, d: "7"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 1, b: "3", c: 6, d: "8"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 1, b: "4", c: 6, d: "7"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 1, b: "4", c: 6, d: "8"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 1, b: "5", c: 6, d: "7"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 1, b: "5", c: 6, d: "8"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 2, b: "3", c: 6, d: "7"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 2, b: "3", c: 6, d: "8"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 2, b: "4", c: 6, d: "7"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 2, b: "4", c: 6, d: "8"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 2, b: "5", c: 6, d: "7"]),
                new OrderedMap<>(["a", "b", "c", "d"], [a: 2, b: "5", c: 6, d: "8"])
            ])
    }
}
