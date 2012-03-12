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
    //Logik logik;

    public Vidmot()
    {
        // Initialize components.
        frame = new JFrame("SimpleCalendar");
        textBox = new JTextArea();
        cal = new JCalendar();
        // initialize date
        loaded = false;
        // initialize logik

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
        // Interact with logik.
    }

    private class closeEvent extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
                // Interact with logik.
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

