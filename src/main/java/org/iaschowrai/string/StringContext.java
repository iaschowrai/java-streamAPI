package org.iaschowrai.string;
/*
Step-by-Step Internal Execution
Step 1: String Literal "Java" is created

When the JVM loads the class and sees "Java" in your code, it first checks a special memory area called
the String Constant Pool (SCP), which is inside the heap but managed separately by the JVM.
If "Java" is not already in the pool, JVM creates a single object for it.
If it exists already, JVM reuses it. String Pool:   "Java"  (1 object)

Step 2: new String("Java") is executed
When you write new String("Java"), something special happens:
The JVM looks at "Java" literal and fetches it from the String pool (created in Step 1).
Then it creates a new String object in the heap memory, which is a copy of that pooled string.
So s1 points to a new heap object (not the pooled one).
Memory visualization:
String Pool:   "Java"          ← (pooled literal)
Heap:          [s1] → "Java"   ← (new String object)

Step 3: Second line executes again
String s2 = new String("Java");
Again, JVM checks the pool — "Java" already exists, so it reuses the same pooled literal.
Then it creates another new heap object with the same content "Java".
Now you have two separate heap objects, both with the same value.
Memory visualization:
String Pool:   "Java"
Heap:
   [s1] → "Java"
   [s2] → "Java"

 */
public class StringContext {
    public static void main(String[] args) {
        primitiveComparison();
        stringLiteralComparison();
        newStringComparison();
        stringInternExample();
        wrapperClassComparison();
        customObjectWithoutEquals();
        customObjectWithEquals();
        nullComparison();
        mixedTypeComparison();
        checkIntComparison();

    }

    // ----------------------------------------------------------
    // 1️⃣ Primitive Comparison
    // ----------------------------------------------------------
    static void primitiveComparison() {
        System.out.println("==== Primitive Comparison ====");

        int a = 10;
        int b = 10;

        System.out.println("Definition: '==' compares primitive values directly.");
        System.out.println("a == b -> " + (a == b)); // true
        System.out.println();
    }

    // ----------------------------------------------------------
    // 2️⃣ String Literal Comparison (String Pool)
    // ----------------------------------------------------------
    static void stringLiteralComparison() {
        System.out.println("==== String Literal Comparison ====");

        String s1 = "Java";
        String s2 = "Java";

        System.out.println("Definition: String literals are stored in String Constant Pool.");
        System.out.println("== compares memory references; equals() compares content.");
        System.out.println("s1 == s2 -> " + (s1 == s2));         // true
        System.out.println("s1.equals(s2) -> " + s1.equals(s2)); // true
        System.out.println();
    }

    // ----------------------------------------------------------
    // 3️⃣ new String() Comparison (Heap Objects)
    // ----------------------------------------------------------
    static void newStringComparison() {
        System.out.println("==== new String() Comparison ====");

        String s1 = new String("Java");
        String s2 = new String("Java");

        System.out.println("Definition: new String() creates objects in heap separately.");
        System.out.println("s1 == s2 -> " + (s1 == s2));         // false
        System.out.println("s1.equals(s2) -> " + s1.equals(s2)); // true
        System.out.println();
    }

    // ----------------------------------------------------------
    // 4️⃣ intern() Example
    // ----------------------------------------------------------
    static void stringInternExample() {
        System.out.println("==== String Intern Example ====");

        String s1 = new String("Hello");
        String s2 = "Hello";

        System.out.println("Definition: intern() returns the pooled string reference.");
        System.out.println("s1 == s2 -> " + (s1 == s2));              // false
        System.out.println("s1.intern() == s2 -> " + (s1.intern() == s2)); // true
        System.out.println("s1.equals(s2) -> " + s1.equals(s2));
        System.out.println();
    }

    // ----------------------------------------------------------
    // 5️⃣ Wrapper Class Comparison (Integer Caching)
    // ----------------------------------------------------------
    static void wrapperClassComparison() {
        System.out.println("==== Wrapper Class Comparison ====");

        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;

        System.out.println("Definition: Wrapper classes cache values between -128 and 127.");
        System.out.println("i1 == i2 (100, cached) -> " + (i1 == i2)); // true
        System.out.println("i3 == i4 (200, not cached) -> " + (i3 == i4)); // false
        System.out.println("i3.equals(i4) -> " + i3.equals(i4)); // true
        System.out.println();
    }

