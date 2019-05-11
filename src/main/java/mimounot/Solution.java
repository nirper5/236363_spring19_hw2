package mimounot;

import mimounot.business.*;

import static mimounot.business.ReturnValue.*;

import mimounot.data.DBConnector;
import mimounot.data.PostgreSQLErrorCodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
//        createTables();
//        Mimouna new_mimouna = Mimouna.badMimouna();
//        new_mimouna.setCity("haifa");
//        new_mimouna.setFamilyName("peretz");
//        new_mimouna.setGuestCount(0);
//        new_mimouna.setPoliticianComing(false);
//        new_mimouna.setId(1);
//        new_mimouna.setUserName("nir");
//        addMimouna(new_mimouna);
        Mimouna nir_mimouna = getMimouna(1);
        System.out.println(deleteMimouna(nir_mimouna));
        //dropTables();
        //clearTables();
    }

    private static boolean mimounaExist(Integer mimouna_id) throws SQLException{
        if(mimouna_id==null) return false;
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt=null;
        try {
            pstmt = connection.prepareStatement("SELECT COUNT(mimouna_id) FROM Mimouna WHERE mimouna_id = ?");
            pstmt.setInt(1, mimouna_id);
            ResultSet results = pstmt.executeQuery();
            results.next();
            if (results.getInt(1) == 0) {
                results.close();
                return false;
            }
            results.close();
        } catch (SQLException e) {
            throw e;
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                connection.close();
            } catch (SQLException e) {
                throw e;
            }
        }
        return true;
    }

    public static void createTables(){
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {

            pstmt = connection.prepareStatement("" +
                    "CREATE TABLE Users\n" +
                    "(\n" +
                    "    user_id integer,\n" +
                    "    user_name text NOT NULL,\n" +
                    "    city text NOT NULL,\n" +
                    "    politician boolean NOT NULL,\n" +
                    "    PRIMARY KEY (user_id),\n" +
                    "    CHECK (user_id > 0)\n" +
                    ") ");
            pstmt.execute();

            pstmt = connection.prepareStatement("" +
                    "CREATE TABLE Mimouna\n"+
                    "(\n" +
                    "   mimouna_id integer,\n" +
                    "   user_name text NOT NULL,\n" +
                    "   family_name text NOT NULL,\n" +
                    "   city text NOT NULL,\n" +
                    "   guests_counter integer,\n" +
                    "   is_politician_coming boolean,\n" +
                    "   PRIMARY KEY (mimouna_id),\n" +
                    "   CHECK (mimouna_id > 0),\n" +
                    "   CHECK (guests_counter >= 0)\n" +
                    ") ");
            pstmt.execute();

            pstmt = connection.prepareStatement("" +
                    "CREATE TABLE MimounaList\n"+
                    "(\n" +
                    "   mimouna_list_id integer,\n" +
                    "   city text NOT NULL,\n" +
                    "   PRIMARY KEY (mimouna_list_id),\n" +
                    "   CHECK (mimouna_list_id > 0)\n" +
                    ") ");
            pstmt.execute();

            pstmt = connection.prepareStatement("" +
                    "CREATE TABLE ConfirmAttendance\n"+
                    "(\n" +
                    "   user_id integer,\n" +
                    "   mimouna_id integer,\n" +
                    "   PRIMARY KEY (user_id, mimouna_id),\n" +
                    "   FOREIGN KEY(user_id)" +
                    "       REFERENCES Users (user_id)\n" +
                    "       ON UPDATE CASCADE\n" +
                    "       ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (mimouna_id)\n" +
                    "        REFERENCES Mimouna (mimouna_id)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ") ");
            pstmt.execute();

            pstmt = connection.prepareStatement("" +
                    "CREATE TABLE FollowAfter\n"+
                    "(\n" +
                    "   user_id integer,\n" +
                    "   mimouna_list_id integer,\n" +
                    "   PRIMARY KEY (user_id, mimouna_list_id),\n" +
                    "   FOREIGN KEY(user_id)" +
                    "       REFERENCES Users (user_id)\n" +
                    "       ON UPDATE CASCADE\n" +
                    "       ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (mimouna_list_id)\n" +
                    "        REFERENCES MimounaList (mimouna_list_id)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ") ");
            pstmt.execute();

            pstmt = connection.prepareStatement("" +
                    "CREATE TABLE MimounaInMimounaList\n"+
                    "(\n" +
                    "   mimouna_id integer,\n" +
                    "   mimouna_list_id integer,\n" +
                    "   PRIMARY KEY (mimouna_id, mimouna_list_id),\n" +
                    "   FOREIGN KEY(mimouna_id)" +
                    "       REFERENCES Mimouna (mimouna_id)\n" +
                    "       ON UPDATE CASCADE\n" +
                    "       ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (mimouna_list_id)\n" +
                    "        REFERENCES MimounaList (mimouna_list_id)\n" +
                    "        ON UPDATE CASCADE\n" +
                    "        ON DELETE CASCADE\n" +
                    ") ");
            pstmt.execute();

        } catch (SQLException e) {
            e.getErrorCode();
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void clearTables() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM ConfirmAttendance ");
            pstmt.execute();
            pstmt = connection.prepareStatement("DELETE FROM FollowAfter ");
            pstmt.execute();
            pstmt = connection.prepareStatement("DELETE FROM MimounaInMimounaList ");
            pstmt.execute();
            pstmt = connection.prepareStatement("DELETE FROM Users ");
            pstmt.execute();
            pstmt = connection.prepareStatement("DELETE FROM Mimouna ");
            pstmt.execute();
            pstmt = connection.prepareStatement("DELETE FROM MimounaList ");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void dropTables() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Users CASCADE");
            pstmt.execute();
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Mimouna CASCADE");
            pstmt.execute();
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS MimounaList CASCADE");
            pstmt.execute();
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS ConfirmAttendance ");
            pstmt.execute();
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS FollowAfter");
            pstmt.execute();
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS MimounaInMimounaList");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

      private static boolean userNotExist(Integer userId) throws SQLException{
        if(userId==null) return true;
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt=null;
        try {
            pstmt = connection.prepareStatement("SELECT COUNT(user_id) FROM Users WHERE user_id= ?");
            pstmt.setInt(1, userId);
            ResultSet results = pstmt.executeQuery();
            results.next();
            if (results.getInt(1) == 0) {
                results.close();
                return true;
            }
            results.close();
        } catch (SQLException e) {
            throw e;
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                connection.close();
            } catch (SQLException e) {
                throw e;
            }
        }
        return false;

    }

    private static boolean CheckIfUserExist(Integer userId) throws SQLException{
        try{
            if(!userNotExist(userId)) { return true;}
            return false;
        } catch (SQLException e) {
            throw e;
        }
    }

    public static ReturnValue addUser(User user) {
        Connection connection = DBConnector.getConnection();
        if( user.getId()<=0 || user.getName()==null || user.getCity()==null )
        { return BAD_PARAMS; }
        PreparedStatement pstmt = null;
        try {
            if(CheckIfUserExist(user.getId())) { return ALREADY_EXISTS ;}

            pstmt = connection.prepareStatement("INSERT INTO Users" +
                    " VALUES (?, ?, ?, ?)");
            pstmt.setInt(1,user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3,user.getCity());
            pstmt.setBoolean(4,user.getPolitician());
            pstmt.execute();

        } catch (SQLException e) {
            return ERROR;
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                connection.close();
            } catch (SQLException e) {
                return ERROR;
            }
        }
        return OK;
    }

    public static User getUserProfile(Integer userId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        User user=new User();
        if(userId==null) { return User.badUser(); }
        try {
            pstmt = connection.prepareStatement("SELECT * FROM Users WHERE user_id= ?");
            pstmt.setInt(1, userId);
            ResultSet results = pstmt.executeQuery();
            results.next();
            if(results.getInt(1)==0)   //if the value is SQL NULL, the value returned is 0
            {
                results.close();
                return User.badUser();
            }
            user.setId(results.getInt(1));
            user.setName(results.getString(2));
            user.setCity(results.getString(3));
            user.setPolitician(results.getBoolean(4));
            results.close();

        } catch (SQLException e) {
            return User.badUser();
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return user;
    }

    public static ReturnValue deleteUser(User user) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            if(userNotExist(user.getId())) return NOT_EXISTS;

            pstmt = connection.prepareStatement("DELETE FROM Users WHERE user_id= ? ");
            pstmt.setInt(1, user.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            return ERROR;
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                connection.close();
            } catch (SQLException e) {
                return ERROR;
            }
        }
        return OK;
    }


    public static ReturnValue addMimouna(Mimouna mimouna) {
        if( mimouna.getId()<=0 || mimouna.getUserName()==null || mimouna.getFamilyName()==null ||
                mimouna.getCity()==null || mimouna.getGuestCount()!=0 || mimouna.getIsPoliticianComing()==true){
            return BAD_PARAMS;
        }
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            if(mimounaExist(mimouna.getId())){
               return ALREADY_EXISTS;
            }
            pstmt = connection.prepareStatement("INSERT INTO Mimouna" +
                    " VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1,mimouna.getId());
            pstmt.setString(2, mimouna.getUserName());
            pstmt.setString(3,mimouna.getFamilyName());
            pstmt.setString(4,mimouna.getCity());
            pstmt.setInt(5,mimouna.getGuestCount());
            pstmt.setBoolean(6,mimouna.getIsPoliticianComing());
            pstmt.execute();
        } catch (SQLException e) {
            return ERROR;
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                return ERROR;
            }
            try {
                connection.close();
            } catch (SQLException e) {
                return ERROR;
            }
        }
        return OK;
    }

    public static Mimouna getMimouna(Integer mimounaId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        Mimouna mimouna=new Mimouna();
        if(mimounaId==null) { return Mimouna.badMimouna(); }
        try {
            pstmt = connection.prepareStatement("SELECT * FROM Mimouna WHERE mimouna_id= ?");
            pstmt.setInt(1, mimounaId);
            ResultSet results = pstmt.executeQuery();
            results.next();
            if(results.getInt(1)==0)
            {
                results.close();
                return Mimouna.badMimouna();
            }
            mimouna.setId(results.getInt(1));
            mimouna.setUserName(results.getString(2));
            mimouna.setFamilyName(results.getString(3));
            mimouna.setCity(results.getString(4));
            mimouna.setGuestCount(results.getInt(5));
            mimouna.setPoliticianComing(results.getBoolean(6));
            results.close();
        } catch (SQLException e) {
            return Mimouna.badMimouna();
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                connection.close();
            } catch (SQLException e) {
                return Mimouna.badMimouna();
            }
        }
        return mimouna;
    }

    public static ReturnValue deleteMimouna(Mimouna mimouna) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            if(!mimounaExist(mimouna.getId())) return NOT_EXISTS;
            pstmt = connection.prepareStatement("DELETE FROM Mimouna WHERE mimouna_id= ? ");
            pstmt.setInt(1, mimouna.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            return ERROR;
        }
        finally {
            try {
                if(pstmt!=null) {
                    pstmt.close();
                }
                connection.close();
            } catch (SQLException e) {
                return ERROR;
            }
        }
        return OK;
    }

    public static ReturnValue addMimounalist(MimounaList mimounaList) {
        return null;
    }

    public static MimounaList getMimounalist(Integer mimounalistId) {
        return null;
    }

    public static ReturnValue deleteMimounalist(MimounaList mimounalist) {
        return null;
    }

    public static ReturnValue attendMimouna(Integer mimounaId, Integer guests){
        return null;
    }

    public static ReturnValue confirmAttendancePoliticianToMimouna(Integer mimounaId, Integer userId){
        return null;
    }

    public static ReturnValue addMimounaToMimounalist(Integer mimounaId, Integer mimounalistId){
        return null;
    }

    public static ReturnValue removeMimounaFromMimounalist(Integer mimounaId, Integer mimounalistId){
        return null;
    }

    public static ReturnValue followMimounalist(Integer userId, Integer mimounalistId){
        return null;
    }

    public static ReturnValue stopFollowMimounalist(Integer userId, Integer mimounalistId){
        return null;
    }

    public static Integer getMimounalistTotalGuests(Integer mimounalistId){
        return null;
    }

    public static Integer getMimounalistFollowersCount(Integer mimounalistId){
        return null;
    }

    public static String getMostKnownMimouna(){
        return null;
    }

    public static Integer getMostPopularMimounalist(){
        return null;
    }

    public static ArrayList<Integer> getMostRatedMimounaList(){
        return null;
    }

    public static ArrayList<Integer> getCloseUsers(Integer userId){
        return null;
    }

    public static ArrayList<Integer> getMimounaListRecommendation (Integer userId){
        return null;
    }

    public static ArrayList<Integer> getTopPoliticianMimounaList(Integer userId) {
        return null;
    }

}

