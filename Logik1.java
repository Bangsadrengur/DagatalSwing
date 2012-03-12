import is.hi.snorri.transport.Value;
import java.util.Date;
import java.util.Formatter;
import java.util.Calendar;
import java.io.*;

public class Logik1
{
    Value days;
    File diary;

    public Logik1()
    {
        days = null;
        diary = new File("./Diary.dat");
    }

    // Sets days table to either an excisting one from Diary.dat in home folder or
    // to a blank empy one if no earlier table existed.
    public void loadDays()
    {
        try
        {
            days = Value.loadFile(diary.getPath());
        } 
        catch(Exception e) 
        {
            days = Value.makeTable();
        }
    }

    // Saves days to file Diary.dat in home folder.
    public void saveDays()
        throws FileNotFoundException, IOException
    {
        //checkFile();
        Value.saveFile(days, "./Diary.dat");
    }

    private void checkFile()
        throws IOException
    {
        if(!diary.exists())
        {
            diary.createNewFile();
            System.out.println("File " + diary.getPath() + " created.");
        }
    }


    // Returns a string with the text for a day date from table days.
    public String getDateText(String date)
    {
        if(Value.makeString(date) == null) System.out.println("ohhooo");
        Value res = days.get(Value.makeString(date));
        if(res == null) return "";
        return res.asString();
    }

    // Sets text value for day date in days to text.
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

    public static void main(String[] args)
        throws IOException
    {
        Logik1 logik = new Logik1();
        logik.loadDays();
        System.out.println(logik.getDateText("20120312"));
        logik.setDateText("20120312", "Derpity");
        System.out.println(logik.getDateText("20120312"));
        logik.saveDays();
        System.out.println(logik.diary.getPath());
    }
}
