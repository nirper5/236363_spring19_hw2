package mimounot;

import mimounot.Solution;
import mimounot.business.Mimouna;
import mimounot.business.MimounaList;
import mimounot.business.ReturnValue;
import mimounot.business.User;
import org.junit.Before;
import org.junit.Test;

import static mimounot.business.ReturnValue.*;
import static org.junit.Assert.*;

public class BasicTest extends AbstractTest{
    private ReturnValue res;

    @Before
    public void initDatabase() {
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
    }

    @Test
    public void testAttendMimount() {
        res = Solution.attendMimouna(-5, 10);
        assertEquals(NOT_EXISTS, res);

        res = Solution.attendMimouna(500, 10);
        assertEquals(NOT_EXISTS, res);

        res = Solution.attendMimouna(500, -5);
        assertEquals(NOT_EXISTS, res);

        res = Solution.attendMimouna(236363, 0);
        assertEquals(OK, res);

        Mimouna res_m = Solution.getMimouna(236363);
        assertEquals(res_m.getGuestCount(), 0);

        res = Solution.attendMimouna(236363, -1);
        assertEquals(BAD_PARAMS, res);

        res_m = Solution.getMimouna(236363);
        assertEquals(res_m.getGuestCount(), 0);

        res = Solution.attendMimouna(236363, 5);
        assertEquals(OK, res);

        res_m = Solution.getMimouna(236363);
        assertEquals(res_m.getGuestCount(), 5);

        res = Solution.attendMimouna(236363, 6);
        assertEquals(OK, res);

        res_m = Solution.getMimouna(236363);
        assertEquals(res_m.getGuestCount(), 11);

        res = Solution.attendMimouna(236363, -12);
        assertEquals(BAD_PARAMS, res);

        res_m = Solution.getMimouna(236363);
        assertEquals(res_m.getGuestCount(), 11);

        res = Solution.attendMimouna(236363, -7);
        assertEquals(OK, res);

        res_m = Solution.getMimouna(236363);
        assertEquals(res_m.getGuestCount(), 4);

        res = Solution.attendMimouna(236363, 0);
        assertEquals(OK, res);

        res_m = Solution.getMimouna(236363);
        assertEquals(res_m.getGuestCount(), 4);

        res = Solution.attendMimouna(236363, -5);
        assertEquals(BAD_PARAMS, res);

        res_m = Solution.getMimouna(236363);
        assertEquals(res_m.getGuestCount(), 4);

        res = Solution.attendMimouna(236363, -4);
        assertEquals(OK, res);

        res_m = Solution.getMimouna(236363);
        assertEquals(res_m.getGuestCount(), 0);
    }

    @Test
    public void testConfirmAttendancePoliticianToMimouna() {
        res = Solution.confirmAttendancePoliticianToMimouna(555, 556);
        assertEquals(NOT_EXISTS, res);

        res = Solution.confirmAttendancePoliticianToMimouna(236363, 556);
        assertEquals(NOT_EXISTS, res);

        Mimouna res_m = Solution.getMimouna(236363);
        assertFalse(res_m.getIsPoliticianComing());

        res = Solution.confirmAttendancePoliticianToMimouna(555, 234123);
        assertEquals(NOT_EXISTS, res);

        res = Solution.confirmAttendancePoliticianToMimouna(236363, 234123);
        assertEquals(OK, res);

        res_m = Solution.getMimouna(236363);
        assertTrue(res_m.getIsPoliticianComing());
        assertEquals(0, res_m.getGuestCount());

        res = Solution.confirmAttendancePoliticianToMimouna(236363, 234123);
        assertEquals(OK, res);

        res_m = Solution.getMimouna(236363);
        assertTrue(res_m.getIsPoliticianComing());

        res = Solution.confirmAttendancePoliticianToMimouna(236363, 236363);
        assertEquals(BAD_PARAMS, res);

        res_m = Solution.getMimouna(236363);
        assertTrue(res_m.getIsPoliticianComing());

        User res_u = Solution.getUserProfile(234123);
        assertNotEquals(User.badUser(), res_u);

        res = Solution.deleteUser(res_u);
        assertEquals(OK, res);

        res_m = Solution.getMimouna(236363); // deletion of the politician does not affect mimouna.
        assertTrue(res_m.getIsPoliticianComing());

    }

