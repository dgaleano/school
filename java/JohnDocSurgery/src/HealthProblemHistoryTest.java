import java.io.*;      // required for Serialising objects

public class HealthProblemHistoryTest {

    private HealthProblemHistory history = new HealthProblemHistory();

    private BufferedReader buf = new BufferedReader(
                                     new InputStreamReader(
                                         System.in));
    public Date getDateFromKeyboard() {
        System.out.println("Please enter day");
        // more to do here
        return null;
    }

    public String getDescription() {
        return "not done yet";
    }

    public String getTreatment() {
        return "not done yet";
    }

    public void runit() throws Exception {
        System.out.println("Test Harness for Health Problem History");
        Date tempDate = this.getDateFromKeyboard();
        String tempDescription = this.getDescription();
        String tempTreatment = this.getTreatment();


        System.out.println("The history is now as follows ");
        HealthProblem tempHealthProblem = new HealthProblem(tempDate,
                                                            tempDescription,
                                                            tempTreatment);
        history.addProblem(tempHealthProblem);
    }

    public static void main (String [] args) throws Exception {
        HealthProblemHistoryTest demo = new HealthProblemHistoryTest();
        demo.runit();
    }

}