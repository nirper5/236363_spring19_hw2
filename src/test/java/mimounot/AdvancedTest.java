package mimounot;

import mimounot.Solution;
import mimounot.business.Mimouna;
import mimounot.business.MimounaList;
import mimounot.business.ReturnValue;
import mimounot.business.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static mimounot.business.ReturnValue.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AdvancedTest extends AbstractTest {
    private ReturnValue res;

    @Before
    public void initDatabase() {
        User u1 = new User();
        u1.setId(1);
        u1.setName("Raymond");
        u1.setCity("NY");
        u1.setPolitician(false);

        Solution.addUser(u1);

        User u2 = new User();
        u2.setId(2);
        u2.setName("Debra");
        u2.setCity("NY");
        u2.setPolitician(false);

        Solution.addUser(u2);

        User u3 = new User();
        u3.setId(3);
        u3.setName("Robert");
        u3.setCity("NY");
        u3.setPolitician(false);

        Solution.addUser(u3);

        User u4 = new User();
        u4.setId(4);
        u4.setName("Marie");
        u4.setCity("NY");
        u4.setPolitician(false);

        Solution.addUser(u4);

        User u5 = new User();
        u5.setId(5);
        u5.setName("Frank");
        u5.setCity("NY");
        u5.setPolitician(true);

        Solution.addUser(u5);

        Mimouna m1 = new Mimouna();
        m1.setId(1);
        m1.setUserName("Raymond");
        m1.setFamilyName("Barone");
        m1.setCity("NY");

        Solution.addMimouna(m1);

        Mimouna m2 = new Mimouna();
        m2.setId(2);
        m2.setUserName("Robert");
        m2.setFamilyName("Barone");
        m2.setCity("NY");

        Solution.addMimouna(m2);

        Mimouna m3 = new Mimouna();
        m3.setId(3);
        m3.setUserName("Hank");
        m3.setFamilyName("MacDougall");
        m3.setCity("NY");

        Solution.addMimouna(m3);

        Mimouna m4 = new Mimouna();
        m4.setId(4);
        m4.setUserName("Lois");
        m4.setFamilyName("Whelan");
        m4.setCity("NY");

        Solution.addMimouna(m4);

        Mimouna m5 = new Mimouna();
        m5.setId(5);
        m5.setUserName("Bernie");
        m5.setFamilyName("Gruenfelder");
        m5.setCity("NY");

        Solution.addMimouna(m5);

        MimounaList ml1 = new MimounaList();
        ml1.setId(1);
        ml1.setCity("NY");

        Solution.addMimounalist(ml1);

        MimounaList ml2 = new MimounaList();
        ml2.setId(2);
        ml2.setCity("NY");

        Solution.addMimounalist(ml2);

        MimounaList ml3 = new MimounaList();
        ml3.setId(3);
        ml3.setCity("NY");

        Solution.addMimounalist(ml3);
    }

    @Test
    public void testGetMostRatedMimounaList() {

        dropTables();
        createTables();
        User u1 = new User();
        u1.setId(1);
        u1.setName("Raymond");
        u1.setCity("NY");
        u1.setPolitician(false);

        Solution.addUser(u1);

        User u2 = new User();
        u2.setId(2);
        u2.setName("Debra");
        u2.setCity("NY");
        u2.setPolitician(false);

        Solution.addUser(u2);

        User u3 = new User();
        u3.setId(3);
        u3.setName("Robert");
        u3.setCity("NY");
        u3.setPolitician(false);

        Solution.addUser(u3);

        User u4 = new User();
        u4.setId(4);
        u4.setName("Marie");
        u4.setCity("NY");
        u4.setPolitician(false);

        Solution.addUser(u4);

        User u5 = new User();
        u5.setId(5);
        u5.setName("Frank");
        u5.setCity("NY");
        u5.setPolitician(true);

        Solution.addUser(u5);

        Mimouna m1 = new Mimouna();
        m1.setId(1);
        m1.setUserName("Raymond");
        m1.setFamilyName("Barone");
        m1.setCity("NY");

        Solution.addMimouna(m1);

        Mimouna m2 = new Mimouna();
        m2.setId(2);
        m2.setUserName("Robert");
        m2.setFamilyName("Barone");
        m2.setCity("NY");

        Solution.addMimouna(m2);

        Mimouna m3 = new Mimouna();
        m3.setId(3);
        m3.setUserName("Hank");
        m3.setFamilyName("MacDougall");
        m3.setCity("NY");

        Solution.addMimouna(m3);

        Mimouna m4 = new Mimouna();
        m4.setId(4);
        m4.setUserName("Lois");
        m4.setFamilyName("Whelan");
        m4.setCity("NY");

        Solution.addMimouna(m4);

        Mimouna m5 = new Mimouna();
        m5.setId(5);
        m5.setUserName("Bernie");
        m5.setFamilyName("Gruenfelder");
        m5.setCity("NY");

        Solution.addMimouna(m5);

        MimounaList ml1 = new MimounaList();
        ml1.setId(1);
        ml1.setCity("NY");

        Solution.addMimounalist(ml1);

        MimounaList ml2 = new MimounaList();
        ml2.setId(2);
        ml2.setCity("NY");

        Solution.addMimounalist(ml2);

        MimounaList ml3 = new MimounaList();
        ml3.setId(3);
        ml3.setCity("NY");

        Solution.addMimounalist(ml3);
        ArrayList<Integer> res_arr;

        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(new ArrayList<Integer>(), res_arr);

        res = Solution.addMimounaToMimounalist(1, 1);
        assertEquals(OK, res);

        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Collections.singletonList(1), res_arr);

        res = Solution.addMimounaToMimounalist(2, 2);
        assertEquals(OK, res);

        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(1, 2), res_arr);

        res = Solution.addMimounaToMimounalist(3, 3);
        assertEquals(OK, res);

        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(1, 2, 3), res_arr);

        res = Solution.addMimounaToMimounalist(4, 3);
        assertEquals(OK, res);

        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(3, 1, 2), res_arr);

        res = Solution.addMimounaToMimounalist(5, 2);
        assertEquals(OK, res);

        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(2, 3, 1), res_arr);

        res = Solution.attendMimouna(1, 1);
        assertEquals(OK, res);

        // list 2 and 3 have each 2 mimounas with 0 guests each.
        // list 1 has one mimouna with 1 guest.
        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(1, 2, 3), res_arr);

        res = Solution.confirmAttendancePoliticianToMimouna(2, 5); // politicians make no difference
        assertEquals(OK, res);

        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(1, 2, 3), res_arr);

        res = Solution.attendMimouna(1, -1);
        assertEquals(OK, res);

        // list 2 and 3 have each 2 mimounas with 0 guests each.
        // list 1 has 0 mimouna with 0 guest.
        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(2, 3, 1), res_arr);

        res = Solution.attendMimouna(1, 2);
        assertEquals(OK, res);

        // list 2 and 3 have each 2 mimounas with 0 guests each.
        // list 1 has one mimouna with 2 guests.
        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(1, 2, 3), res_arr);

        res = Solution.attendMimouna(5, 2);
        assertEquals(OK, res);

        // list1: 1 + 2; list2: 2 + 0 + 2; list3: 2 + 0 + 0.
        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(2, 1, 3), res_arr);

        res = Solution.attendMimouna(4, 1);
        assertEquals(OK, res);

        // list1: 1 + 2; list2: 2 + 0 + 2; list3: 2 + 0 + 1.
        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(2, 1, 3), res_arr);

        res = Solution.addMimounaToMimounalist(5, 3);
        assertEquals(OK, res);

        // list1: 1 + 2; list2: 2 + 0 + 2; list3: 3 + 0 + 1 + 2.
        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(3, 2, 1), res_arr);

        Mimouna res_m = Solution.getMimouna(5);
        assertNotEquals(Mimouna.badMimouna(), res_m);

        res = Solution.deleteMimouna(res_m);
        assertEquals(OK, res);

        // list1: 1 + 2; list2: 1 + 0; list3: 2 + 0 + 1.
        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(1, 3, 2), res_arr);

        res_m.setGuestCount(0);
        res = Solution.addMimouna(res_m);
        assertEquals(OK, res);
        res = Solution.attendMimouna(res_m.getId(),2);
        assertEquals(OK, res);

        // list1: 1 + 2; list2: 1 + 0; list3: 2 + 0 + 1.
        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(1, 3, 2), res_arr);

        res_m = Solution.getMimouna(2);
        assertNotEquals(Mimouna.badMimouna(), res_m);

        res = Solution.deleteMimouna(res_m);
        assertEquals(OK, res);

        // list1: 1 + 2; list2: 0; list3: 2 + 0 + 1.
        res_arr = Solution.getMostRatedMimounaList();
        assertEquals(Arrays.asList(1, 3), res_arr);
    }

