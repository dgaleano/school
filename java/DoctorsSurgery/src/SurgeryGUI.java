import java.awt.*;        // Bring in the necessary classes from the AWT
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SurgeryGUI extends JFrame {

	/**
	 * New Patient definitions.
	 */
	JTextField newPatientNameJT = new JTextField(20);
	JTextField newPatientDobDayJT = new JTextField(2);
	JTextField newPatientDobMonthJT = new JTextField(2);
	JTextField newPatientDobYearJT = new JTextField(4);
	JTextField newPatientNhsNumberJT = new JTextField(20);
	JButton newPatientClearJB = new JButton("Clear Details");
	JButton newPatientAddJB = new JButton("Add Patient");
	
	/**
     * New Employee definitions.
     */
	JComboBox newEmployeeJobsJC;
	JTextField newEmployeeNameJT = new JTextField(20);
	JButton newEmployeeClearJB = new JButton("Clear Entry");
	JButton newEmployeeAddJB = new JButton("Add Employee");
	
	/**
	 * New Appointment definitions.
	 */
	JComboBox newAppointmentEmployeeJC = new JComboBox();
	JComboBox newAppointmentTimeSlotJC = new JComboBox();
	JTextField newAppointmentPatientNhsNumJT = new JTextField(20);
	JTextField newAppointmentDateDayJT = new JTextField(2);
	JTextField newAppointmentDateMonthJT = new JTextField(2);
	JTextField newAppointmentDateYearJT = new JTextField(4);
	JButton newAppointmentClearJB = new JButton("Clear Details");
	JButton newAppointmentAddJB = new JButton("Book Appointment");
	
	/**
	 * Diagnostics definitions.
	 */
	JButton diagnosticsPrintEprJB = new JButton("Go!");
	JButton diagnosticsPrintEmpJB = new JButton("Go!");
	JButton diagnosticsPrintApptsJB = new JButton("Go!");
	JComboBox diagnosticsEmployeeJC = new JComboBox();

	/**
	 * New Health Problem defintions.
	 */
	JTextField newHealthProblemNhsNumJT = new JTextField(20);
	JTextField newHealthProblemTitleJT = new JTextField(20);
	JTextField newHealthProblemTreatmentJT = new JTextField(20);
	JTextField newHealthProblemDateDayJT = new JTextField(2);
	JTextField newHealthProblemDateMonthJT = new JTextField(2);
	JTextField newHealthProblemDateYearJT = new JTextField(4);
	JButton newHealthProblemClearJB = new JButton("Clear Details");
	JButton newHealthProblemAddJB = new JButton("Add Problem");
	
	/**
	 * View Appointments definitions.
	 */
	JComboBox viewAppointmentsEmployeeJC = new JComboBox();
	JTextArea viewAppointmentsDetailsJT = new JTextArea("", 5, 10);
	
	/**
	 * General class definitions.
	 */
	Surgery theSurgery = new Surgery();
    Listener theListener = new Listener();
    
    /**
     * Commonly used Swing Constants.
     */
    int alignRight = SwingConstants.RIGHT;
	int alignCenter = SwingConstants.CENTER;

	/**
	 * Everything starts here!
	 */
	public SurgeryGUI() {

		super("Bob & Smith Doctors Surgery");
		super.setBounds(200, 200, 0, 0);

		// Load the surgery data.
		theSurgery.startUp("DataFile.epr");

		// Define panels and such.
		JTabbedPane surgeryJT = new JTabbedPane();
		JPanel welcomeJP = new JPanel(new BorderLayout());
		JPanel newEmployeeJP = new JPanel(new BorderLayout());
		JPanel newAppointmentJP = new JPanel(new BorderLayout());
		JPanel newPatientJP = new JPanel(new BorderLayout());;
		JPanel newHealthProblemJP = new JPanel(new BorderLayout());
		JPanel newAppoientmentJP = new JPanel(new BorderLayout());
		JPanel viewAppointmentsJP = new JPanel(new BorderLayout());
		JPanel diagnosticsJP = new JPanel(new BorderLayout());

		// Add tabs to main panel.
		surgeryJT.addTab("Welcome", welcomeJP);
		surgeryJT.addTab("New Employee", newEmployeeJP);
		surgeryJT.addTab("New Appointment", newAppointmentJP);
		surgeryJT.addTab("New Patient", newPatientJP);
		surgeryJT.addTab("New Health Problem", newHealthProblemJP);
		surgeryJT.addTab("View Appointments", viewAppointmentsJP);
		surgeryJT.addTab("Diagnostics", diagnosticsJP);
		
		/*
		 * Welcome Panel.
		 */
		String welcomeText = "Please select a tab...";
		JLabel welcomeTitleJL = new JLabel("Welcome!", alignCenter);
		JLabel welcomeTextJL = new JLabel(welcomeText, alignCenter);
		welcomeJP.add(welcomeTitleJL, BorderLayout.NORTH);
		welcomeJP.add(welcomeTextJL, BorderLayout.CENTER);
		
		/*
		 * New Patient Panel.
		 */
		JLabel newPatientTitleJL = new JLabel("Create Patient", alignCenter);
		JPanel newPatientGridJP = new JPanel(new GridLayout(4, 2));
		JPanel newPatientDobGridJP = new JPanel(new GridLayout(1, 5));
		
		// Special Date of Birth grid.
		newPatientDobGridJP.add(newPatientDobDayJT);
		newPatientDobGridJP.add(new JLabel("/", alignCenter));
		newPatientDobGridJP.add(newPatientDobMonthJT);
		newPatientDobGridJP.add(new JLabel("/", alignCenter));
		newPatientDobGridJP.add(newPatientDobYearJT);

		// Add fields, labels, buttons and listeners.
		newPatientGridJP.add(new JLabel("Patient Name: ", alignRight));
		newPatientGridJP.add(newPatientNameJT);
		newPatientGridJP.add(new JLabel("NHS Number: ", alignRight));
		newPatientGridJP.add(newPatientNhsNumberJT);
		newPatientGridJP.add(new JLabel("Date of Birth: ", alignRight));
		newPatientGridJP.add(newPatientDobGridJP);
		newPatientGridJP.add(newPatientClearJB);
		newPatientGridJP.add(newPatientAddJB);
		newPatientClearJB.addActionListener(theListener);
		newPatientAddJB.addActionListener(theListener);
		
		// Add to main panel.
		newPatientJP.add(newPatientTitleJL, BorderLayout.NORTH);
		newPatientJP.add(newPatientGridJP, BorderLayout.CENTER);

		/*
		 * New Employee Panel.
		 */
		JLabel newEmployeeTitleJL = new JLabel("New Employee", alignCenter);
		JPanel newEmployeeGridJP = new JPanel(new GridLayout(3,2));
		
		// Add staff to the staff combo box.
		String[] employeeJobs = {"Select...", "Doctor", "Nurse", "Receptionist"};
		newEmployeeJobsJC = new JComboBox(employeeJobs);
		newEmployeeJobsJC.setSelectedIndex(0);
		
		// Add fields, labels, buttons and listeners.
		newEmployeeGridJP.add(new JLabel("Employee Name: ", alignRight));
		newEmployeeGridJP.add(newEmployeeNameJT);
		newEmployeeGridJP.add(new JLabel("Employee Position: ", alignRight));
		newEmployeeGridJP.add(newEmployeeJobsJC);
		newEmployeeGridJP.add(newEmployeeAddJB);
		newEmployeeGridJP.add(newEmployeeClearJB);
		newEmployeeAddJB.addActionListener(theListener);
		newEmployeeClearJB.addActionListener(theListener);
		
		// Add to main panel.
		newEmployeeJP.add(newEmployeeTitleJL, BorderLayout.NORTH);
		newEmployeeJP.add(newEmployeeGridJP, BorderLayout.CENTER);

		/*
		 * New Appointment Panel.
		 */
		JLabel newAppointmentTitleJL = new JLabel("New Appointment", alignCenter);
		JPanel newAppointmentGridJP = new JPanel(new GridLayout(5,2));
		JPanel newAppointmentDateGridJP = new JPanel(new GridLayout(1, 5));
		
		// Special Date grid.
		newAppointmentDateGridJP.add(newAppointmentDateDayJT);
		newAppointmentDateGridJP.add(new JLabel("/", alignCenter));
		newAppointmentDateGridJP.add(newAppointmentDateMonthJT);
		newAppointmentDateGridJP.add(new JLabel("/", alignCenter));
		newAppointmentDateGridJP.add(newAppointmentDateYearJT);

		// Add fields, labels, buttons and listeners.
		newAppointmentGridJP.add(new JLabel("Staff Member: ", alignRight));
		newAppointmentGridJP.add(newAppointmentEmployeeJC);
		newAppointmentGridJP.add(new JLabel("Select Time Slot: ", alignRight));
		newAppointmentGridJP.add(newAppointmentTimeSlotJC);
		newAppointmentGridJP.add(new JLabel("Select Date: ", alignRight));
		newAppointmentGridJP.add(newAppointmentDateGridJP);
		newAppointmentGridJP.add(new JLabel("Patient NHS Number: ", alignRight));
		newAppointmentGridJP.add(newAppointmentPatientNhsNumJT);
		newAppointmentGridJP.add(newAppointmentAddJB);
		newAppointmentGridJP.add(newAppointmentClearJB);
		newAppointmentEmployeeJC.addActionListener(theListener);
		newAppointmentClearJB.addActionListener(theListener);
		newAppointmentAddJB.addActionListener(theListener);
		
		// Update the slot selection box.
		newAppointmentUpdateEmployees();
		newAppointmentUpdateTimeSlots();
		
		// Add to main panel.
		newAppointmentJP.add(newAppointmentTitleJL, BorderLayout.NORTH);
		newAppointmentJP.add(newAppointmentGridJP, BorderLayout.CENTER);
		
		/*
		 * Diagnostics Panel.
		 */
		JPanel diagnosticsGridJP = new JPanel(new GridLayout(4,2));
		JLabel diagnosticsTitleJL = new JLabel("Diagnostics", alignCenter);
		
		// Add some buttons.
		diagnosticsGridJP.add(new JLabel("Print EPR: ", alignRight));
		diagnosticsGridJP.add(diagnosticsPrintEprJB);
		diagnosticsGridJP.add(new JLabel("Print Employees: ", alignRight));
		diagnosticsGridJP.add(diagnosticsPrintEmpJB);
		diagnosticsGridJP.add(new JLabel("Select Employee: ", alignRight));
		diagnosticsGridJP.add(diagnosticsEmployeeJC);
		diagnosticsGridJP.add(new JLabel("Print Appointments: ", alignRight));
		diagnosticsGridJP.add(diagnosticsPrintApptsJB);
		diagnosticsPrintEprJB.addActionListener(theListener);
		diagnosticsPrintEmpJB.addActionListener(theListener);
		diagnosticsPrintApptsJB.addActionListener(theListener);
		
		// Add to main panel.
		diagnosticsJP.add(diagnosticsTitleJL, BorderLayout.NORTH);
		diagnosticsJP.add(diagnosticsGridJP, BorderLayout.CENTER);
		
		/*
		 * New Health Problem Panel.
		 */
		JLabel newHealthProblemTitleJL = new JLabel("New Health Problem", alignCenter);
		JPanel newHealthProblemGridJP = new JPanel(new GridLayout(5, 2));
		JPanel newHealthProblemDateGridJP = new JPanel(new GridLayout(1, 5));
		
		// Special Date of Birth grid.
		newHealthProblemDateGridJP.add(newHealthProblemDateDayJT);
		newHealthProblemDateGridJP.add(new JLabel("/", alignCenter));
		newHealthProblemDateGridJP.add(newHealthProblemDateMonthJT);
		newHealthProblemDateGridJP.add(new JLabel("/", alignCenter));
		newHealthProblemDateGridJP.add(newHealthProblemDateYearJT);

		// Add fields, labels, buttons and listeners.
		newHealthProblemGridJP.add(new JLabel("Patient NHS Number: ", alignRight));
		newHealthProblemGridJP.add(newHealthProblemNhsNumJT);
		newHealthProblemGridJP.add(new JLabel("The Problem: ", alignRight));
		newHealthProblemGridJP.add(newHealthProblemTitleJT);
		newHealthProblemGridJP.add(new JLabel("The Treatment: ", alignRight));
		newHealthProblemGridJP.add(newHealthProblemTreatmentJT);
		newHealthProblemGridJP.add(new JLabel("Discovered (dd/mm/yyyy): ", alignRight));
		newHealthProblemGridJP.add(newHealthProblemDateGridJP);
		newHealthProblemGridJP.add(newHealthProblemClearJB);
		newHealthProblemGridJP.add(newHealthProblemAddJB);
		newHealthProblemClearJB.addActionListener(theListener);
		newHealthProblemAddJB.addActionListener(theListener);
		
		// Add to main panel.
		newHealthProblemJP.add(newHealthProblemTitleJL, BorderLayout.NORTH);
		newHealthProblemJP.add(newHealthProblemGridJP, BorderLayout.CENTER);
		
		/*
		 * View Appointments Panel.
		 */
		JLabel viewAppointmentsTitleJL = new JLabel("View Appointments", alignCenter);
		JPanel viewAppointmentsGridJP = new JPanel(new GridLayout(2, 1));
		
		// Add fields, labels, buttons and listeners.
		viewAppointmentsGridJP.add(viewAppointmentsEmployeeJC);
		viewAppointmentsGridJP.add(viewAppointmentsDetailsJT);
		viewAppointmentsEmployeeJC.addActionListener(theListener);
		viewAppointmentsShow();
		
		// Add to main panel.
		viewAppointmentsJP.add(viewAppointmentsTitleJL, BorderLayout.NORTH);
		viewAppointmentsJP.add(viewAppointmentsGridJP, BorderLayout.CENTER);
		
		/*
		 * Add a window listener for window events.
		 */
		this.addWindowListener(new WindowHandler());
		getContentPane().add(surgeryJT);
		this.pack();
		setVisible(true);
	}

	/**
	 * Exit the Application
	 */
	private void exitTool()	{
		theSurgery.shutDown("DataFile.epr");
		System.exit(1);
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
	 * Listen for button pressed.
	 */
    public class Listener implements ActionListener {

		/**
		 * Depending on which button is pressed carry out action.
		 */
		public void actionPerformed(ActionEvent event) {

			// Where did we come from?
			Object theSource = event.getSource();
			
			/*
			 * New Employee: Add
			 */
			if (theSource == newEmployeeAddJB) {
				newEmployeeAdd();
				return;
			}

			/*
			 * New Employee: Clear
			 */
			if (theSource == newEmployeeClearJB) {
				newEmployeeClear();
				return;
			}

			/*
			 * New Appointment: Add
			 */
			if (theSource == newAppointmentAddJB) {
				newAppointmentAdd();
				return;
			}
			
			/*
			 * New Appointment: Clear
			 */
			if (theSource == newAppointmentClearJB) {
				newAppointmentClear();
				return;
			}

			/*
			 * New Patient: Add
			 */
			if (theSource == newPatientAddJB) {
				newPatientAdd();
				return;
 			}

			/*
			 * New Patient: Clear
			 */
			if (theSource == newPatientClearJB) {
				newPatientClear();
				return;
			}
			
			/*
			 * New Appointment: Update valid slots.
			 */
			if (theSource == newAppointmentEmployeeJC) {
				newAppointmentUpdateTimeSlots();
				return;
			}
			
			/*
			 * Diagnostics: Print EPR
			 */
			if (theSource == diagnosticsPrintEprJB) {
				diagnosticsPrintEpr();
				return;
			}
			
			/*
			 * Diagnostics: Print Employees
			 */
			if (theSource == diagnosticsPrintEmpJB) {
				diagnosticsPrintEmp();
				return;
			}
			
			/*
			 * Diagnostics: Print Appointments
			 */
			if (theSource == diagnosticsPrintApptsJB) {
				diagnosticsPrintAppts();
				return;
			}
			
			/*
			 * New Health Problem: Add
			 */
			if (theSource == newHealthProblemAddJB) {
				newHealthProblemAdd();
			}
			
			/*
			 * New Health Problem: Clear
			 */
			if (theSource == newHealthProblemClearJB) {
				newHealthProblemClear();
			}
			
			/*
			 * View Appointments: Show
			 */
			if (theSource == viewAppointmentsEmployeeJC) {
				viewAppointmentsShow();
			}
		}
	}
	
	/**
	 * View Appointments: Show
	 */
	public void viewAppointmentsShow() {
		
		// Define objects and variables, etc.
		BookablePerson theEmployee = null;
		String employeeName = (String)viewAppointmentsEmployeeJC.getSelectedItem();
		String apptsText = "";
		String newApptsText = "";
		
		// Start of with a nice friendly tip...
		apptsText = "Select a staff member, and the ";
		apptsText += "results will appear in this area.";
		viewAppointmentsDetailsJT.setText(apptsText);
		
		try {
			// Try and find the employee.
			theEmployee = (BookablePerson)theSurgery.getEmployees().getByName(employeeName);
			
		} catch (HealthException e) {
			return;
		}
		
		// Reset the temp var for text box stuff.
		newApptsText = "";
		
		Iterator<Appointment> iter = theEmployee.iterator();
		while (iter.hasNext()) {
			newApptsText += iter.next() + "\n";
		}
		
		if (newApptsText != "") {
			apptsText = newApptsText;
		} else {
			apptsText = "This member of staff has no appointments.";
		}
		
		viewAppointmentsDetailsJT.setText(apptsText);
	}
	
	/**
	 * New Health Problem: Add
	 */
	public void newHealthProblemAdd() {
		
		// Get values from text and combo boxes, etc.
		String theProb = newHealthProblemTitleJT.getText();
		String theTreat = newHealthProblemTreatmentJT.getText();
		String nhsNumber = (String)newHealthProblemNhsNumJT.getText();
		int dateDay, dateMonth, dateYear;
		Date theDate = null;
		Patient thePatient = null;
		
		try {
			// Try to parse date.
			dateDay = Integer.parseInt(newHealthProblemDateDayJT.getText());
			dateMonth = Integer.parseInt(newHealthProblemDateMonthJT.getText());
			dateYear = Integer.parseInt(newHealthProblemDateYearJT.getText());
			
		} catch (NumberFormatException e) {
			warningMessage("Invalid date!");
			return;
		}
		
		try {
			// Make new Date object.
			theDate = new Date(dateDay, dateMonth, dateYear);
			
			// Try and find a patient.
			thePatient = theSurgery.getEPR().getPatient(nhsNumber);
			
		} catch (HealthException e) {
			warningMessage(e.getMessage());
			return;
		}
		
		// Parse the health problem into object.
		HealthProblem theProblem = new HealthProblem(theDate, theProb, theTreat);
		
		try {
			// Try to add problem to patient record.
			thePatient.getHistory().addProblem(theProblem);
			
		} catch (HealthException e) {
			warningMessage(e.getMessage());
			return;
		}
		
		// Clear fields to show mission succsessfull!
		newHealthProblemClear();
	}
	
	/**
	 * New Health Problem: Clear
	 */
	public void newHealthProblemClear() {
		newHealthProblemNhsNumJT.setText("");
		newHealthProblemTitleJT.setText("");
		newHealthProblemTreatmentJT.setText("");
		newHealthProblemDateDayJT.setText("");
		newHealthProblemDateMonthJT.setText("");
		newHealthProblemDateYearJT.setText("");
	}
	
	/**
	 * Diagnostics: Print EPR
	 */
	public void diagnosticsPrintEpr() {
		theSurgery.printEPR();
	}
	
	/**
	 * Diagnostics: Print Employees
	 */
	public void diagnosticsPrintEmp() {
		theSurgery.printEmployees();
	}
	
	/**
	 * Diagnostics: Print Employees
	 */
	public void diagnosticsPrintAppts() {
		
		String employeeName = (String)diagnosticsEmployeeJC.getSelectedItem();
		BookablePerson theEmployee = null;
		
		try {
			theEmployee = (BookablePerson)theSurgery.getEmployees().getByName(employeeName);
		
		} catch (HealthException e) {
			warningMessage(e.getMessage());
			return;
		}
		
		System.out.println("\nPrinting appointments for " + theEmployee);
		Iterator<Appointment> iter = theEmployee.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
	/**
	 * New Appointment: Add
	 */
	public void newAppointmentAdd() {
		
		// Define objects and stuff.
		Patient thePatient = null;
		BookablePerson theEmployee = null;
		Appointment theAppt = new Appointment();
		int dateDay, dateMonth, dateYear;
		
		// Get various values from JComboBox objects.
		String nhsNumber = (String)newAppointmentPatientNhsNumJT.getText();
		String employeeName = (String)newAppointmentEmployeeJC.getSelectedItem();
		String timeSlotLabel = (String)newAppointmentTimeSlotJC.getSelectedItem();
		
		try {
			// Try and find the employee.
			theEmployee = (BookablePerson)theSurgery.getEmployees().getByName(employeeName);
		
			// Get the time slot's Time object and add to appointment.
			ValidSlot timeSlot = theEmployee.getSlotByLabel(timeSlotLabel);
			Time theTime = timeSlot.getTime();
			theAppt.setTime(theTime);
			
		} catch (HealthException e) {
			// Selected item may be a user tip in the drop down box.
		}
		
		try {
			// Work out the Date and add to the appointment.
			dateDay = Integer.parseInt(newAppointmentDateDayJT.getText());
			dateMonth = Integer.parseInt(newAppointmentDateMonthJT.getText());
			dateYear = Integer.parseInt(newAppointmentDateYearJT.getText());
			
		} catch (NumberFormatException e) {
			warningMessage("Invalid date!");
			return;
		}
		
		try {
			// Make new date object.
			Date theDate = new Date(dateDay, dateMonth, dateYear);
			theAppt.setDate(theDate);
			
			// Try to find the patient.
			thePatient = theSurgery.getEPR().getPatient(nhsNumber);
			theAppt.setPatient(thePatient);
			
			// Add appontment to BookablePerson's list and clear the form.
			theEmployee.addFreeAppointment(theAppt);
			
		} catch (HealthException e) {
			warningMessage(e.getMessage());
			return;
		}
		
		viewAppointmentsShow();
		newAppointmentClear();
	}
	
	/**
	 * New Appointment: Clear
	 */
	public void newAppointmentClear() {
		newAppointmentEmployeeJC.setSelectedIndex(0);
		newAppointmentTimeSlotJC.setSelectedIndex(0);
		newAppointmentPatientNhsNumJT.setText("");
		newAppointmentDateDayJT.setText("");
		newAppointmentDateMonthJT.setText("");
		newAppointmentDateYearJT.setText("");
	}
	
	/**
	 * New Appointment: Updates employee list.
	 */
	public void newAppointmentUpdateEmployees() {
		
		// First, clear the slot list.
		newAppointmentEmployeeJC.removeAllItems();
		diagnosticsEmployeeJC.removeAllItems();
		viewAppointmentsEmployeeJC.removeAllItems();
		
		// Now get the stuff.
		Iterator<Person> iter = theSurgery.getEmployees().iterator();
		Person tempEmployee;
		
		if (theSurgery.getEmployees().staffCount() > 0) {
			
			newAppointmentEmployeeJC.addItem("Select...");
			viewAppointmentsEmployeeJC.addItem("Select...");
			
			while (iter.hasNext()) {
				tempEmployee = iter.next();
				if (tempEmployee.getBookable()) {
					newAppointmentEmployeeJC.addItem(tempEmployee.getName());
					diagnosticsEmployeeJC.addItem(tempEmployee.getName());
					viewAppointmentsEmployeeJC.addItem(tempEmployee.getName());
				}
			}
		} else {
			newAppointmentEmployeeJC.addItem("No staff found!");
			viewAppointmentsEmployeeJC.addItem("No staff found!");
		}
	}
	
	/**
	 * New Appointment: Updates time slot list.
	 */
	public void newAppointmentUpdateTimeSlots() {
		
		// Define objects.
		Person theEmployee = null;
		
		// First, clear the slot list.
		newAppointmentTimeSlotJC.removeAllItems();
		
		// Then get the employee's valid slots.
		String selected = (String)newAppointmentEmployeeJC.getSelectedItem();
		
		try {
			// Try and find the employee.
			theEmployee = theSurgery.getEmployees().getByName(selected);
			Iterator<ValidSlot> iter = theEmployee.getValidSlots().iterator();
			
			newAppointmentTimeSlotJC.addItem("Select time...");
			
			// And add them to the slot list.
			while (iter.hasNext()) {
				newAppointmentTimeSlotJC.addItem(iter.next().getLabel());
			}
			
		} catch (HealthException e) {
			newAppointmentTimeSlotJC.addItem("Select staff member!");
		}
	}
	
	/**
	 * New Employee: Add employee to Surgery.
	 */
	public void newEmployeeAdd() {
		
		String name = newEmployeeNameJT.getText();
		String selected = (String)newEmployeeJobsJC.getSelectedItem();
		Person addStaff;
		
		switch (selected.charAt(0)) {
			case 'D':
				addStaff = new Doctor(name);
				break;
				
			case 'N':
				addStaff = new Nurse(name);
				break;
				
			case 'R':
				addStaff = new Receptionist(name);
				break;
			
			default:
				return;
		}
		
		// *** Generates employee not found error? ***		
		theSurgery.getEmployees().addPerson(addStaff);
		newAppointmentUpdateEmployees();
		newEmployeeClear();
	}
	
	/**
	 * New Employee: Clear text fields.
	 */
	public void newEmployeeClear() {
		newEmployeeNameJT.setText("");
		newEmployeeJobsJC.setSelectedIndex(0);
	}
	
	/**
	 * New Patient: Add patient to EPR.
	 */
	public void newPatientAdd() {
		
		// Get input values and declare some other stuff.
		String name = newPatientNameJT.getText();
		String nhsNumber = newPatientNhsNumberJT.getText();
		int dobDay, dobMonth, dobYear;
		Date dob = null;
		
		try {
			// Parse input for Date class.
			dobDay = Integer.parseInt(newPatientDobDayJT.getText());
			dobMonth = Integer.parseInt(newPatientDobMonthJT.getText());
			dobYear = Integer.parseInt(newPatientDobYearJT.getText());
			
		} catch (NumberFormatException e) {
			warningMessage("Invalid date!");
			return;
		}
		
        try {
			// Try and add to the Date object.
			dob = new Date(dobDay, dobMonth, dobYear);
			
			// Add patient to EPR.
        	theSurgery.getEPR().addPatient(new Patient(name, nhsNumber, dob));
        	
        } catch (HealthException e) {
        	// May have missed out name or nhs number?
        	warningMessage(e.getMessage());
        	return;
        }
		
        
        newPatientClear();
	}
	
	/**
	 * New Patient: Clear text fields.
	 */
	public void newPatientClear() {
		newPatientNameJT.setText("");
		newPatientNhsNumberJT.setText("");
		newPatientDobDayJT.setText("");
		newPatientDobMonthJT.setText("");
		newPatientDobYearJT.setText("");
	}
	
	/**
	 * Shows a warning message alert window.
	 */
	public void warningMessage(String theMessage) {
		JOptionPane.showMessageDialog(
			this, theMessage, "Oops!",
			JOptionPane.WARNING_MESSAGE
		);
	}

	public static void main (String [] args) {
		SurgeryGUI demo = new SurgeryGUI();
	}
}