import java.awt.*;
import java.awt.event.*;
import javax.swing.*;     // Bring in the Swing classes
import java.io.*;         // Required for standard input from keyboard


public class GraphicExample2 extends JFrame
{

    private JTextField userInput = new JTextField("Your input goes here");
    /**
     * Constructor to create a new Example1 AWT
     */
    public GraphicExample2()
    {
        super("This is example 3");

        setBounds(50,50,300,300);   // x, y offset, width, height

        getContentPane().setBackground(Color.orange);

        getContentPane().add(new JLabel("CS12420"));

        userInput.setBackground(Color.blue);
        getContentPane().add(userInput);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setString(String theData)
    {
        userInput.setText(theData);
    }

    public static void main (String [] args)
        throws IOException
    {
        String theString;

        GraphicExample2 thirdWindow = new GraphicExample2();

		thirdWindow.setVisible(true);

        BufferedReader stdin =  new BufferedReader(
                                 new InputStreamReader(System.in));

        System.out.println("Enter your string now please");

        theString = stdin.readLine();  // throws IOException

        thirdWindow.setString(theString);

    }
}