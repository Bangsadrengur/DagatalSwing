import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Vidmot
{
    // Data decleration:
    // frame is a java frame.
    // textBox is an input area for text.
    // cal is an interactive calander widget.
    // date holds selected date, possibly it holds 
    // the last selected date for a short while untill
    // it's text has been updated in Value table and 
    // date is set to current date.
    // loaded is set to false as long as Value table days 
    // hasn't been loaded in logik.
    // logik handles the purely logical part of this program.
    JFrame frame;
    JTextArea textBox;
    JCalendar cal;
    String date;
    boolean loaded;
    Logik logik;

    // Sets up user interface.
    public Vidmot()
    {
        // Initialize components.
        frame = new JFrame("SimpleCalendar");
        textBox = new JTextArea();
        cal = new JCalendar();
        loaded = false;
        logik = new Logik();
        date = logik.dateToString(cal.getDate());

        // Set events.
        frame.addWindowListener(new closeEvent());
        cal.addPropertyChangeListener(new dateChanged());

        // Set attributes.
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setLayout(new BorderLayout());

        // Add widgets to JFrame.
        frame.add(cal, BorderLayout.NORTH);
        frame.add(textBox, BorderLayout.CENTER);

        // Pass applet sizing to window manager.
        frame.pack();

        // Set parts visible.
        cal.setVisible(true);
        textBox.setVisible(true);
        frame.setVisible(true);
    }

    // On first call from calendar widget during construction 
    // this function loads days table and sets up the text 
    // for the current day.
    // On laters calls input from textarea is saved and 
    // text for new day is loaded to textarea.
    private void changeDay()
    {
        if(!loaded)
        {
            logik.loadDays();
            textBox.setText(logik.getDateText(date));
            loaded = true;
        }
        logik.setDateText(date, textBox.getText());
        date = logik.dateToString(cal.getDate());
        textBox.setText(logik.getDateText(date));
    }

    // closeEvent handles window close event.
    private class closeEvent extends WindowAdapter
    {
        // Saves updated calendar entries and closes
        // applet.
        public void windowClosing(WindowEvent evt)
        {
            try
            {
                logik.saveDays();
            } catch(Exception e) {}
            System.exit(0);
        }
    }

    // dateChanged handles change in days events from calendar.
    private class dateChanged implements PropertyChangeListener
    {
        // propertyChange passes functionality to changeDay();
        public void propertyChange(PropertyChangeEvent evt)
        {
            changeDay();
        }
    }

    // Start applet.
    public static void main(String[] args)
    {
        new Vidmot();
    }
}

