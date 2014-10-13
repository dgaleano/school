import java.awt.*;        // Bring in the necessary classes from the AWT
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class QuizGUI extends JFrame{

        Quiz theQuiz = new Quiz();


        /*
        *  Quiz Panel
        */

        JButton nextQuestionJB = new JButton("Next");
        JButton refreshJB = new JButton("Refresh");
        JTextField questionJT = new JTextField(50);

        String selectedCategory = "Select Category";
        JButton adminJB = new JButton("Admin");
        JComboBox selectCatJC = new JComboBox();



        public QuizGUI() {
        super("TEH PUB QUIZLE!1");

        // create a listener called controller
        Listener controller = new Listener();

        JTabbedPane quizJT = new JTabbedPane();



        JPanel quizJP = new JPanel(new GridLayout(3,2));
        JPanel actualQuizJP = new JPanel(new GridLayout(3,20));



        quizJT.addTab("Categories", quizJP);
        quizJT.addTab("Quiz", actualQuizJP);


        /***
	* The Quiz Tab
        ***/


        getCategories();
	quizJP.add(refreshJB);

        refreshJB.addActionListener(controller);

        quizJP.add(selectCatJC);
        selectCatJC.addActionListener(controller);

        quizJP.add(adminJB);
        adminJB.addActionListener(controller);
       

       /***
       * The bit where the questions are displayed
       ***/

       actualQuizJP.add(new JLabel("Question 1)"));
       actualQuizJP.add(questionJT);
       
       // create a window handler to actually display the GUI
        this.addWindowListener(new WindowHandler());
       getContentPane().add(quizJT);
       this.pack();
       this.setLocationRelativeTo(null);
       setVisible(true);


        }

           public class WindowHandler extends WindowAdapter
     {
         public void windowClosing(WindowEvent event)
         {
             System.exit(1);
         }
     }



         public class Listener implements ActionListener
    {

	public void actionPerformed(ActionEvent event)
    {
            	Object theSource = event.getSource();

                if(theSource == selectCatJC) {
                getQuestions();
                }

                if(theSource == adminJB) {
                new AdminLogin(theQuiz);
                }

                if(theSource == refreshJB) {
                getCategories();
                }





                if(theSource == nextQuestionJB)
                {
                System.out.println("Category has been selected... moving on");
                //questionJT.setText(theQuestion.toString());
    		}




    }
    }

        public static void main( String args[]) {
                new QuizGUI();



        }

            private void getCategories() {


    	//selectCatJC.addItem(new Category(newCategoryText));

    	Iterator<Category> iter = theQuiz.iterator();
    	while (iter.hasNext()) {
    		selectCatJC.addItem(iter.next());
    	}


    }

        private void getQuestions() {

        Category theCategory = (Category)selectCatJC.getSelectedItem();
        Iterator<Question> iter = theCategory.iterator();
        while(iter.hasNext()) {
                questionJT.setText("why aren't you working");
                }
                }


}
