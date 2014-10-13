import java.awt.*;        // Bring in the necessary classes from the AWT
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SurgeryGUI extends JFrame {
	
	/*
    * Definitions in the Employee Frame
    */

	JComboBox employeePositionJC;
	JButton addStaffJB = new JButton("Add Employee");
	JButton clearStaffJB = new JButton("Clear Entry");     
	JTextField addEmployeeNameJT = new JTextField(20);


   	/**
	 * Definitions in View Appointments Frame
	 */
	JComboBox viewAppointmentEmployeeJC = new JComboBox();
	JTextArea viewAppointmentsDetailsJT = new JTextArea("", 5, 10);
   
    /*
    * Definitions in the Add Appointment Frame
    */
	JTextField addAppointmentNhsNumberJT = new JTextField(20);
	JTextField addAppointmentPatientNameJT = new JTextField(20);
	JTextField addAppointmentDateDayJT = new JTextField(2);
	JTextField addAppointmentDateMonthJT = new JTextField(2);
	JTextField addAppointmentDateYearJT = new JTextField(4);
	JComboBox addAppointmentForEmployeeJC = new JComboBox();
	JComboBox addAppointmentTimeSlotJC = new JComboBox();
	JButton addAppointmentProbFindPatientJB = new JButton("Find Patient");
	JButton addAppointmentClearDetailJB = new JButton("Clear Details");
	JButton addAppoitmentBookJB = new JButton("Book Appointment");

	/*
	* Definitions in the Add patient Frame
	*/
	JTextField addPatientNameJT = new JTextField(20);
	JTextField addPatientDateOfBirthJT = new JTextField(20);
	JTextField addPatientNhsNumberJT = new JTextField(20);
	JTextField addPatientDateDayJT = new JTextField(2);
	JTextField addPatientDateMonthJT = new JTextField(2);
	JTextField addPatientDateYearJT = new JTextField(4);
	JComboBox addPatientAddAppForEmployeeJC;
	JComboBox addPatientAppointmentTimeSlotJC;
	JButton addPatientClearPatientDetailJB = new JButton("Clear Details");
	JButton addPatientAddPatientJB = new JButton("Add Patient");

	/*
     * Definitions in the HealthProblem Frame
     */
	JTextField healthProbDescriptionJT = new JTextField(20);
	JTextField healthProbTreatmentJT = new JTextField(20);
	JTextField healthProbDateDayJT = new JTextField(2);
	JTextField healthProbDateMonthJT = new JTextField(2);
	JTextField healthProbDateYearJT = new JTextField(4);
	JButton healthProbClearDetailsJB = new JButton("Clear Details");
	JButton healthProbAddHealthProbJB = new JButton("Add Health Problem");
	
	
	/*
	* General definitions
	*/
	
	AppointmentList theAppointmentList = new AppointmentList();
	HealthProblemHistory theHealthProblemHistory = new HealthProblemHistory();
	TimeSlotList timeSlots = new TimeSlotList();
	Surgery theSurgery = new Surgery();
    
    Listener controller = new Listener();
    Patient temp;

	public SurgeryGUI() {

		super("Electronic Patient Record System");
		theSurgery.startUp("DataFile.epr");
		
		JTabbedPane surgeryJT = new JTabbedPane();
		JPanel addEmployeeJP = new JPanel(new BorderLayout());
		JPanel healthProblemJP = new JPanel(new BorderLayout());
		JPanel addAppointmentJP = new JPanel(new BorderLayout());
		JPanel viewAppointmentsJP = new JPanel(new BorderLayout());
		JPanel addPatientDetailsJP = new JPanel(new BorderLayout());;

		surgeryJT.addTab("Add Employee(s)", addEmployeeJP);
		surgeryJT.addTab("Add Appointment(s)", addAppointmentJP);
		surgeryJT.addTab("View Appointment(s)", viewAppointmentsJP);
		surgeryJT.addTab("Add Patient(s)", addPatientDetailsJP);
		surgeryJT.addTab("Add Health Problem(s)", healthProblemJP);


		/*
		*  Add Patient Panel
		*/

		JPanel addPatientDetailsGridJP = new JPanel(new GridLayout(4,2));
		JPanel addPatientDateGridJP = new JPanel(new GridLayout(1,5));
		
		addPatientDetailsJP.add(new JLabel("Add Patient(s)", SwingConstants.CENTER),
													BorderLayout.NORTH);
		addPatientDetailsGridJP.add(new JLabel("Patient Name: ",SwingConstants.RIGHT));
		addPatientDetailsGridJP.add(addPatientNameJT);

		addPatientDetailsGridJP.add(new JLabel("NHS Number: ",SwingConstants.RIGHT));
		addPatientDetailsGridJP.add(addPatientNhsNumberJT);
		
		
		addPatientDetailsGridJP.add(new JLabel("Date Of Birth (dd/mm/yy): ",SwingConstants.RIGHT));
		
		addPatientDateGridJP.add(addPatientDateDayJT);
		addPatientDateGridJP.add(new JLabel("/ ",SwingConstants.CENTER));
		addPatientDateGridJP.add(addPatientDateMonthJT);
		addPatientDateGridJP.add(new JLabel("/ ",SwingConstants.CENTER));
		addPatientDateGridJP.add(addPatientDateYearJT);
		
		addPatientDetailsGridJP.add(addPatientDateGridJP);
		  
		addPatientDetailsGridJP.add(addPatientClearPatientDetailJB);
		addPatientClearPatientDetailJB.addActionListener(controller);

		addPatientDetailsGridJP.add(addPatientAddPatientJB);
		addPatientAddPatientJB.addActionListener(controller);

		addPatientDetailsJP.add(addPatientDetailsGridJP, BorderLayout.CENTER);

	
		/*
		 * View Appointments Panel
		 */
		 
	 	 JPanel viewAppointmentsButtonsGridJP = new JPanel(new GridLayout(1,3));
		 viewAppointmentsJP.add(new JLabel("View Appointments", SwingConstants.CENTER),BorderLayout.NORTH);
		 
		 viewAppointmentsJP.add(viewAppointmentsDetailsJT);
		 viewAppointmentsJP.add(viewAppointmentsButtonsGridJP, BorderLayout.SOUTH);
	     viewAppointmentsButtonsGridJP.add(new JLabel(""));
	     viewAppointmentsButtonsGridJP.add(viewAppointmentEmployeeJC);
	     viewAppointmentEmployeeJC.addActionListener(controller);
	     viewAppointmentsButtonsGridJP.add(new JLabel(""));
		
		
		/*
		*  Add Health Problem Panel
		*/
		
		JPanel healthProbGridJP = new JPanel(new GridLayout(5,2));
		JPanel healthProbDateGridJP = new JPanel(new GridLayout(1,5));
	
		healthProblemJP.add(new JLabel("Add Health Problem(s)", SwingConstants.CENTER),
													BorderLayout.NORTH);
		healthProbGridJP.add(new JLabel("Date Of Health Problem (dd/mm/yy): ",
													SwingConstants.RIGHT));
		
		healthProbDateGridJP.add(healthProbDateDayJT);
		healthProbDateGridJP.add(new JLabel("/ ",SwingConstants.CENTER));
		healthProbDateGridJP.add(healthProbDateMonthJT);
		healthProbDateGridJP.add(new JLabel("/ ",SwingConstants.CENTER));
		healthProbDateGridJP.add(healthProbDateYearJT);
		healthProbGridJP.add(healthProbDateGridJP);
		
		healthProbGridJP.add(new JLabel("Description: ",SwingConstants.RIGHT));
		healthProbGridJP.add(healthProbDescriptionJT);
		
		healthProbGridJP.add(new JLabel("Treatment: ",SwingConstants.RIGHT));
		healthProbGridJP.add(healthProbTreatmentJT);
		
		healthProbGridJP.add(new JLabel(""));
		healthProbGridJP.add(new JLabel(""));
		
		healthProbGridJP.add(healthProbAddHealthProbJB);
		healthProbAddHealthProbJB.addActionListener(controller);

		healthProbGridJP.add(healthProbClearDetailsJB);
		healthProbClearDetailsJB.addActionListener(controller);
	
    	healthProblemJP.add(healthProbGridJP, BorderLayout.CENTER);

		/*
		*  Add Employees Panel
		*/

		addEmployeeJP.add(new JLabel("Add Employee(s)", SwingConstants.CENTER),
		BorderLayout.NORTH);

		JPanel addEmployeeGridJP = new JPanel(new GridLayout(6,2));
		addEmployeeGridJP.add(new JLabel(""));
		addEmployeeGridJP.add(new JLabel(""));
		addEmployeeGridJP.add(new JLabel("Employee Name: ",SwingConstants.RIGHT));
		addEmployeeGridJP.add(addEmployeeNameJT);
		addEmployeeGridJP.add(new JLabel("Employee Position: ",SwingConstants.RIGHT));

		String[] jobs = {"Doctor", "Nurse", "Receptionist"};

		employeePositionJC = new JComboBox(jobs);
		employeePositionJC.setSelectedIndex(0);
		addEmployeeGridJP.add(employeePositionJC);
		addEmployeeGridJP.add(new JLabel(""));
		addEmployeeGridJP.add(new JLabel(""));
		addEmployeeGridJP.add(addStaffJB);
		
		addStaffJB.addActionListener(controller);
		addEmployeeGridJP.add(clearStaffJB);
		clearStaffJB.addActionListener(controller);
		addEmployeeJP.add(addEmployeeGridJP, BorderLayout.CENTER);


		/*
		*  Add Appointments Panel
		*/
		JPanel addAppointmentGridJP = new JPanel(new GridLayout(5,2));
		JPanel addAppointmentDateGridJP = new JPanel(new GridLayout(1,5));
		JPanel addAppointmentButtonsGridJP = new JPanel(new GridLayout(1,3));
		
		addAppointmentJP.add(new JLabel("Add Appointment(s)", SwingConstants.CENTER),
		BorderLayout.NORTH);
	
		addAppointmentForEmployeeJC = new JComboBox();
		addAppointmentForEmployeeJC.addActionListener(controller);
		
		addAppointmentTimeSlotJC = new JComboBox();

		addAppointmentGridJP.add(new JLabel("Choose Employee ",SwingConstants.RIGHT));
		addAppointmentGridJP.add(addAppointmentForEmployeeJC);

		addAppointmentGridJP.add(new JLabel("Appointment Slot ",SwingConstants.RIGHT));
		addAppointmentGridJP.add(addAppointmentTimeSlotJC);

		addAppointmentGridJP.add(new JLabel("Patient Name: ",SwingConstants.RIGHT));
		addAppointmentGridJP.add(addAppointmentPatientNameJT);

		addAppointmentGridJP.add(new JLabel("NHS Number: ",SwingConstants.RIGHT));
		addAppointmentGridJP.add(addAppointmentNhsNumberJT);
		
		
		addAppointmentGridJP.add(new JLabel("Date Of Birth (dd/mm/yy): ",SwingConstants.RIGHT));
		
		addAppointmentDateGridJP.add(addAppointmentDateDayJT);
		addAppointmentDateGridJP.add(new JLabel("/ ",SwingConstants.CENTER));
		addAppointmentDateGridJP.add(addAppointmentDateMonthJT);
		addAppointmentDateGridJP.add(new JLabel("/ ",SwingConstants.CENTER));
		addAppointmentDateGridJP.add(addAppointmentDateYearJT);
		
		
		addAppointmentGridJP.add(addAppointmentDateGridJP);
						  
		addAppointmentButtonsGridJP.add(addAppointmentProbFindPatientJB);
		addAppointmentProbFindPatientJB.addActionListener(controller);
		
		addAppointmentButtonsGridJP.add(addAppointmentClearDetailJB);
		addAppointmentClearDetailJB.addActionListener(controller);

		addAppointmentButtonsGridJP.add(addAppoitmentBookJB);
		addAppoitmentBookJB.addActionListener(controller);
				
		addAppointmentJP.add(addAppointmentButtonsGridJP,BorderLayout.SOUTH);
		addAppointmentJP.add(addAppointmentGridJP, BorderLayout.CENTER);
		
		// Now finish off the JFrame
	
		populateStaffDropDown();
		
		// add a window listener for window events
		this.addWindowListener(new WindowHandler());
		getContentPane().add(surgeryJT);
		this.pack();
		setVisible(true);
	}

	/*
	* Exit the Application
	*/
	private void exitTool()
	{
		theSurgery.shutDown("DataFile.epr");
		// Just for testing
		Iterator<Person> iter = theSurgery.getEmployees().iterator();
		Person temp;
		while (iter.hasNext()) {
			temp = iter.next();
			System.out.println(temp.getName());
		}
		System.exit(1);
	}

    /**
	* Inner class definition *
	*/
    public void addAppointmentAdd() {
    	
		Time theTime;
		Date theDate;
		
		String nhsNumber = (String)addAppointmentNhsNumberJT.getText();
		String employeeName = (String)addAppointmentForEmployeeJC.getSelectedItem();
		String slotName = (String)addAppointmentTimeSlotJC.getSelectedItem();
		
		Patient thePatient = theSurgery.getEPR().getPatient(nhsNumber);
		BookablePerson theEmployee = (BookablePerson)theSurgery.getEmployees().getPerson(employeeName);
		
		TimeSlot theSlot = timeSlots.getTimeSlot(slotName);
		theTime = theSlot.getTime();
		
		int day = Integer.parseInt(addAppointmentDateDayJT.getText());
		int month = Integer.parseInt(addAppointmentDateMonthJT.getText());
		int year = Integer.parseInt(addAppointmentDateYearJT.getText());
		
		theDate = new Date(day,month,year);
		
		Appointment theApp = new Appointment(theTime, theDate, thePatient);
		
		theEmployee.addFreeAppointment(theApp);
		
		Iterator<Appointment> iter = theEmployee.iterator();
		while (iter.hasNext()){
			System.out.println(iter.next());
		}
		
    }
   
    public void addHealthProblem() {
    
    	String theProb = healthProbDescriptionJT.getText();
		String theMed = healthProbTreatmentJT.getText();
							
		int day = Integer.parseInt(healthProbDateDayJT.getText());
		int month = Integer.parseInt(healthProbDateMonthJT.getText());
		int year = Integer.parseInt(healthProbDateYearJT.getText());
		Date theDate = new Date(day, month, year);
								    				
		HealthProblem tempHealthProblem = new HealthProblem(theDate, theProb, theMed);
	    tempHealthProblem.setDate(theDate);               
	    theHealthProblemHistory.addProblem(tempHealthProblem);
			        
		/*
	    *Lists Health Problems in DOS window.
	    */
	    Iterator<HealthProblem> iter = theHealthProblemHistory.iterator();
		HealthProblem temp;
		while (iter.hasNext()) {
			temp = iter.next();
			System.out.println();
			System.out.println(temp.getDate().toString());
			System.out.println(temp.getDescription());
			System.out.println(temp.getTreatment());
		}					
    }
    
   	public void clearHealthProblemFields() {
    	
    	healthProbDateDayJT.setText("");
		healthProbDateMonthJT.setText("");
		healthProbDateYearJT.setText("");
		healthProbDescriptionJT.setText("");
		healthProbTreatmentJT.setText("");
		System.out.println("Health Problem Fields Have Been Cleared");
		
    }
   
    public void viewBookings(){
    	
    	String appTxt ="";
    	String employeeName = (String)viewAppointmentEmployeeJC.getSelectedItem();
       	BookablePerson theEmployee = (BookablePerson)theSurgery.getEmployees().getPerson(employeeName);
       	
       	Iterator<Appointment> iter = theEmployee.iterator();
    	
    	while(iter.hasNext()){
    	   	appTxt += iter.next()+"\n";
    	}
    	viewAppointmentsDetailsJT.setText(appTxt);
    	
    }
    
    public void addStaff() {
    	Person temp;
		switch (((String)employeePositionJC.getSelectedItem()).charAt(0)) {
			case 'D':
			case 'd':
			 	temp = new Doctor(addEmployeeNameJT.getText());
			 	temp.setBookable(true);
			 	theSurgery.getEmployees().addPerson(temp);
			 	break;
			case 'R':
			case 'r':
				temp = new Receptionist(addEmployeeNameJT.getText());
				theSurgery.getEmployees().addPerson(temp);
				addEmployeeNameJT.setText(""); // now remove name
				return; // leave if not a BookablePerson
			case 'N':
			case 'n':
				temp = new Nurse(addEmployeeNameJT.getText());
				temp.setBookable(true);
				theSurgery.getEmployees().addPerson(temp);
				 break;
			 }

			// Now add the BookablePerson to the list of available staff
			addAppointmentForEmployeeJC.addItem(addEmployeeNameJT.getText());
			viewAppointmentEmployeeJC.addItem(addEmployeeNameJT.getText());
			addEmployeeNameJT.setText(""); // now remove name
		}

   public void addPatient() {
    	
    	String name = addPatientNameJT.getText();
		String nhsNum = addPatientNhsNumberJT.getText();
		
		int dobDay = Integer.parseInt(addPatientDateDayJT.getText());
		int dobMonth = Integer.parseInt(addPatientDateMonthJT.getText());
		int dobYear = Integer.parseInt(addPatientDateYearJT.getText());
		Date theDob = new Date(dobDay, dobMonth, dobYear);
		
		Patient tempPatient = new Patient(name, nhsNum, theDob);
		theSurgery.getEPR().addPatient(tempPatient);
		
		Iterator<Patient> iter = theSurgery.getEPR().iterator();
		Patient temp;
		while (iter.hasNext()) {
		
			temp = iter.next();
			System.out.println();
			System.out.println(temp.getName());
			System.out.println(temp.getNhsNumber());
			System.out.println(temp.getDateOfBirth().toString());
			System.out.println(tempPatient);
		}
		
	}	
    
    public void addPatientClearFields() {
    	
    	addPatientNameJT.setText(null);
		addPatientNhsNumberJT.setText(null);
		addPatientDateDayJT.setText(null);
		addPatientDateMonthJT.setText(null);
		addPatientDateYearJT.setText(null);
		
		System.out.println("Patient Details Tab: Patient Detail Fields Cleared");
    }
    
    public void addAppointmentClearFields() {
    	
    	addAppointmentPatientNameJT.setText(null);
	 	addAppointmentNhsNumberJT.setText(null);
	 	addAppointmentDateDayJT.setText(null);
		addAppointmentDateMonthJT.setText(null);
		addAppointmentDateYearJT.setText(null);
    }
    
    
    public void addAppointmentFindPatient() {
    	String nhsNumber = addAppointmentNhsNumberJT.getText();
		temp = theSurgery.getEPR().getPatient(nhsNumber);
		addAppointmentPatientNameJT.setText(temp.getName());
		addAppointmentNhsNumberJT.setText(temp.getNhsNumber());
		Date dob = temp.getDateOfBirth();
				
		addAppointmentDateDayJT.setText(Integer.toString(dob.getDay()));
		addAppointmentDateMonthJT.setText(Integer.toString(dob.getMonth()));
		addAppointmentDateYearJT.setText(Integer.toString(dob.getYear()));
			
	}
    
    public void addAppointmentGenSlots() {
    	
    	String selectedStaff = (String)addAppointmentForEmployeeJC.getSelectedItem();
    	BookablePerson theEmployee = (BookablePerson)theSurgery.getEmployees().getPerson(selectedStaff);
    	int appointmentLength = theEmployee.getAppointmentLength();
    	
    	if ((60 % appointmentLength) > 0 ) {
    		return;
    	}
    	
    	addAppointmentTimeSlotJC.removeAllItems();
    	
    	int slotsPerHour = (60 / appointmentLength);
    	String hourText;
    	String minsText;
    	String slotName;
    	TimeSlot tempSlot;
    	Time slotTime;
    	int mins = 0;
    	
    	
    	for(int hour = 9; hour <= 17; hour++) {
    		for(int slot = 0; slot < slotsPerHour; slot++) {
    			
    			hourText = Integer.toString(hour);    			
    			minsText = Integer.toString(slot * appointmentLength);
    			slotName = hourText+ ":" + minsText;
    			
    			slotTime = new Time(hour, mins);
    			tempSlot = new TimeSlot(slotName, slotTime);
    			
    			// Leave at bottom of loop.
    			timeSlots.addTimeSlot(tempSlot);
    			addAppointmentTimeSlotJC.addItem(tempSlot.getName());
    		}
    		
    	}
    }
    
   	public void populateStaffDropDown(){
   		Iterator<Person> iter = theSurgery.getEmployees().iterator();
		Person temp;
		while (iter.hasNext()) {
			temp = iter.next();
			
			if (temp.getBookable()){
				addAppointmentForEmployeeJC.addItem(temp.getName());
    			viewAppointmentEmployeeJC.addItem(temp.getName());
			}
   		}
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

    
    
        
    /**
	* Listen for button pressed
	*/
    public class Listener implements ActionListener
    {

		/**
		* Depending on which button is pressed carry out action
		*/
		public void actionPerformed(ActionEvent event) {

			Object theSource = event.getSource();
			/*
			 *Adds Staff to EPR System
			 */
			if (theSource == addStaffJB) {
				 addStaff();
				 return;
			}
				 
			/*
			* Clears block back out once value entered.
			*/
			if (theSource == clearStaffJB) {
				addEmployeeNameJT.setText("");
				return;
			}

			/*
			*Create Appointment Tab : Clear Fields
			*/
			if (theSource == addAppointmentClearDetailJB) {
				addAppointmentClearFields();
			 	return;
			}
			
			/*
			* Create Appointment Tab : Book Appointment
			*/
			if (theSource == addAppoitmentBookJB) {
				addAppointmentAdd();
				return;
		
					
			}
			
			/*
			* Create Appointment Tab : Clears Booking Fields
			*/
			if (theSource == addPatientClearPatientDetailJB) {
				addPatientClearFields();
				return;
			}

			/*
			 *Find Patient Details Before Booking
			 */
			 if (theSource == addAppointmentProbFindPatientJB) {
			 	addAppointmentFindPatient();
			 	return;
			}
			
			/*
			* Add Health Problem Panel: Adds Health Problem.
			*/
			if (theSource == healthProbAddHealthProbJB) {
				addHealthProblem();
				return;	
			}
		
	        /*
	        *Add Health Problem Panel: Clear Fields
	        */
	        if (theSource == healthProbClearDetailsJB) {
				clearHealthProblemFields();
				return;
			}
			
	       	/*
			* Add Patient Panel: Clear Patient Fields
			*/
			if (theSource == addPatientClearPatientDetailJB) {
				addPatientClearFields();
				return;
			}
	
			/*
			* Add Patient Panel: Add patient to EPR.
			*/
			if (theSource == addPatientAddPatientJB) {
				addPatient();
				return;
			}
			
			/*
			*Choose Employee to print appointments
			*/
			if (theSource == viewAppointmentEmployeeJC) {
				viewBookings();
				return;
			}
			
			/*
			*Generates Appointments Slots
			*/
			if (theSource == addAppointmentForEmployeeJC) {
				addAppointmentGenSlots();
				return;
			}
	
		}
				
	}

	public static void main (String [] args) {
		SurgeryGUI demo = new SurgeryGUI();
	}
}