    // ----------------------------------------------------------
    // 6️⃣ Custom Object Without equals() Override
    // ----------------------------------------------------------
    static void customObjectWithoutEquals() {
        System.out.println("==== Custom Object Without equals() ====");

        class Person {
            String name;
            Person(String name) { this.name = name; }
        }

        Person p1 = new Person("Alice");
        Person p2 = new Person("Alice");

        System.out.println("Definition: Default equals() in Object compares references.");
        System.out.println("p1 == p2 -> " + (p1 == p2));         // false
        System.out.println("p1.equals(p2) -> " + p1.equals(p2)); // false
        System.out.println();
    }

    // ----------------------------------------------------------
    // 7️⃣ Custom Object With equals() Overridden
    // ----------------------------------------------------------
    static void customObjectWithEquals() {
        System.out.println("==== Custom Object With equals() ====");

        class Person {
            String name;
            Person(String name) { this.name = name; }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (!(obj instanceof Person)) return false;
                Person p = (Person) obj;
                return this.name.equals(p.name);
            }
        }

        Person p1 = new Person("Bob");
        Person p2 = new Person("Bob");

        System.out.println("Definition: Overriding equals() lets us compare by content.");
        System.out.println("p1 == p2 -> " + (p1 == p2));         // false
        System.out.println("p1.equals(p2) -> " + p1.equals(p2)); // true
        System.out.println();
    }

    // ----------------------------------------------------------
    // 8️⃣ Null Comparison
    // ----------------------------------------------------------
    static void nullComparison() {
        System.out.println("==== Null Comparison ====");

        String s1 = null;
        String s2 = null;

        System.out.println("Definition: == works safely with null, but equals() throws NPE.");
        System.out.println("s1 == s2 -> " + (s1 == s2)); // true

        System.out.println("Using Objects.equals(s1, s2): " + java.util.Objects.equals(s1, s2)); // safe true
        // System.out.println(s1.equals(s2)); // would throw NullPointerException
        System.out.println();
    }

    // ----------------------------------------------------------
    // 9️⃣ Mixed Type Comparison
    // ----------------------------------------------------------
    static void mixedTypeComparison() {
        System.out.println("==== Mixed Type Comparison ====");

        String s = "123";
        Integer i = 123;

        System.out.println("Definition: equals() returns false for different object types.");
        System.out.println("s.equals(i) -> " + s.equals(i)); // false
        System.out.println();
    }

    static void checkIntComparison() {

        System.out.println("==== int and Integer Comparison ====");

        // Primitive ints
        int a = 127;
        int b = 127;
        int c = -128;
        int d = 128;

        // Wrapper Integers
        Integer aa = 127;   // cached
        Integer bb = 128;   // not cached
        Integer cc = -128;  // cached
        Integer dd = 128;   // not cached

        System.out.println("=== Primitive int comparisons ===");
        System.out.println("a == b : " + (a == b)); // true, 127 == 127
        System.out.println("a == c : " + (a == c)); // false, 127 != -128
        System.out.println("d == 128 : " + (d == 128)); // true, primitive value comparison

        System.out.println("\n=== Integer wrapper comparisons using == ===");
        System.out.println("aa == 127 : " + (aa == 127)); // true, auto-unboxing
        System.out.println("aa == bb : " + (aa == bb));   // false, 127 cached vs 128 not cached
        System.out.println("bb == dd : " + (bb == dd));   // false, both 128 but different objects
        System.out.println("aa == cc : " + (aa == cc));   // false, 127 != -128
        System.out.println("cc == -128 : " + (cc == -128)); // true, auto-unboxing

        System.out.println("\n=== Integer wrapper comparisons using .equals() ===");
        System.out.println("aa.equals(bb) : " + aa.equals(bb)); // false, 127 != 128
        System.out.println("bb.equals(dd) : " + bb.equals(dd)); // true, value equality
        System.out.println("aa.equals(cc) : " + aa.equals(cc)); // false, 127 != -128
        System.out.println("cc.equals(-128) : " + cc.equals(-128)); // true, value equality
    }

}