    @Test
    public void testAddMimounaToMimounaList() {
        res = Solution.addMimounaToMimounalist(123, 456);
        assertEquals(NOT_EXISTS, res);

        res = Solution.addMimounaToMimounalist(236363, 456);
        assertEquals(NOT_EXISTS, res);

        res = Solution.addMimounaToMimounalist(123, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.addMimounaToMimounalist(236319, 111);
        assertEquals(BAD_PARAMS, res);

        res = Solution.addMimounaToMimounalist(236319, 111);
        assertEquals(BAD_PARAMS, res);

        res = Solution.addMimounaToMimounalist(236319, 111);
        assertEquals(BAD_PARAMS, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(OK, res);

        Mimouna res_m = Solution.getMimouna(236363); // mimouna deletion should cascade it
        assertNotEquals(Mimouna.badMimouna(), res_m);
        res = Solution.deleteMimouna(res_m);
        assertEquals(OK, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);
        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.addMimouna(res_m);
        assertEquals(OK, res);

        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(ALREADY_EXISTS, res);
        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(ALREADY_EXISTS, res);

        MimounaList res_ml = Solution.getMimounalist(111); // list deletion should cascade
        assertNotEquals(MimounaList.badMimounalist(), res_ml);
        res = Solution.deleteMimounalist(res_ml);
        assertEquals(OK, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.addMimounaToMimounalist(236363, 112);
        assertEquals(OK, res);

        res = Solution.addMimounalist(res_ml);
        assertEquals(OK, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(ALREADY_EXISTS, res);
        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(ALREADY_EXISTS, res);

        Mimouna m = new Mimouna(); // mimouna without city can't be added to list
        m.setId(676);
        m.setUserName("SomeName");
        m.setFamilyName("SomeFName");

        res = Solution.addMimouna(m);
        assertEquals(OK, res);

        res = Solution.addMimounaToMimounalist(676, 111);
        assertEquals(BAD_PARAMS, res);
        res = Solution.addMimounaToMimounalist(676, 112);
        assertEquals(BAD_PARAMS, res);
        res = Solution.addMimounaToMimounalist(676, 113);
        assertEquals(BAD_PARAMS, res);
    }

    @Test
    public void testRemoveMimounaFromMimounaList() {
        res = Solution.removeMimounaFromMimounalist(500, 600);
        assertEquals(NOT_EXISTS, res);

        res = Solution.removeMimounaFromMimounalist(236363, 600);
        assertEquals(NOT_EXISTS, res);

        res = Solution.removeMimounaFromMimounalist(500, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.removeMimounaFromMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(236363, 112);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(234218, 112);
        assertEquals(OK, res);

        res = Solution.removeMimounaFromMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.removeMimounaFromMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.removeMimounaFromMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.addMimounaToMimounalist(236363, 112); // check that rest of database didn't change
        assertEquals(ALREADY_EXISTS, res);
        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(ALREADY_EXISTS, res);
        res = Solution.addMimounaToMimounalist(234218, 112);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(ALREADY_EXISTS, res);

        Mimouna res_m = Solution.getMimouna(236363);
        assertNotEquals(Mimouna.badMimouna(), res_m);
        res = Solution.deleteMimouna(res_m);
        assertEquals(OK, res);

        res = Solution.removeMimounaFromMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.addMimouna(res_m);
        assertEquals(OK, res);

        res = Solution.removeMimounaFromMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.removeMimounaFromMimounalist(236363, 111);
        assertEquals(OK, res);
        res = Solution.removeMimounaFromMimounalist(234218, 111);
        assertEquals(OK, res);

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(236363, 112);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(234218, 112);
        assertEquals(ALREADY_EXISTS, res);

        MimounaList res_ml = Solution.getMimounalist(111);
        assertNotEquals(MimounaList.badMimounalist(), res_ml);
        res = Solution.deleteMimounalist(res_ml);
        assertEquals(OK, res);

        res = Solution.removeMimounaFromMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);
        res = Solution.removeMimounaFromMimounalist(236363, 112);
        assertEquals(OK, res);
        res = Solution.removeMimounaFromMimounalist(234218, 112);
        assertEquals(OK, res);
        res = Solution.removeMimounaFromMimounalist(236363, 112);
        assertEquals(NOT_EXISTS, res);
        res = Solution.removeMimounaFromMimounalist(234218, 112);
        assertEquals(NOT_EXISTS, res);
    }

    @Test
    public void testFollowMimounalist() {
        res = Solution.followMimounalist(500, 600);
        assertEquals(NOT_EXISTS, res);

        res = Solution.followMimounalist(236363, 600);
        assertEquals(NOT_EXISTS, res);

        res = Solution.followMimounalist(500, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.followMimounalist(111, 236363);
        assertEquals(NOT_EXISTS, res);

        res = Solution.followMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.followMimounalist(234123, 111);
        assertEquals(OK, res);

        res = Solution.followMimounalist(236363, 111);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.followMimounalist(234123, 111);
        assertEquals(ALREADY_EXISTS, res);

        User res_u = Solution.getUserProfile(236363);
        assertNotEquals(User.badUser(), res_u);
        res = Solution.deleteUser(res_u);
        assertEquals(OK, res);

        res = Solution.followMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);
        res = Solution.followMimounalist(234123, 111);
        assertEquals(ALREADY_EXISTS, res);

        res = Solution.addUser(res_u);
        assertEquals(OK, res);

        res = Solution.followMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.followMimounalist(236363, 112);
        assertEquals(OK, res);
        res = Solution.followMimounalist(234123, 112);
        assertEquals(OK, res);

        MimounaList res_ml = Solution.getMimounalist(111);
        assertNotEquals(MimounaList.badMimounalist(), res_ml);
        res = Solution.deleteMimounalist(res_ml);
        assertEquals(OK, res);

        res = Solution.followMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);
        res = Solution.followMimounalist(236363, 112);
        assertEquals(ALREADY_EXISTS, res);
    }

    @Test
    public void testStopFollowMimounaList() {
        res = Solution.stopFollowMimounalist(500, 600);
        assertEquals(NOT_EXISTS, res);

        res = Solution.stopFollowMimounalist(236363, 600);
        assertEquals(NOT_EXISTS, res);

        res = Solution.stopFollowMimounalist(500, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.stopFollowMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.followMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.stopFollowMimounalist(111, 236363);
        assertEquals(NOT_EXISTS, res);

        res = Solution.stopFollowMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.stopFollowMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.followMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.followMimounalist(236363, 112);
        assertEquals(OK, res);

        res = Solution.followMimounalist(234123, 111);
        assertEquals(OK, res);

        res = Solution.followMimounalist(234123, 112);
        assertEquals(OK, res);

        res = Solution.stopFollowMimounalist(236363, 111);
        assertEquals(OK, res);

        res = Solution.stopFollowMimounalist(236363, 111);
        assertEquals(NOT_EXISTS, res);

        res = Solution.stopFollowMimounalist(236363, 112);
        assertEquals(OK, res);

        res = Solution.stopFollowMimounalist(234123, 111);
        assertEquals(OK, res);

        res = Solution.stopFollowMimounalist(234123, 112);
        assertEquals(OK, res);
    }

    @Test
    public void testGetMimounaListTotalGuests() {
        Integer res_i = Solution.getMimounalistTotalGuests(500);
        assertEquals(0, res_i.intValue());

        res_i = Solution.getMimounalistTotalGuests(236363);
        assertEquals(0, res_i.intValue());

        res_i = Solution.getMimounalistTotalGuests(111);
        assertEquals(0, res_i.intValue());

        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(ERROR, res);
        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111);
        assertEquals(0, res_i.intValue());

        res = Solution.attendMimouna(236319, 50);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111);
        assertEquals(0, res_i.intValue());

        res = Solution.attendMimouna(234218, 60);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111);
        assertEquals(60, res_i.intValue());

        res = Solution.attendMimouna(234218, 60);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111);
        assertEquals(120, res_i.intValue());

        res = Solution.attendMimouna(236363, 15);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111);
        assertEquals(135, res_i.intValue());

