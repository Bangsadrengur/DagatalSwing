import is.hi.snorri.transport.Value;
import java.util.Date;
import java.util.Formatter;
import java.util.Calendar;
import java.io.*;

public class Logik
{
    Value days;
    String path;

    public Logik()
    {
        days = null;
        path = System.getenv("USERPROFILE")+"\\Diary.dat";
    }

    public void loadDays()
    {
        try
        {
            days = Value.loadFile(path);
        } catch(Exception e) {
            days = Value.makeTable();
        }
    }

    public void saveDays()
        throws FileNotFoundException, IOException
    {
        Value.saveFile(days, path);
    }

    public String getDateText(String date)
    {
        Value res = days.get(Value.makeString(date));
        if(res == null) return "";
        return res.asString();
    }

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