//    @Test
//    public void testListLargerThat10_A() {
//        for (int i = 0; i < 20; ++i) {
//            MimounaList ml = new MimounaList();
//            ml.setId(1000 + i);
//            ml.setCity("NY");
//
//            res = Solution.addMimounalist(ml);
//            assertEquals(OK, res);
//        }
//
//        ArrayList<Integer> res_arr;
//
//        res_arr = Solution.getMostRatedMimounaList();
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        for (int i = 0; i < 20; ++i) {
//            res = Solution.addMimounaToMimounalist(1, 1000 + i);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getMostRatedMimounaList();
//        assertEquals(Arrays.asList(1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009), res_arr);
//
//        for (int i = 0; i < 20; ++i) {
//                Mimouna m = new Mimouna();
//                m.setId(i*1000 + 1000);
//                m.setCity("NY");
//                m.setUserName("SomeName");
//                m.setFamilyName("SomeFName");
//
//                res = Solution.addMimouna(m);
//                assertEquals(OK, res);
//
//                res = Solution.attendMimouna(m.getId(), i);
//                assertEquals(OK, res);
//
//                res = Solution.addMimounaToMimounalist(m.getId() , 1000 + i);
//                assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getMostRatedMimounaList();
//        assertEquals(Arrays.asList(1019, 1018, 1017, 1016, 1015, 1014, 1013, 1012, 1011, 1010), res_arr);
//    }
//
//    @Test
//    public void testGetCloseUsers() {
//        ArrayList<Integer> res_arr;
//
//        res_arr = Solution.getCloseUsers(-5);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res_arr = Solution.getCloseUsers(49);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        // in the beginning all users have 0 follows, and 0 is at least 67% of 0.
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(Arrays.asList(2, 3, 4, 5), res_arr);
//
//        res_arr = Solution.getCloseUsers(2);
//        assertEquals(Arrays.asList(1, 3, 4, 5), res_arr);
//
//        res_arr = Solution.getCloseUsers(3);
//        assertEquals(Arrays.asList(1, 2, 4, 5), res_arr);
//
//        res_arr = Solution.getCloseUsers(4);
//        assertEquals(Arrays.asList(1, 2, 3, 5), res_arr);
//
//        res_arr = Solution.getCloseUsers(5);
//        assertEquals(Arrays.asList(1, 2, 3, 4), res_arr);
//
//        res = Solution.followMimounalist(1, 1);
//        assertEquals(OK, res);
//
//        // since 0 is not >= of 67% of 1, user 1 has no close users.
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res = Solution.followMimounalist(2, 2);
//        assertEquals(OK, res);
//
//        res = Solution.followMimounalist(3, 3);
//        assertEquals(OK, res);
//
//        // users 2 and 3 are following only lists that user 1 does not follow.
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res = Solution.followMimounalist(2, 1);
//        assertEquals(OK, res);
//
//        res = Solution.followMimounalist(3, 1);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(Arrays.asList(2, 3), res_arr);
//
//        res = Solution.followMimounalist(1, 2);
//        assertEquals(OK, res);
//
//        /*
//        user 1 follows 1 and 2
//        user 2 follows 1 and 2
//        user 3 follows 1 and 3
//         */
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(Collections.singletonList(2), res_arr);
//
//        res = Solution.followMimounalist(1, 3);
//        assertEquals(OK, res);
//
//                /*
//        user 1 follows 1 and 2 and 3
//        user 2 follows 1 and 2
//        user 3 follows 1 and 3 -> each follow exactly 67% of user1's follows
//         */
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(Arrays.asList(2, 3), res_arr);
//
//        res = Solution.followMimounalist(3, 2);
//        assertEquals(OK, res);
//
//        // they are NOT ordered by closeness or something like that
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(Arrays.asList(2, 3), res_arr);
//
//        User res_u = Solution.getUserProfile(2);
//        assertNotEquals(User.badUser(), res_u);
//
//        res = Solution.deleteUser(res_u);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(Collections.singletonList(3), res_arr);
//
//        res = Solution.addUser(res_u);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(Collections.singletonList(3), res_arr);
//
//        MimounaList res_ml = Solution.getMimounalist(1);
//        assertNotEquals(MimounaList.badMimounalist(), res_ml);
//
//        res = Solution.deleteMimounalist(res_ml);
//        assertEquals(OK, res);
//
//        res = Solution.stopFollowMimounalist(3, 2);
//        assertEquals(OK, res);
//
//        /*
//        user 1 follows 2 and 3
//        user 3 follows 3
//         */
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res = Solution.addMimounalist(res_ml);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//    }
//
//
//    @Test
//    public void testListLargerThat10_B() {
//        for (int i = 0; i < 20; ++i) {
//            User u = new User();
//            u.setId(1000 + i);
//            u.setName("SomeName");
//            u.setCity("SomeCity");
//
//            res = Solution.addUser(u);
//            assertEquals(OK, res);
//        }
//
//        ArrayList<Integer> res_arr;
//
//        res_arr = Solution.getCloseUsers(1);
//        assertEquals(Arrays.asList(2, 3, 4, 5, 1000, 1001, 1002, 1003, 1004, 1005), res_arr);
//
//        res_arr = Solution.getCloseUsers(4);
//        assertEquals(Arrays.asList(1, 2, 3, 5, 1000, 1001, 1002, 1003, 1004, 1005), res_arr);
//
//        res_arr = Solution.getCloseUsers(1003);
//        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 1000, 1001, 1002, 1004, 1005), res_arr);
//
//        res_arr = Solution.getCloseUsers(1019);
//        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 1000, 1001, 1002, 1003, 1004), res_arr);
//
//        res = Solution.followMimounalist(1019, 1);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getCloseUsers(1019);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        for (int i = 5; i < 19; ++i) {
//            res = Solution.followMimounalist(1000 + i, 1);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getCloseUsers(1019);
//        assertEquals(Arrays.asList(1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012, 1013, 1014), res_arr);
//
//    }
//
//    @Test
//    public void testGetMimounaListRecommendation() {
//        ArrayList<Integer> res_arr;
//
//        res_arr = Solution.getMimounaListRecommendation(700);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res_arr = Solution.getMimounaListRecommendation(-5);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res_arr = Solution.getMimounaListRecommendation(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res = Solution.followMimounalist(2, 3);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getMimounaListRecommendation(1);
//        assertEquals(Collections.singletonList(3), res_arr);
//
//        res = Solution.followMimounalist(3, 2);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getMimounaListRecommendation(1);
//        assertEquals(Arrays.asList(2, 3), res_arr);
//
//        res = Solution.followMimounalist(4, 1);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getMimounaListRecommendation(1);
//        assertEquals(Arrays.asList(1, 2, 3), res_arr);
//
//        res = Solution.followMimounalist(1, 1);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getMimounaListRecommendation(1); // only 4 is close, but mimounalist 1 is already followed by 1
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res = Solution.followMimounalist(4, 2);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getMimounaListRecommendation(1);
//        assertEquals(Collections.singletonList(2), res_arr);
//
//        res = Solution.stopFollowMimounalist(1, 1);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getMimounaListRecommendation(1); // mimouna 2 is followed twice
//        assertEquals(Arrays.asList(2, 1, 3), res_arr);
//    }
//
//    @Test
//    public void testListLargerThan3() {
//        ArrayList<Integer> res_arr;
//
//        for (int i = 0; i < 10; ++i) {
//            MimounaList ml = new MimounaList();
//            ml.setId(1000 + i);
//            ml.setCity("Haifa");
//
//            res = Solution.addMimounalist(ml);
//            assertEquals(OK, res);
//
//            res = Solution.followMimounalist(1, ml.getId());
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getMimounaListRecommendation(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res_arr = Solution.getMimounaListRecommendation(2);
//        assertEquals(Arrays.asList(1000, 1001, 1002), res_arr);
//
//        for (int i = 1000; i < 1003; ++i) {
//            res = Solution.followMimounalist(2, i);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getMimounaListRecommendation(2);
//        assertEquals(Arrays.asList(1003, 1004, 1005), res_arr);
//
//        for (int i = 1000; i < 1003; ++i) {
//            res = Solution.stopFollowMimounalist(2, i);
//            assertEquals(OK, res);
//        }
//
//        for (int i = 1006; i <= 1009; ++i) {
//            res = Solution.followMimounalist(3, i);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getMimounaListRecommendation(2);
//        assertEquals(Arrays.asList(1006, 1007, 1008), res_arr);
//
//        res = Solution.followMimounalist(4, 1009);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getMimounaListRecommendation(2);
//        assertEquals(Arrays.asList(1009, 1006, 1007), res_arr);
//    }
//
//    @Test
//    public void testGetTopPoliticianMimounaList() {
//
//        Mimouna m6 = new Mimouna();
//        m6.setId(6);
//        m6.setUserName("Bernie");
//        m6.setFamilyName("Gruenfelder");
//        m6.setCity("NOT NY");
//
//        Solution.addMimouna(m6);
//
//        MimounaList ml4 = new MimounaList();
//        ml4.setId(4);
//        ml4.setCity("NOT NY");
//
//        Solution.addMimounalist(ml4);
//
//        ArrayList<Integer> res_arr;
//
//        res_arr = Solution.getTopPoliticianMimounaList(500);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res_arr = Solution.getTopPoliticianMimounaList(-5);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res = Solution.addMimounaToMimounalist(6, 4);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res = Solution.confirmAttendancePoliticianToMimouna(6, 5);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1); // mimouna 5 not in same city
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res = Solution.addMimounaToMimounalist(1, 1);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        res = Solution.confirmAttendancePoliticianToMimouna(1, 5);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Collections.singletonList(1), res_arr);
//
//        res_arr = Solution.getTopPoliticianMimounaList(5); // no list for politicians!
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        for (int i = 2; i <= 5; ++i) {
//            res = Solution.confirmAttendancePoliticianToMimouna(i, 5);
//            assertEquals(OK, res);
//        }
//
//        for (int i = 2; i <= 3; ++i) {
//            res = Solution.addMimounaToMimounalist(i, i);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Arrays.asList(1, 2, 3), res_arr);
//
//        User res_u = Solution.getUserProfile(5);
//        assertNotEquals(User.badUser(), res_u);
//
//        res = Solution.deleteUser(res_u);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1); // removing the politician does not affect mimounas
//        assertEquals(Arrays.asList(1, 2, 3), res_arr);
//
//        res = Solution.addUser(res_u);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Arrays.asList(1, 2, 3), res_arr);
//
//        res = Solution.removeMimounaFromMimounalist(2, 2);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Arrays.asList(1, 3), res_arr);
//
//        res = Solution.addMimounaToMimounalist(2, 2);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Arrays.asList(1, 2, 3), res_arr);
//
//        res = Solution.attendMimouna(3, 50);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Arrays.asList(3, 1, 2), res_arr);
//
//        res = Solution.attendMimouna(1, 50);
//        assertEquals(OK, res);
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Arrays.asList(1, 3, 2), res_arr);
//    }
//
//    @Test
//    public void testListLargerThan10_C() {
//        ArrayList<Integer> res_arr = new ArrayList<>();
//
//        for (int i = 1000; i < 1020; ++i) {
//            MimounaList ml = new MimounaList();
//            ml.setId(i);
//            ml.setCity("NY");
//
//            res = Solution.addMimounalist(ml);
//            assertEquals(OK, res);
//
//            Mimouna m = new Mimouna();
//            m.setId(i);
//            m.setCity("NY");
//            m.setUserName("Some name");
//            m.setFamilyName("Some family");
//
//            res = Solution.addMimouna(m);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        for (int i = 1000; i < 1020; ++i) {
//            res = Solution.confirmAttendancePoliticianToMimouna(i, 5);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(new ArrayList<Integer>(), res_arr);
//
//        for (int i = 1000; i < 1020; ++i) {
//            res = Solution.addMimounaToMimounalist(i, i);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Arrays.asList(1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009), res_arr);
//
//        for (int i = 1008; i < 1020; ++i) {
//            res = Solution.attendMimouna(i, 50);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Arrays.asList(1008, 1009, 1010, 1011, 1012, 1013, 1014, 1015, 1016, 1017), res_arr);
//
//        for (int i = 1008; i < 1012; ++i) {
//            res = Solution.addMimounaToMimounalist(i, i + 3);
//            assertEquals(OK, res);
//        }
//
//        res_arr = Solution.getTopPoliticianMimounaList(1);
//        assertEquals(Arrays.asList(1011, 1012, 1013, 1014, 1008, 1009, 1010, 1015, 1016, 1017), res_arr);
//    }
}