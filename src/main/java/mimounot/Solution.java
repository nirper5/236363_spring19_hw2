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
        //createTables();
        dropTables();

//        clearTables();
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

    public static ReturnValue addUser(User user) {
       return null;
    }

    public static User getUserProfile(Integer userId) {
      return null;
    }

    public static ReturnValue deleteUser(User user) {
       return null;
    }

    public static ReturnValue addMimouna(Mimouna mimouna) {
        return null;
    }

    public static Mimouna getMimouna(Integer mimounaId) {
        return null;
    }

    public static ReturnValue deleteMimouna(Mimouna mimouna) {
        return null;
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

