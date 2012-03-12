import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.*;
import java.util.Date;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Vidmot
{
    JFrame frame;
    JTextArea textBox;
    JCalendar cal;
    Logik1 logik;
    String date;
    boolean loaded;

    public Vidmot()
    {
        // Initialize components.
        frame = new JFrame("SimpleCalendar");
        textBox = new JTextArea();
        cal = new JCalendar();
        logik = new Logik1();
        date = logik.dateToString(cal.getDate());
        loaded = false;

        // Set events.
        frame.addWindowListener(new closeEvent());
        cal.addPropertyChangeListener(new dateChanged());

        // Set attributes.
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setLayout(new BorderLayout());

        // Add widgets to JFrame.
        frame.add(cal, BorderLayout.NORTH);
        frame.add(textBox, BorderLayout.CENTER);

        // Pass sizing of window to Swing.
        frame.pack();

        // Set parts visible.
        cal.setVisible(true);
        textBox.setVisible(true);
        frame.setVisible(true);

        //initDays();
    }

    private void initDays()
        throws NullPointerException
    {
        // Load days table and initialize first
        // entry apperance.
        logik.loadDays();
        String tmp = logik.getDateText(date);
        textBox.setText(tmp);
    }

    private void changeDay()
        throws NullPointerException
    {
        if(!loaded)
        {
            logik.loadDays();
            textBox.setText(logik.getDateText(date));
            loaded = true;
        }
        String tmp = textBox.getText();
        logik.setDateText(date, tmp);
        boolean changed = 
            logik.setDateText(date, textBox.getText());
        date = logik.dateToString(cal.getDate());
        textBox.setText(logik.getDateText(date));
    }

    private class closeEvent extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            try
            {
                logik.saveDays();
            } catch(java.io.FileNotFoundException f) {
            } catch(java.io.IOException i) {}
            System.exit(0);
        }
    }

    private class dateChanged implements PropertyChangeListener
    {
        public void propertyChange(PropertyChangeEvent evt)
        {
            System.out.println("I'm changing!!");
            changeDay();
        }
    }

    public static void main(String[] args)
    {
        new Vidmot();
    }
}


