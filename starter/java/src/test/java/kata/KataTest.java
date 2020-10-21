package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

class KataTest {
    @Test
    void testSomeKataMethod() {
        Kata subjectUnderTest = new Kata();
        assertEquals("hello, world!", subjectUnderTest.someKataMethod());
        int n = 10;
        String s = "test";
        int i = 0;
        ArrayList<User> usr = new ArrayList<User>();
        while (i++ < 10) {
        	User u = new User(s + i);
        	usr.add(u);
        	subjectUnderTest.user.put(s + i, u);
        }
        // Testing that number of users in kata is actually equal to number of users added
        assertEquals(subjectUnderTest.user.size(), n);
        i = 0;
        // Testing that the user in kata is actually the user originally added
        for (String key : subjectUnderTest.user.keySet()) {
        	assertEquals(subjectUnderTest.user.get(key), usr.get(i++));
        }
    }
}
