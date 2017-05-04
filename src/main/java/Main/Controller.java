package Main;

import Constractor.Record;
import DB.DB;
import StoreGUI.MainWindow;

import java.sql.Date;

/**
 * Created by mash4 on 5/4/2017.
 */
public class Controller {
     static DB db ;
     static Record record;
    public static void main(String[] args) {
        MainWindow m = new MainWindow();
        db = new DB();

    }

    public static void getDateFromDatabase() {

    }



    public static void saveConsgrInfo(int quantity, String artist, String title, double salePrx, Date smDAte) {
        db.saveConsgrDetails(quantity, artist, title, salePrx, smDAte);
    }

    public static void getIDfromDatabase(int id) {
        db.getConsgrWithID(record);
    }

    public static void addConsignrDetails(Record rcd) {
        db.addingConsgrInfo(rcd);
    }

    public static void getAllDataFromDB() {
        db.getConsgmnetsFromDatabase();
    }
}
