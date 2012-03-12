import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Vidmot
{
    JFrame frame;
    JTextArea textBox;
    JCalendar cal;
    String date;
    boolean loaded;
    Logik logik;

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

    private class closeEvent extends WindowAdapter
    {
        public void windowClosing(WindowEvent evt)
        {
            try
            {
                logik.saveDays();
            } catch(Exception e) {}
            System.exit(0);
        }
    }

    private class dateChanged implements PropertyChangeListener
    {
        public void propertyChange(PropertyChangeEvent evt)
        {
            changeDay();
        }
    }

    public static void main(String[] args)
    {
        new Vidmot();
    }
}

