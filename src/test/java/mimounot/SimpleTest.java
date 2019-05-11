package mimounot;

import org.junit.Test;
import mimounot.business.*;
import static org.junit.Assert.assertEquals;
import static mimounot.business.ReturnValue.*;
import static org.junit.Assert.assertNotEquals;


public class SimpleTest extends AbstractTest{

    public void testCreateTables()
    {
    createTables();
    }


    public void testDropTables()
    {
        dropTables();
    }

    public void testClearTables()
    {
        clearTables();
    }



    public void simpleTestCreateUser()
    {
        User v = new User();
        v.setId(1);
        v.setName("Dani");
        v.setCity("");
        v.setPolitician(true);
        ReturnValue ret = Solution.addUser(v);
        assertEquals(OK, ret);
    }


    public void testDeleteUser(){
        User u = new User();
        u.setId(10);
        u.setName("Eli");
        u.setCity("Kfar Saba");
        u.setPolitician(true);

        ReturnValue ret = Solution.deleteUser(u);
        assertEquals(NOT_EXISTS , ret);
    }

     private ReturnValue res;
     @Test
     public void testGetMimounaListFollowersCount() {
         dropTables();
         createTables();
         User u1 = new User();
         u1.setId(236363);
         u1.setName("Benny");
         u1.setCity("Haifa");
         u1.setPolitician(false);

         Solution.addUser(u1);

         User u2 = new User();
         u2.setId(234123);
         u2.setName("Leonid");
         u2.setCity("Tel Aviv");
         u2.setPolitician(true);

         Solution.addUser(u2);

         Mimouna m1 = new Mimouna();
         m1.setId(236319);
         m1.setUserName("Yossi");
         m1.setFamilyName("Gil");
         m1.setCity("Jerusalem");

         Solution.addMimouna(m1);

         Mimouna m2 = new Mimouna();
         m2.setId(234218);
         m2.setUserName("Tamer");
         m2.setFamilyName("Salman");
         m2.setCity("Naharia");

         Solution.addMimouna(m2);

         Mimouna m3 = new Mimouna();
         m3.setId(236363);
         m3.setUserName("Benny");
         m3.setFamilyName("Kimelfeld");
         m3.setCity("Naharia");

         Solution.addMimouna(m3);

         Mimouna m4 = new Mimouna();
         m4.setId(9999999);
         m4.setUserName("Benny");
         m4.setFamilyName("Kimelfeld2");
         m4.setCity("Naharia");

         Solution.addMimouna(m4);

         Mimouna m5 = new Mimouna();
         m5.setId(88888);
         m5.setUserName("Benny");
         m5.setFamilyName("Kimelfeld");
         m5.setCity("Naharia");

         Solution.addMimouna(m5);

         MimounaList ml1  = new MimounaList();
         ml1.setId(111);
         ml1.setCity("Naharia");

         Solution.addMimounalist(ml1);

         MimounaList ml2  = new MimounaList();
         ml2.setId(112);
         ml2.setCity("Naharia");

         Solution.addMimounalist(ml2);

         MimounaList ml3  = new MimounaList();
         ml3.setId(113);
         ml3.setCity("Naharia");

         Solution.addMimounalist(ml3);
         Integer res_i = Solution.getMimounalistFollowersCount(500);
         assertEquals(0, res_i.intValue());

         res_i = Solution.getMimounalistFollowersCount(236363);
         assertEquals(0, res_i.intValue());

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(0, res_i.intValue());

         res = Solution.followMimounalist(234211, 111);
         assertEquals(NOT_EXISTS, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(0, res_i.intValue());

         res = Solution.followMimounalist(234123, 111);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(1, res_i.intValue());

         res = Solution.followMimounalist(234123, 111);
         assertEquals(ALREADY_EXISTS, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(1, res_i.intValue());


         res = Solution.followMimounalist(236363, 111);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(2, res_i.intValue());

         res = Solution.stopFollowMimounalist(236363, 111);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(1, res_i.intValue());

         res = Solution.stopFollowMimounalist(236363, 111);
         assertEquals(NOT_EXISTS, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(1, res_i.intValue());

         res = Solution.followMimounalist(236363, 111);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(2, res_i.intValue());

         // test cascade
         User res_u = Solution.getUserProfile(234123);
         assertNotEquals(User.badUser(), res_u);
         res = Solution.deleteUser(res_u);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(1, res_i.intValue());

         res = Solution.addUser(res_u);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(1, res_i.intValue());

         res = Solution.followMimounalist(236363, 112);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(112);
         assertEquals(1, res_i.intValue());

         MimounaList res_ml = Solution.getMimounalist(111);
         assertNotEquals(MimounaList.badMimounalist(), res_ml);
         res = Solution.deleteMimounalist(res_ml);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(0, res_i.intValue());

         res_i = Solution.getMimounalistFollowersCount(112);
         assertEquals(1, res_i.intValue());

         res = Solution.addMimounalist(res_ml);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(0, res_i.intValue());

         res_i = Solution.getMimounalistFollowersCount(112);
         assertEquals(1, res_i.intValue());

         res = Solution.followMimounalist(234123, 111);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(1, res_i.intValue());

         res = Solution.followMimounalist(236363, 111);
         assertEquals(OK, res);

         res_i = Solution.getMimounalistFollowersCount(111);
         assertEquals(2, res_i.intValue());

         res_i = Solution.getMimounalistFollowersCount(112);
         assertEquals(1, res_i.intValue());
     }

}

