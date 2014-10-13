import java.awt.*;        // Bring in the necessary classes from the AWT
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class PlayerGUI extends JFrame {
	
	/**
	 * Lets keep these nice and constant.
	 */
	String pubQuizText = "Pub Quiz";

	/**
	 * Definitions for Player Interface.
	 */
	 JCheckBox answer1JC = new JCheckBox("InsertAnswer1Here");
	 JCheckBox answer2JC = new JCheckBox("InsertAnswer2Here");
	 JCheckBox answer3JC = new JCheckBox("InsertAnswer3Here");
	 JCheckBox answer4JC = new JCheckBox("InsertAnswer4Here");
	 JButton nextQuestionJB = new JButton("Next Qestion");
	
    
    public PlayerGUI() {
       
       super("Quiz Player Panel");
       JTabbedPane quizInterfaceJT = new JTabbedPane();
       JPanel playerJP = new JPanel(new BorderLayout());
       JPanel adminJP = new JPanel(new BorderLayout());
       quizInterfaceJT.addTab("Admin", adminJP);
       quizInterfaceJT.addTab("Player", playerJP);

    }

    /**
     * Exit the Application.
     */
    private void exitTool()
    {
        System.exit(1);
    }

    /**
      * WindowHandler - listens out for user operations on the frame
      */
     public class WindowHandler extends WindowAdapter
     {
         public void windowClosing(WindowEvent event)
         {
         	exitTool();
         }
     }
}