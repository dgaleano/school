/**
 * CS12420 Group Project (Java Quiz) - AdminLogin.java
 *
 * @author Nick Bolton (njb4@aber.ac.uk)
 * @version 1.0 Beta
 *
 * This is a small class which allows us to authenticate a user, once the user
 * has been authenticated, the AdminGUI class will be called from here.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AdminLogin extends JFrame {
	
	/**
	 * Tells the super class if login was succesfull.
	 */
	boolean isGood = false;
	
	/**
	 * Username and password for system.
	 */
	String userName = "admin";
	String passWord = "1234";
	
	/**
	 * Definitions for Categories Panel.
	 */
	JTextField userNameJT = new JTextField(20);
	JTextField passWordJT = new JTextField(20);
	JButton continueJB = new JButton("Continue");
	
    /*
     * Define an action listener.
     */
    Listener theListener = new Listener();
    Quiz theQuiz;

	/**
	 * The default constructor will instantiate all of the panels, text boxes
	 * and drop down menus, etc, which will be used globally thoughout the
	 * program. This also populates the drop down menus with already existing
	 * data from the main Quiz program.
	 *
	 * @param theQuz The Quiz object passed from QuizGUI.
	 */
    public AdminLogin(Quiz theQuiz) {
       
       // Give the window a name.
       super("Quiz Admin Login");
       
       this.theQuiz = theQuiz;
       
       // Define some panels to be used later.
       JTabbedPane adminJT = new JTabbedPane();
       JPanel loginJP = new JPanel(new BorderLayout());
       
       // Add some tabs for easy navigation.
       adminJT.addTab("Login", loginJP);

       /**
        * ===========================
        * Login Panel
        * ---------------------------
        * Provides the user with two fields; one to enter their username and
        * the other to enter a password. Once the user clicks the continue
        * button, the login will then be processed.
        * ===========================
        */

       // Add text boxes and such.
       JPanel loginGridJP = new JPanel(new GridLayout(2,2));
       loginGridJP.add(new JLabel("Username: "));
       loginGridJP.add(userNameJT);
       loginGridJP.add(new JLabel("Password: "));
       loginGridJP.add(passWordJT);
       loginJP.add(loginGridJP, BorderLayout.CENTER);
       loginJP.add(continueJB, BorderLayout.SOUTH);
       
       // Add some listeners for the button.
       continueJB.addActionListener(theListener);

       // Add a window listener for window events.
       this.addWindowListener(new WindowHandler());
       this.getContentPane().add(adminJT);
       this.setPreferredSize(new Dimension(225, 125));
       this.pack();
       this.setLocationRelativeTo(null);
	   this.setVisible(true);
    }
    
    private void runGUI() {
	    this.setVisible(false);
    	AdminGUI theGUI = new AdminGUI(theQuiz);
    }

    /**
      * WindowHandler - listens out for user operations on the frame
      */
     public class WindowHandler extends WindowAdapter {
         public void windowClosing(WindowEvent event) {
         	exitTool();
         }
     }

    /**
     * Exit the Application.
     */
    private void exitTool() {
    	// Leave this up to Tom's code.
        // System.exit(1);
    }

    /**
     * Listen for button pressed
     */
    public class Listener implements ActionListener {

        /**
         * Depending on which button is pressed carry out action
         */
        public void actionPerformed(ActionEvent event) {
           
        	Object theSource = event.getSource();
            
            if (theSource == continueJB) {
            	processLogin();
            }
        }
    }
    
    /**
     * Once the user has clicked continue.
     */
    private void processLogin() {
    	
    	if ((userNameJT.getText().equalsIgnoreCase(userName)) &&
    		(passWordJT.getText().equalsIgnoreCase(passWord))) {
    		
    		System.out.println("Login OK!");
    		
	    	// Hide this window + show GUI.
	    	this.runGUI();
	    	
    	} else {
    		System.out.println("Login failed!");
    	}
    }
}