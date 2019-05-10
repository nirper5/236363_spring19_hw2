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
        createTables();
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


//            pstmt = connection.prepareStatement("" +
//                    "CREATE TABLE Mimouna\n" +
//                    "(\n" +
//                    "    mimouna_id integer,\n" +
//                    "    user_name text NOT NULL,\n" +
//                    " family_name text NOT NULL,\n" +
//                    "city text NOT NULL,\n" +
//                    "    guests_counter integer,\n" +
//                    "    is_politician_coming boolean,\n"+
//                    "    PRIMARY KEY (mimouna_id),\n" +
//                    "    CHECK (mimouna_id > 0),\n" +
//                    "    CHECK (guests_counter >= 0),\n"+
//                    ") ");
//            pstmt.execute();
//            pstmt = connection.prepareStatement("" +
//                    "CREATE TABLE Playlists\n" +
//                    "(\n" +
//                    "    playlist_id integer,\n" +
//                    "    genre text NOT NULL,\n" +
//                    "    description text NOT NULL,\n" +
//                    "    PRIMARY KEY (playlist_id),\n" +
//                    "    CHECK (playlist_id > 0)\n" +
//                    ") ");
//            pstmt.execute();


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

    }

    public static void dropTables() {

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

