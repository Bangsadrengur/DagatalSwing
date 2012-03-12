import is.hi.snorri.transport.Value;
import java.util.Date;
import java.util.Formatter;
import java.util.Calendar;
import java.io.*;

public class Logik
{
    // Data decleration:
    // days is a table mapping between dates and text.
    // path points to folder containted in 
    // environment variable USERPROFILE. For linux
    // you need to set this variable.
    Value days;
    String path;

    // Initialize variables.
    public Logik()
    {
        days = null;
        path = System.getenv("USERPROFILE")+"\\Diary.dat";
    }

    // Loads Value table from file at path if it exists,
    // else it returns a new Value table.
    public void loadDays()
    {
        try
        {
            days = Value.loadFile(path);
        } catch(Exception e) {
            days = Value.makeTable();
        }
    }

    // Saves Value table to file at path.
    public void saveDays()
        throws FileNotFoundException, IOException
    {
        Value.saveFile(days, path);
    }

    // Get text that date points to in Value table
    // days. If date points to a null value an empty
    // string is returned.
    public String getDateText(String date)
    {
        Value res = days.get(Value.makeString(date));
        if(res == null) return "";
        return res.asString();
    }

    // Update Value table at date with text.
    // Does nothing if the input is the same as 
    // earlier input in days.
    // Sets value to null if input is an empty string.
    // Returns a bool value corresponding to if the
    // value was changed in days or not.
    public boolean setDateText(String date, String text)
    {
        String oldText = getDateText(date);
        if(text.equals(oldText)) return false;
        Value newVal = null;
        if(!text.equals("")) newVal = Value.makeString(text);
        days.put(Value.makeString(date), newVal);
        return true;
    }

    // Creates a string of the form YYYYMMDD that matches
    // the inserted day date.
    public String dateToString(Date date)
    {
        StringBuilder b = new StringBuilder();
        Formatter f = new Formatter(b);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        f.format( "%04d%02d%02d"
                , c.get(Calendar.YEAR)
                , c.get(Calendar.MONTH) + 1
                , c.get(Calendar.DAY_OF_MONTH)
                );
        return b.toString();
    }
}
