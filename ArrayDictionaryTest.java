package src;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDictionaryTest {
    @Test
    public void demo() {
        int testSize = 5;
        ArrayDictionary dict = new ArrayDictionary(testSize);
        assertTrue(dict.add(2, 82));
        assertTrue(dict.add(4, 84));
        assertTrue(dict.add(7, 87));
        System.out.println(dict);
    }

    @Test
    /*Test method for remove function within ArrayDictionary. Includes all given test cases assigned (comment by each one with
     * explination of which it is). For each case I created a dictionary with the needed sizes, and added given elements. I then
     * wrote an assert statement calling remove on what is needed which returns true or false respectively. I wrote asserTrue
     * when the expected result needs to be true, and assertFalse when the expected is false. If one doesnt match the program will
     * crash.
     */
    public void remove() {
        ArrayDictionary dict1 = new ArrayDictionary(10);
        assertFalse(dict1.remove(7));//empty dictionary
        
        ArrayDictionary dict2 = new ArrayDictionary(2);
        dict2.add(0, 103);
        assertTrue(dict2.remove(0));//key exists no collision
        assertFalse(dict2.remove(1));//key does not exist, no collision
        
        ArrayDictionary dict3 = new ArrayDictionary(2);
        dict3.add(0,103);
        dict3.add(2,105);
        dict3.add(4, 108);
        dict3.add(6, 211);
        dict3.add(1, 105);
        assertTrue(dict3.remove(4));//key exists within collision
        assertTrue(dict3.remove(1));//key exists but does not collide
        assertFalse(dict3.remove(3));//key does not exist, has collision
    }

    @Test
    /*Exactly the same as test method above except it is calling the contains function in the file above. Read Block comment in
     * above method.
     */
    public void contains() {
        ArrayDictionary dict1 = new ArrayDictionary(10);
        assertFalse(dict1.contains(-1));
        assertFalse(dict1.contains(0));
        assertFalse(dict1.contains(1));
        
        ArrayDictionary dict2 = new ArrayDictionary(1);
        dict2.add(0,103);
        assertFalse(dict2.contains(2));
        assertTrue(dict2.contains(0));
        
        ArrayDictionary dict3 = new ArrayDictionary(2);
        dict3.add(0,103);
        dict3.add(1,105);
        assertTrue(dict3.contains(0));
        assertTrue(dict3.contains(1));
        assertFalse(dict3.contains(2));
        assertFalse(dict3.contains(3));
        
        ArrayDictionary dict4 = new ArrayDictionary(3);
        dict4.add(0,103);
        dict4.add(1,105);
        dict4.add(2,206);
        dict4.add(4,407);
        assertTrue(dict4.contains(1));
        assertTrue(dict4.contains(4));
        assertFalse(dict4.contains(7));
        assertFalse(dict4.contains(8));
        
        ArrayDictionary dict5a = new ArrayDictionary(3);
        dict5a.add(0,103);
        dict5a.add(1,105);
        assertFalse(dict5a.contains(3));
        dict5a.add(2, 206);
        assertTrue(dict5a.contains(1));
    }
}