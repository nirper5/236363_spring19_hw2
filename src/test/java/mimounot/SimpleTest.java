package mimounot;

import org.junit.Test;
import mimounot.business.*;
import static org.junit.Assert.assertEquals;
import static mimounot.business.ReturnValue.*;


public class SimpleTest extends AbstractTest{


    @Test
    public void testCreateTables()
    {
    createTables();
    }

    @Test
    public void testDropTables()
    {
        dropTables();
    }
    @Test
    public void testClearTables()
    {
        clearTables();
    }


    @Test
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

    @Test
    public void testDeleteUser(){
        User u = new User();
        u.setId(10);
        u.setName("Eli");
        u.setCity("Kfar Saba");
        u.setPolitician(true);

        ReturnValue ret = Solution.deleteUser(u);
        assertEquals(NOT_EXISTS , ret);

    }
    
     @Test
    public void GaiTests() {
        dropTables();
        createTables();
        User user=new User();
        user.setId(2);
        user.setName("rea");
        user.setCity("rossh haain");
        user.setPolitician(true);
        assertEquals(OK,Solution.addUser(user));

        Mimouna mimouna=new Mimouna();
        mimouna.setId(2);
        mimouna.setUserName("gai");
        mimouna.setFamilyName("lazar");
        mimouna.setCity("tel aviv");
        mimouna.setGuestCount(0);
        mimouna.setPoliticianComing(false);
        assertEquals(OK, Solution.addMimouna(mimouna));

        MimounaList mimounaList=new MimounaList();
        mimounaList.setId(4);
        mimounaList.setCity("tel aviv");
        assertEquals(OK, Solution.addMimounalist(mimounaList));

        assertEquals(OK,Solution.addMimounaToMimounalist(2,4));
        assertEquals(ALREADY_EXISTS,Solution.addMimounaToMimounalist(2,4));
        assertEquals(BAD_PARAMS,Solution.addMimounaToMimounalist(3,4));
        assertEquals(BAD_PARAMS,Solution.addMimounaToMimounalist(2,3));
        mimouna.setId(5);
        mimouna.setCity("rosh haain");
        assertEquals(OK, Solution.addMimouna(mimouna));
        assertEquals(BAD_PARAMS,Solution.addMimounaToMimounalist(5,4));

        assertEquals(OK,Solution.deleteMimounalist(mimounaList));
        assertEquals(BAD_PARAMS,Solution.addMimounaToMimounalist(2,4));
        assertEquals( MimounaList.badMimounalist(), Solution.getMimounalist(4));

        Mimouna mimouna2=new Mimouna();
        mimouna2.setId(6);
        mimouna2.setUserName("gai");
        mimouna2.setFamilyName("lazar");
        mimouna2.setCity("tel aviv");
        mimouna2.setGuestCount(0);
        mimouna2.setPoliticianComing(false);
        assertEquals(OK, Solution.addMimouna(mimouna2));
        assertEquals(OK,Solution.addMimounalist(mimounaList));
        assertEquals(OK,Solution.addMimounaToMimounalist(6,4));
        assertEquals(NOT_EXISTS,Solution.removeMimounaFromMimounalist(16,4));
        assertEquals(NOT_EXISTS,Solution.removeMimounaFromMimounalist(6,14));
        assertEquals(OK,Solution.removeMimounaFromMimounalist(6,4));
        assertEquals(NOT_EXISTS,Solution.removeMimounaFromMimounalist(6,4));

        assertEquals( mimouna2.toString(), Solution.getMimouna(6).toString());

        //follow
        assertEquals(OK,Solution.followMimounalist(2,4));
        assertEquals(NOT_EXISTS,Solution.followMimounalist(11,4));
        assertEquals(NOT_EXISTS,Solution.followMimounalist(2,14));
        assertEquals(ALREADY_EXISTS,Solution.followMimounalist(2,4));
        assertEquals(ALREADY_EXISTS,Solution.followMimounalist(2,4));

        assertEquals(NOT_EXISTS,Solution.stopFollowMimounalist(11,4));
        assertEquals(NOT_EXISTS,Solution.stopFollowMimounalist(2,14));
        assertEquals(OK ,Solution.stopFollowMimounalist(2,4));
        assertEquals(NOT_EXISTS ,Solution.stopFollowMimounalist(2,4));
        assertEquals(NOT_EXISTS ,Solution.stopFollowMimounalist(2,4));








    }
}
