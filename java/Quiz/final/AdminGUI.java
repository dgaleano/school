/**
 * CS12420 Group Project (Java Quiz) - AdminGUI.java
 *
 * @author Nick Bolton (njb4@aber.ac.uk)
 * @version 1.0 Beta
 *
 * This class provides a GUI for a system administrator to create, update and
 * delete categories, questions and answers.
 *
 * There is one panel for each section, those bing categories, questions and
 * answers. Each section has a drop down menu at the top, this allows the user
 * to either select for example "New Category", which will indeed allow them to
 * create a new category. Once a category has been added, they can then select
 * this from the drop down menu. Once selected, the button changes to "Edit",
 * and any changes made to the text box will be applied once they click this.
 *
 * Only authorized users (i.e. those knowing the username and password) will
 * be able to use this part of the system; the public will use the Quiz class.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AdminGUI extends JFrame {
	
	/**
	 * Lets keep these nice and constant.
	 */
	String newCategoryText = "New Category";
	String newQuestionText = "New Question";
	String newAnswerText = "New Answer";
	String navSelectCategory = "Please select a category.";
	String navSelectQuestion = "Please select a question.";
	String addCategoryText = "Add Category";
	String addQuestionText = "Add Question";
	String addAnswerText = "Add Answer";
	String editCategoryText = "Update Category";
	String editQuestionText = "Update Question";
	String editAnswerText = "Update Answer";
	String noActionsText = "No Actions Available";
	
	/**
	 * Definitions for Categories Panel.
	 */
	JLabel categoryNavJL = new JLabel();
	JTextField categoryNameJT = new JTextField(20);
	JComboBox categorySelectJC = new JComboBox();
	JButton categoryRemoveJB = new JButton("Remove");
	JButton categoryAddJB = new JButton();
	
	/**
	 * Definitions for Questions Panel.
	 */
	JLabel questionNavJL = new JLabel();
	JTextField questionNameJT = new JTextField(20);
	JComboBox questionSelectJC = new JComboBox();
	JButton questionRemoveJB = new JButton("Remove");
	JButton questionAddJB = new JButton();
	
	/**
	 * Definitions for Answers Panel.
	 */
	JLabel answerNavJL = new JLabel();
	JTextField answerNameJT = new JTextField(20);
	JCheckBox answerCorrectJC = new JCheckBox("Correct");
	JComboBox answerSelectJC = new JComboBox();
	JButton answerRemoveJB = new JButton("Remove");
	JButton answerAddJB = new JButton();
	
	/**
	 * Need a few variables to see what selected
	 * indexes to use on drop down menus.
	 */
	int categorySelectIndex = 0;
	int questionSelectIndex = 0;
	int answerSelectIndex = 0;
	
    /*
     * Define the root of information (Quiz) and an action listener.
     */
    Listener theListener = new Listener();
    Quiz theQuiz;

	/**
	 * The default constructor will instantiate all of the panels, text boxes
	 * and drop down menus, etc, which will be used globally thoughout the
	 * program. This also populates the drop down menus with already existing
	 * data from the main Quiz program.
	 *
	 * @param theQuiz The Quiz object passed from QuizGUI.
	 */
    public AdminGUI(Quiz theQuiz) {
       
       // Give the window a name.
       super("Quiz Admin Panel");
       
       this.theQuiz = theQuiz;
       
       // Define some panels to be used later.
       JTabbedPane adminJT = new JTabbedPane();
       JPanel categoriesJP = new JPanel(new BorderLayout());
       JPanel questionsJP = new JPanel(new BorderLayout());
       JPanel answersJP = new JPanel(new BorderLayout());
       
       // Add some tabs for easy navigation.
       adminJT.addTab("Categories", categoriesJP);
       adminJT.addTab("Questions", questionsJP);
       adminJT.addTab("Answers", answersJP);

       /**
        * ===========================
        * Categories Panel
        * ---------------------------
        * Allows us to select an existing category from a drop down for editing,
        * or, also using the drop down we can select new category, and insert it
        * this way. If "New Category" is selected, then make all fields blank.
        * ===========================
        */
       
       // Make a list of categorys so far.
       populateCategories();
       categoryAddJB.setText(addCategoryText);
       categoryRemoveJB.setEnabled(false);
       
       // Drop down menu and navigation bar.
       JPanel categoriesNorthGridJP = new JPanel(new GridLayout(2,1));
       categoriesNorthGridJP.add(categoryNavJL);
       categoriesNorthGridJP.add(categorySelectJC);
       categoriesJP.add(categoriesNorthGridJP, BorderLayout.NORTH);

       // Add text boxes and such.
       JPanel categoriesGridJP = new JPanel(new GridLayout(2,2));
       categoriesGridJP.add(new JLabel("Value: "));
       categoriesGridJP.add(categoryNameJT);
       categoriesGridJP.add(categoryAddJB);
       categoriesGridJP.add(categoryRemoveJB);
       categoriesJP.add(categoriesGridJP, BorderLayout.CENTER);
       
       // Add some listeners for the button and dropdown.
       categorySelectJC.addActionListener(theListener);
       categoryAddJB.addActionListener(theListener);
       categoryRemoveJB.addActionListener(theListener);
       
       /**
        * ===========================
        * Questions Panel
        * ---------------------------
        * Allows us to select an existing question from a drop down for editing,
        * or, also using the drop down we can select new question, and insert it
        * this way. If "New Question" is selected, then make all fields blank.
        * ===========================
        */
       
       // Find out what category is selected, and get all answers..
       populateQuestions();
       questionAddJB.setText(addQuestionText);
       questionRemoveJB.setEnabled(false);
		       
       // Drop down menu and navigation bar.
       JPanel questionsNorthGridJP = new JPanel(new GridLayout(2,1));
       questionsNorthGridJP.add(questionNavJL);
       questionsNorthGridJP.add(questionSelectJC);
       questionsJP.add(questionsNorthGridJP, BorderLayout.NORTH);

       // Add text boxes and such.
       JPanel questionsGridJP = new JPanel(new GridLayout(2,2));
       questionsGridJP.add(new JLabel("Value: "));
       questionsGridJP.add(questionNameJT);
       questionsGridJP.add(questionAddJB);
       questionsGridJP.add(questionRemoveJB);
       questionsJP.add(questionsGridJP, BorderLayout.CENTER);
       
       // Add some listeners for the button and dropdown.
       questionSelectJC.addActionListener(theListener);
       questionAddJB.addActionListener(theListener);
       questionRemoveJB.addActionListener(theListener);

       /**
        * ===========================
        * Answers Panel
        * ---------------------------
        * Allows us to select an existing answer from a drop down for editing,
        * or, also using the drop down we can select new answer, and insert it
        * this way. If "New Answer" is selected, then make all fields blank.
        * ===========================
        */
       
       // Find out what category is selected, and get all answers..
       populateAnswers();
       answerAddJB.setText(addAnswerText);
       answerRemoveJB.setEnabled(false);
       
       // Drop down menu and navigation bar.
       JPanel answersNorthGridJP = new JPanel(new GridLayout(2,1));
       answersNorthGridJP.add(answerNavJL);
       answersNorthGridJP.add(answerSelectJC);
       answersJP.add(answersNorthGridJP, BorderLayout.NORTH);

       // Add text boxes and such.
       JPanel answersGridJP = new JPanel(new GridLayout(2,1));
       
       JPanel answersInputGridJP = new JPanel(new GridLayout(1,3));
       answersInputGridJP.add(new JLabel("Value: "));
       answersInputGridJP.add(answerNameJT);
       answersInputGridJP.add(answerCorrectJC);
       answersGridJP.add(answersInputGridJP);
       
       JPanel answersButtonsGridJP = new JPanel(new GridLayout(1,3));
       answersButtonsGridJP.add(answerAddJB);
       answersButtonsGridJP.add(answerRemoveJB);
       answersGridJP.add(answersButtonsGridJP);
       
       answersJP.add(answersGridJP, BorderLayout.CENTER);
       
       // Add some listeners for the button and dropdown.
       answerSelectJC.addActionListener(theListener);
       answerAddJB.addActionListener(theListener);
       answerRemoveJB.addActionListener(theListener);
       
       // Initialize the navigation system.
       rebuildNavigator();

       // Add a window listener for window events.
       this.addWindowListener(new WindowHandler());
       this.getContentPane().add(adminJT);
       this.setPreferredSize(new Dimension(400, 175));
       this.pack();
       this.setLocationRelativeTo(null);
       this.setVisible(true);
    }

    /**
     * Exit the Application.
     */
    private void exitTool() {
    	// Let Tom's code handle this.
        // System.exit(1);
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
     * Listen for button pressed
     */
    public class Listener implements ActionListener {

        /**
         * Depending on which button is pressed carry out action
         */
        public void actionPerformed(ActionEvent event) {
           
        	Object theSource = event.getSource();
            
            if (theSource == categorySelectJC) {
            	newCategorySelected();
		    	populateQuestions();
		    	populateAnswers();
            }
            
            if (theSource == categoryAddJB) {
            	setCurrentIndexes();
            	modifyCategory();
		    	populateCategories();
		    	populateQuestions();
		    	populateAnswers();
            }
            
            if (theSource == categoryRemoveJB) {
            	removeCategory();
		    	populateCategories();
		    	populateQuestions();
		    	populateAnswers();
            }
            
            if (theSource == questionSelectJC) {
            	newQuestionSelected();
		    	populateAnswers();
            }
            
            if (theSource == questionAddJB) {
            	setCurrentIndexes();
            	modifyQuestion();
		    	populateQuestions();
		    	populateAnswers();
            }
            
            if (theSource == questionRemoveJB) {
            	removeQuestion();
		    	populateQuestions();
		    	populateAnswers();
            }
            
            if (theSource == answerSelectJC) {
            	newAnswerSelected();
            }
            
            if (theSource == answerAddJB) {
            	setCurrentIndexes();
            	modifyAnswer();
		    	populateAnswers();
            }
            
            if (theSource == answerRemoveJB) {
            	removeAnswer();
		    	populateAnswers();
            }
            
            rebuildNavigator();
        }
    }
    
    /**
     * Store current indexes for important dropdowns.
     */
    private void setCurrentIndexes() {
    	
        // Remeber the current selected indexs before they're rebuilt.
		categorySelectIndex = categorySelectJC.getSelectedIndex();
		questionSelectIndex = questionSelectJC.getSelectedIndex();
		answerSelectIndex = answerSelectJC.getSelectedIndex();
    }
    
    /**
     * If the user has selected "new category".
     */
    private void newCategorySelected() {
    	
    	try {
        	Category selectedCat = (Category)categorySelectJC.getSelectedItem();
        	
        	if ((selectedCat.getValue() == newCategoryText) ||
        		(selectedCat.getValue() == noActionsText)) {
        		
        		resetCategoryFiels();
	        	
        	} else {
        		categoryNameJT.setText(selectedCat.getValue());
        		categoryAddJB.setText(editCategoryText);
	        	categoryRemoveJB.setEnabled(true);
        	}
        	
        } catch (NullPointerException e) {
    		// Menu is empty or not yet populated.
    		
    	} catch (ClassCastException e) {
    		// May be a text string instead of an item.
    	}
    }
    
    /**
     * If the user has selected "new category".
     */
    private void modifyCategory() {
		 
		// Bail out for empty text boxes.
		if (categoryNameJT.getText().equalsIgnoreCase("")) return;
		
    	try {
        	
        	Category selectedCat = (Category)categorySelectJC.getSelectedItem();
        	
        	if ((selectedCat.getValue() == newCategoryText) ||
        		(selectedCat.getValue() == noActionsText)) {          		
        		
        		theQuiz.addItem(new Category(categoryNameJT.getText()));
        		categoryNameJT.setText("");
        		this.categorySelectIndex = categorySelectJC.getItemCount();
        		
        	} else {
        		selectedCat.setValue(categoryNameJT.getText());
        	}
        	
    	} catch (NullPointerException e) {
    		// Menu is empty or not yet populated.
    		
    	} catch (ClassCastException e) {
    		// May be a text string instead of an item.
    	}
    	
    	
    }
    
    /**
     * Removes a Category.
     */
    private void removeCategory() {
    	Category theCategory = (Category)categorySelectJC.getSelectedItem();
    	theQuiz.removeItem(theCategory);
    	this.categorySelectIndex = 0;
    }
    
    /**
     * If the user has selected "new question".
     */
    private void newQuestionSelected() {
    	
    	try {
        	Question selectedQue = (Question)questionSelectJC.getSelectedItem();
        	
        	if ((selectedQue.getValue() == newQuestionText) ||
        		(selectedQue.getValue() == noActionsText)) {
        		
        		resetQuestionFiels();
        		
        	} else {
        		questionNameJT.setText(selectedQue.getValue());
        		questionAddJB.setText(editQuestionText);
	        	questionRemoveJB.setEnabled(true);
        	}
        	
        } catch (NullPointerException e) {
    		// Menu is empty or not yet populated.
    		
    	} catch (ClassCastException e) {
    		// May be a text string instead of an item.
    	}
    }
    
    /**
     * If the user has modified / added a question.
     */
    private void modifyQuestion() {
    	
	    // Bail out for empty text boxes.
		if (questionNameJT.getText().equalsIgnoreCase("")) return;
    	
    	try {
        	Category selectedCat = (Category)categorySelectJC.getSelectedItem();
        	Question selectedQue = (Question)questionSelectJC.getSelectedItem();
        		            	
        	if ((selectedQue.getValue() == newQuestionText) ||
        		(selectedQue.getValue() == noActionsText)) {
        		
        		selectedCat.addItem(new Question(questionNameJT.getText()));
        		questionNameJT.setText("");
				this.questionSelectIndex = questionSelectJC.getItemCount();
        		
        	} else {
        		selectedQue.setValue(questionNameJT.getText());
        	}
        	
    	} catch (NullPointerException e) {
    		// Menu is empty or not yet populated.
    		
    	} catch (ClassCastException e) {
    		// May be a text string instead of an item.
    	}
		
    }
    
    /**
     * Removes a Question.
     */
    private void removeQuestion() {
    	Category theCategory = (Category)categorySelectJC.getSelectedItem();
    	Question theQuestion = (Question)questionSelectJC.getSelectedItem();
    	theCategory.removeItem(theQuestion);
    	this.questionSelectIndex = 0;
    }
    
    /**
     * If the user has selected "new answer".
     */
    private void newAnswerSelected() {
    	try {
        	Answer selectedAns = (Answer)answerSelectJC.getSelectedItem();
        	
        	if ((selectedAns.getValue() == newAnswerText) ||
        		(selectedAns.getValue() == noActionsText)) {
        		
        		resetAnswerFiels();
	        	
        	} else {
        		answerNameJT.setText(selectedAns.getValue());
        		answerCorrectJC.setSelected(selectedAns.getCorrect());
        		answerAddJB.setText(editAnswerText);
	        	answerRemoveJB.setEnabled(true);
        	}
        	
        } catch (NullPointerException e) {
    		// Menu is empty or not yet populated.
    		
    	} catch (ClassCastException e) {
    		// May be a text string instead of an item.
    	}
    }
    
    /**
     * If the user wants to modify / add a question.
     */
    private void modifyAnswer() {
    	
    	try {
        	
        	Question selectedQue = (Question)questionSelectJC.getSelectedItem();
        	Answer selectedAns = (Answer)answerSelectJC.getSelectedItem();
        	
        	if (answerCorrectJC.isSelected()) {
    			setAnswersIncorrect(selectedQue);
    		}
        		            	
        	if ((selectedAns.getValue() == newAnswerText) ||
        		(selectedAns.getValue() == noActionsText)) {
        		
        		selectedQue.addItem(new Answer(answerNameJT.getText(),
        			answerCorrectJC.isSelected()));
        		
        	} else {
        		selectedAns.setValue(answerNameJT.getText());
        		selectedAns.setCorrect(answerCorrectJC.isSelected());
        	}
        	
    		answerCorrectJC.setSelected(false);
    		answerNameJT.setText("");
        	
    	} catch (NullPointerException e) {
    		// Menu is empty or not yet populated.
    		
    	} catch (ClassCastException e) {
    		// May be a text string instead of an item.
    	}
    }
    
    /**
     * Removes a Question.
     */
    private void removeAnswer() {
    	Question theQuestion = (Question)questionSelectJC.getSelectedItem();
    	Answer theAnswer = (Answer)answerSelectJC.getSelectedItem();
    	theQuestion.removeItem(theAnswer);
    	this.answerSelectIndex = 0;
    }
    
    /**
     * Reset all the fields in the category tab to default.
     */
    private void resetCategoryFiels() {
    	categoryNameJT.setText("");
		categoryAddJB.setText(addCategoryText);
    	categoryRemoveJB.setEnabled(false);
    	resetQuestionFiels();
    }
    
    /**
     * Reset all the fields in the question tab to default.
     */
    private void resetQuestionFiels() {
    	questionNameJT.setText("");
		questionAddJB.setText(addQuestionText);
    	questionRemoveJB.setEnabled(false);
    	resetAnswerFiels();
	}
    
    /**
     * Reset all the fields in the answer tab to default.
     */
    private void resetAnswerFiels() {
    	answerNameJT.setText("");
		answerCorrectJC.setSelected(false);
		answerAddJB.setText(addAnswerText);
    	answerRemoveJB.setEnabled(false);
    }
    
    /**
     * Sets all the answers to incorrect, so the new correct answer is the
     * only correct answer.
     *
     * @param theQuestion Takes the question which we want to deal with.
     */
    private void setAnswersIncorrect(Question theQuestion) {
    	
    	Answer theAnswer, newAnswer;
    	Iterator<Answer> iter = theQuestion.iterator();
    	
    	while (iter.hasNext()) {
    		theAnswer = iter.next();
    		theAnswer.setCorrect(false);
    	}
    }
    
    /**
     * When the button is actually pressed.
     */
    private void populateCategories() {
    	
	    // Start with a new list.
	    if (categorySelectJC.getItemCount() > 0) {
	    	categorySelectJC.removeAllItems();
	    }
	    
	    // When this item is selected, we are in "Add" mode.
    	categorySelectJC.addItem(new Category(newCategoryText));
    	
    	Iterator<Category> iter = theQuiz.iterator();
    	while (iter.hasNext()) {
    		categorySelectJC.addItem(iter.next());
    	}
    	
    	categorySelectJC.setSelectedIndex(this.categorySelectIndex);
    }
    
    /**
     * Fills the questions combo box width data.
     */
    private void populateQuestions() {
    	
    	Category theCategory;
    	
	    if (questionSelectJC.getItemCount() > 0) {
	    	questionSelectJC.removeAllItems();
	    }
	    
	    try {
	    	theCategory = (Category)categorySelectJC.getSelectedItem();
	    	
	    	if ((theCategory.toString() == newCategoryText) ||
	    		(theCategory.toString() == noActionsText)) {
		    	questionSelectJC.addItem(new Question(noActionsText));
		    	return;
		    }
	    	
	    } catch (NullPointerException e) {
    		// Menu is empty or not yet populated.
    		return;
    	}
	    
	    // When this item is selected, we are in "Add" mode.
    	questionSelectJC.addItem(new Question(newQuestionText));
    	
    	Iterator<Question> iter = theCategory.iterator();
    	while (iter.hasNext()) {
    		questionSelectJC.addItem(iter.next());
    	}
    	
    	questionSelectJC.setSelectedIndex(this.questionSelectIndex);
    }
    
    /**
     * Fills the answers combo box width data.
     */
    private void populateAnswers() {
    	
    	Question theQuestion;
    	
	    if (answerSelectJC.getItemCount() > 0) {
	    	answerSelectJC.removeAllItems();
	    }
	    
	    try {
	    	theQuestion = (Question)questionSelectJC.getSelectedItem();
	    	
	    	if ((theQuestion.toString() == newQuestionText) ||
	    		(theQuestion.toString() == noActionsText)) {
		    	answerSelectJC.addItem(new Question(noActionsText));
		    	return;
		    }
	    	
	    } catch (NullPointerException e) {
    		// Menu is empty or not yet populated.
    		return;
    	}
	    
	    // When this item is selected, we are in "Add" mode.
    	answerSelectJC.addItem(new Answer(newAnswerText));
    	
    	Iterator<Answer> iter = theQuestion.iterator();
    	while (iter.hasNext()) {
    		answerSelectJC.addItem(iter.next());
    	}
    	
    	answerSelectJC.setSelectedIndex(this.answerSelectIndex);
    }
    
    /**
     * Rebuilds the navigator bar at the top of each page.
     */
    private void rebuildNavigator() {
	    
	    // Set these now incase an exception is caused later.
	    categoryNavJL.setText(navSelectCategory);
	    questionNavJL.setText(navSelectCategory);
	    answerNavJL.setText(navSelectQuestion);
    	
    	Category selectedCat;
    	Question selectedQue;
    	Answer selectedAns;
    	
    	try {
    		// Find out where all the dropdown menus are ate.
    		selectedCat = (Category)categorySelectJC.getSelectedItem();
	    	selectedQue = (Question)questionSelectJC.getSelectedItem();
    		
	    	if ((selectedCat.toString() != newCategoryText) &&
	    		(selectedCat.toString() != noActionsText)) {
	    		
	    		// If there is a real category selected.
	    		questionNavJL.setText(selectedCat + " > Edit Questions");
	    		answerNavJL.setText(navSelectQuestion);
	    	}
	    	
	    	if ((selectedQue.toString() != newQuestionText) &&
	    		(selectedQue.toString() != noActionsText)) {
	    		
	    		// If there is a real question selected.
	    		answerNavJL.setText(selectedCat + " > " + selectedQue
	    			+ " > Edit Answers");
	    	}
		    
    	} catch (NullPointerException e) {
    		// One or more menus were empty at some point.
    		return;
    		
    	}
    }
}