        res = Solution.addMimounaToMimounalist(234218, 112);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111);
        assertEquals(135, res_i.intValue());

        res_i = Solution.getMimounalistTotalGuests(112);
        assertEquals(120, res_i.intValue());

        res = Solution.attendMimouna(234218, -40);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111);
        assertEquals(95, res_i.intValue());

        res_i = Solution.getMimounalistTotalGuests(112);
        assertEquals(80, res_i.intValue());

        Mimouna res_m = Solution.getMimouna(234218);
        assertNotEquals(Mimouna.badMimouna(), res_m);
        res = Solution.deleteMimouna(res_m);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111); // cascades
        assertEquals(15, res_i.intValue());

        res_i = Solution.getMimounalistTotalGuests(112);
        assertEquals(0, res_i.intValue());

        res_m.setGuestCount(0);
        res = Solution.addMimouna(res_m);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111); // cascades
        assertEquals(15, res_i.intValue());

        res_i = Solution.getMimounalistTotalGuests(112);
        assertEquals(0, res_i.intValue());

        res = Solution.addMimounaToMimounalist(234218, 111);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(234218, 112);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111); // cascades
        assertEquals(15, res_i.intValue());

        res_i = Solution.getMimounalistTotalGuests(112);
        assertEquals(0, res_i.intValue());

        res = Solution.attendMimouna(236363, 17);
        assertEquals(OK, res);
        res = Solution.addMimounaToMimounalist(236363, 112);
        assertEquals(OK, res);

        MimounaList res_ml = Solution.getMimounalist(111);
        assertNotEquals(MimounaList.badMimounalist(), res_ml);
        res = Solution.deleteMimounalist(res_ml);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(111); // cascades
        assertEquals(0, res_i.intValue());

        res_i = Solution.getMimounalistTotalGuests(112);
        assertEquals(32, res_i.intValue());


        res_i = Solution.getMimounalistTotalGuests(111); // cascades
        assertEquals(0, res_i.intValue());

        res_i = Solution.getMimounalistTotalGuests(112);
        assertEquals(32, res_i.intValue());

        res = Solution.confirmAttendancePoliticianToMimouna(236363, 234123);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(112);
        assertEquals(32, res_i.intValue());

    }

    @Test
    public void testGetMimounaListFollowersCount() {
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

    @Test
    public void testGetMostKnownMimouna() {
//        String res_s = Solution.getMostKnownMimouna();
//        assertEquals("", res_s);
//
//        res_s = Solution.getMostKnownMimouna();
//        assertEquals("", res_s);
//
//        res = Solution.addMimounaToMimounalist(236363, 111);
//        assertEquals(OK, res);
//
//        res_s = Solution.getMostKnownMimouna();
//        assertEquals("Kimelfeld", res_s);
//
//        res = Solution.addMimounaToMimounalist(234218, 112);
//        assertEquals(OK, res);
//
//        res_s = Solution.getMostKnownMimouna();
//        assertEquals("Kimelfeld", res_s);
//
//        res = Solution.addMimounaToMimounalist(9999999, 111);
//        assertEquals(OK, res);
//
//        res_s = Solution.getMostKnownMimouna();
//        assertEquals("Kimelfeld2", res_s);
//
//        Mimouna res_m = Solution.getMimouna(9999999);
//        assertNotEquals(Mimouna.badMimouna(), res_m);
//        res = Solution.deleteMimouna(res_m);
//        assertEquals(OK, res);
//
//        res_s = Solution.getMostKnownMimouna();
//        assertEquals("Kimelfeld", res_s);
//
//        MimounaList res_ml = Solution.getMimounalist(111);
//        assertNotEquals(MimounaList.badMimounalist(), res_ml);
//        res = Solution.deleteMimounalist(res_ml);
//        assertEquals(OK, res);
//
//        res_s = Solution.getMostKnownMimouna();
//        assertEquals("Salman", res_s);
//
//        res = Solution.addMimounalist(res_ml);
//        assertEquals(OK, res);
//
//        res_s = Solution.getMostKnownMimouna();
//        assertEquals("Salman", res_s);
//
//        res = Solution.addMimounaToMimounalist(234218, 111);
//        assertEquals(OK, res);
//        res = Solution.addMimounaToMimounalist(234218, 113);
//        assertEquals(OK, res);
//
//        res_s = Solution.getMostKnownMimouna();
//        assertEquals("Salman", res_s);
//
//        res = Solution.addMimounaToMimounalist(236363, 111);
//        assertEquals(OK, res);
//        res = Solution.addMimounaToMimounalist(236363, 113);
//        assertEquals(OK, res);
//        res = Solution.addMimounaToMimounalist(88888, 112);
//        assertEquals(OK, res);
//        res = Solution.addMimounaToMimounalist(88888, 113);
//        assertEquals(OK, res);
//
//        res_s = Solution.getMostKnownMimouna(); /* lists contain 4 mimounas with family "Kimelfeld", but these are 2
//                                                * separate ones, so mimouna of family "Salman" beats them */
//        assertEquals("Salman", res_s);
//
//        res = Solution.addMimounaToMimounalist(236363, 112);
//        assertEquals(OK, res);
//
//        res_s = Solution.getMostKnownMimouna();
//        assertEquals("Kimelfeld", res_s);
    }

    @Test
    public void testGetMostPopularMimounaList() {

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

        Integer res_i;
        res_i = Solution.getMostPopularMimounalist();
        assertEquals(113, res_i.intValue()); // all are 0 guests and this is highest id.

        res = Solution.addMimounaToMimounalist(236363, 111);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(113, res_i.intValue());

        res = Solution.addMimounaToMimounalist(234218, 112);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(113, res_i.intValue());

        res = Solution.attendMimouna(236363, 40);
        assertEquals(OK, res);

        res_i = Solution.getMimounalistTotalGuests(113);
        assertEquals(0, res_i.intValue());

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(111, res_i.intValue());

        res = Solution.attendMimouna(234218, 40);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(112, res_i.intValue());

        res = Solution.attendMimouna(234218, -1);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(111, res_i.intValue());

        res = Solution.addMimounaToMimounalist(236363, 112);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(112, res_i.intValue());

        res = Solution.addMimounaToMimounalist(88888, 111);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(112, res_i.intValue());

        res = Solution.attendMimouna(88888, 40);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(111, res_i.intValue());

        Solution.attendMimouna(234218, 500);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(112, res_i.intValue());

        Mimouna res_m = Solution.getMimouna(234218);
        assertNotEquals(Mimouna.badMimouna(), res_m);

        res = Solution.deleteMimouna(res_m);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(111, res_i.intValue());

        Mimouna res_m2 = Solution.getMimouna(88888);
        assertNotEquals(Mimouna.badMimouna(), res_m);

        res = Solution.deleteMimouna(res_m2);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(112, res_i.intValue());

        res = Solution.addMimounaToMimounalist(9999999, 111);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(112, res_i.intValue());

        res = Solution.attendMimouna(9999999, 1);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(111, res_i.intValue());

        res_m.setGuestCount(0);

        res = Solution.addMimouna(res_m);
        assertEquals(OK, res);

        res = Solution.attendMimouna(res_m.getId(),5000);
        assertEquals(OK, res);

        res_i = Solution.getMostPopularMimounalist();
        assertEquals(111, res_i.intValue());
    }
}
