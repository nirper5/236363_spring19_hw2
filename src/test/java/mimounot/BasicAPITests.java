
package mimounot;

import org.junit.Test;
import mimounot.business.*;
import static org.junit.Assert.assertEquals;
import static mimounot.business.ReturnValue.*;


public class BasicAPITests extends AbstractTest {
    @Test
    public void Test1() {

        ReturnValue res;
        Mimouna m = new Mimouna();
        m.setId(1);
        m.setUserName("Yonatan");
        m.setCity("Tel Aviv");
        m.setFamilyName("Cohen");

        res = Solution.addMimouna(m);
        assertEquals(OK, res);

        res = Solution.attendMimouna(1, 5);
        assertEquals(OK, res);

        res = Solution.attendMimouna(1, -19);
        assertEquals(BAD_PARAMS, res);
    }

    @Test
    public void Test2() {

        ReturnValue res;
        MimounaList p = new MimounaList();
        p.setId(10);
        p.setCity("Tel Aviv");

        res = Solution.addMimounalist(p);
        assertEquals(OK, res);

        User u = new User();
        u.setId(100);
        u.setName("Nir");
        u.setCity("Tel Aviv");
        u.setPolitician(false);

        res = Solution.addUser(u);
        assertEquals(OK, res);

        res = Solution.followMimounalist(100, 10);
        assertEquals(OK , res);

        res = Solution.followMimounalist(100, 10);
        assertEquals(ALREADY_EXISTS , res);

        res = Solution.followMimounalist(101, 10);
        assertEquals(NOT_EXISTS , res);

        User u2 = new User();
        u.setId(5);
        u.setName("Nir");
        u.setCity("Ramat Aviv");
        u.setPolitician(true);

        res = Solution.addUser(u);
        assertEquals(OK, res);

        res = Solution.followMimounalist(5, 10);
        assertEquals(OK , res);
    }
    @Test
    public void Test3() {
        
        User u1 = new User();
        u1.setId(100);
        u1.setName("Nir");
        u1.setCity("Tel Aviv");
        u1.setPolitician(false);

        Mimouna m = new Mimouna();
        m.setId(1);
        m.setUserName("Yonatan");
        m.setCity("Tel Aviv");
        m.setFamilyName("Cohen");

        ReturnValue res = Solution.addMimouna(m);
        assertEquals(OK , res);

        res = Solution.attendMimouna(1,100);
        assertEquals(OK , res);

        User u2 = new User();
        u1.setId(101);
        u1.setName("Nir");
        u1.setCity("Ramat Aviv");
        u1.setPolitician(false);

        res = Solution.attendMimouna(1,100);
        assertEquals(OK , res);
    }
}


