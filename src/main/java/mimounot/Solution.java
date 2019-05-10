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

    public static void createTables(){

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

