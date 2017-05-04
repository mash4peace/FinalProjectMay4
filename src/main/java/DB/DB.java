package DB;

import Constractor.Record;

import java.sql.*;

/**
 * Created by mash4 on 5/4/2017.
 */
public class DB {

    private static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/Mystore";
    private static final String USER = ("mash4peace");
    private static final String PASSWORDS = System.getenv("MYSQL_pw");
    //private static final String TABLE_NAME = "records";

    private static Connection conn = null;
    private static Statement statement = null;
    private ResultSet rs = null;

    public DB() {
        try {
            Class.forName(JDBC_Driver);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; " +
                    "check you have drives and classpath configured" +
                    " correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program

        }
        try {
            conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORDS);
            System.out.println("Your connection is OK");

        } catch (SQLException sqle) {
            System.out.println("Can't connect to database. " +
                    "\nIs MySQL running? " +
                    "\nHave you created the database? " +
                    "\nVerify username and password. " +
                    "\nHave you granted the right permissions to your user?");
            sqle.printStackTrace();

        }

    }



    public void saveConsgrDetails(int quantity, String artist, String title, double salePrx, Date smDAte) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORDS)){

            String addConsgnmtInfo = "INSERT INTO  records ( quantity, artist, title , salesPrx, date) VALUES ( ?, ?, ?, ?, ?) ";
            PreparedStatement addConsgrDetails = conn.prepareStatement(addConsgnmtInfo);

            addConsgrDetails.setInt(1, quantity);
            addConsgrDetails.setString(2, artist);
            addConsgrDetails.setString(3, title);
            addConsgrDetails.setDouble(4, salePrx);
            addConsgrDetails.setDate(5, smDAte);

            addConsgrDetails.execute();
            System.out.println("Added data into records table ");
            addConsgrDetails.close();
            conn.close();
        }catch (SQLException sql){
            sql.getCause();
            sql.getErrorCode();
            sql.printStackTrace();
        }
    }

    public void getConsgrWithID(Record record) {
        try(Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORDS)){
            int id = record.getConsgrID();
            String search = "Select * from records where  id = ? ";
            ResultSet rs = statement.executeQuery(search);
            System.out.println(rs);

        }catch (SQLException sql ){
            sql.getErrorCode();
            sql.getCause();
            sql.printStackTrace();
        }

    }

    public void addingConsgrInfo(Record rcd) {
        try(Connection cnn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORDS)){
            String addConsgrDetails = "INSERT INTO consigrdetails (consgrName, phone, email) VALUES ( ?, ?, ?)";
            PreparedStatement addcongsrDetailsPS = conn.prepareStatement(addConsgrDetails);

            addcongsrDetailsPS.setString(1, rcd.getConsgr_name());
            addcongsrDetailsPS.setString(2, rcd.getConsgr_phoneNumber());
            addcongsrDetailsPS.setString(3, rcd.getConsgr_Email());

            addcongsrDetailsPS.execute();
            System.out.println("Added  consigrdetails table ");

            addcongsrDetailsPS.close();
            conn.close();

        }catch (SQLException sql){
            sql.getCause();
            sql.getErrorCode();
            sql.printStackTrace();
        }
    }

    public void getConsgmnetsFromDatabase() {
        try(Connection cnn  = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORDS) ){
            String fetchAllData = " Select * From records ";
            rs = statement.executeQuery(fetchAllData);


        }catch (SQLException sql){
            sql.getErrorCode();
            sql.printStackTrace();
            sql.getCause();
        }
    }
}
