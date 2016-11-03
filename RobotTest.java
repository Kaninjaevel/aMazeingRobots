import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple program set to test the RightHandRule robot and
 * memory Robot.
 * @author Thomas Sarlin <thsa0043/id15tsn>
 * @version 1.0 19/4-2016
 */

public class RobotTest {
    private RightHandRuleRobot rHR;
    private MemoryRobot mR;
    private Maze m[];

    /**
     * Constructor for the test, uses an input-argument in form
     * of a maze-file.
     * @param argv - maze-file to test on the robots.
     * @throws IllegalArgumentException - If maze file is invalid
     * @throws IOException - If maze-file is missing or corrupt.
     */

    public RobotTest(String[] argv) throws IllegalArgumentException,IOException{
        File inputFile;
                m= new Maze[argv.length];
        for(int i=0;i<argv.length;i++) {
            inputFile = new File(argv[i]);
            this.m[i] = new Maze(inputFile);
        }
        this.robotTestMenu(argv);
    }

    /**
     *
     */
    private void robotTestMenu(String[] argv) throws IOException{
        char selection = '\0';
        int mazeSelection=0;
        boolean validChoice=false;
        while (selection != 'Q') {
            BufferedReader bR = new BufferedReader(
                    new InputStreamReader(System.in));

            System.out.println("\n--------------------------"
                    +"\nWhich maze do you want to test?" +
                    "\nWrite one of the following numbers:");
            for(int i=0;i<argv.length;i++)
                System.out.println(i+1 +":" +argv[i]);


            while(!validChoice){
                validChoice=true;
                System.out.print(">.. ");
                try {
                    mazeSelection=Integer.parseInt(bR.readLine());
                }catch(NumberFormatException e) {
                    System.out.println("Not a valid choice try again");
                    validChoice=false;
                }
                if(mazeSelection>argv.length||mazeSelection<1){
                    System.out.println("Not a valid choice try again");
                    validChoice=false;
                }

            }
            System.out.println("\n--------------------------"
                    +"\nWhich robot do you want to test?"
                    + "\nR for right hand rule robot."
                    + "\nM for memory robot."
                    + "\nQ to quit program."
                    + "\n--------------------------");
            System.out.print(">.. ");
            selection = (char)System.in.read();
            System.in.read();
            if(selection=='R')
                rightHandRuleRobotTest(m[mazeSelection-1]);
            else if(selection=='M')
                memoryRobotTest(m[mazeSelection-1]);
            else if (selection=='Q')
                System.out.println("Have a nice day");
            else
                System.out.println("Invalid input, try again.");
        }
    }

    /**
     * Prints out that you have selected RightHandRuleRobot
     * and starts the specific test for that robot.
     * @throws IOException
     */

    private void rightHandRuleRobotTest(Maze m) throws IOException{
        System.out.println("Right hand rule robot selected.");
        this.rHR = new RightHandRuleRobot(m);
        testSelection(rHR);
    }
    /**
     * Prints out that you have selected memoryRobot
     * and starts the specific test for that robot.
     * @throws IOException
     */
    private void memoryRobotTest(Maze m) throws IOException{
        System.out.println("Memory robot selected.");
        this.mR = new MemoryRobot(m);
        testSelection(mR);
    }

    /**
     * indicates what options you have during your testing.
     * @param r
     * @throws IOException
     */
    private void testSelection(Robot r) throws IOException{
        char selection;
        boolean testIsDone = false;
        System.out.println("\nSelect test method:"
                + "\nS - Step by step"
                + "\nR - Run trough maze");
        while(!testIsDone){
            System.out.print(">.. ");
            selection = (char) System.in.read();
            System.in.read();
            if (selection == 'S'){
                stepByStepTest(r);
                testIsDone = true;
            }
            else if(selection=='R') {
                runTroughTest(r);
                testIsDone = true;
            }
            else
                System.out.println("Invalid input, try again.");

        }
    }


    /**
     * Prints the maze step by step
     * with an X to indicate the robots location.
     * @param r - Selected Robot to run trough maze.
     * @throws IOException
     */
    public void stepByStepTest(Robot r) throws IOException{
        char selection= '\0';
        System.out.println("Current maze:");
        while(selection!='Q') {
            r.m.printMaze(r.getCurrentPosition());
            System.out.println("Press enter for next step, write Q to quit.");
            selection=(char)System.in.read();
            r.move();
            if(r.hasReachedGoal()) {
                r.m.printMaze(r.getCurrentPosition());
                System.out.println("Goal reached. press enter to continue");
                break;
            }
        }
        System.in.read();
    }

    /**
     * Tests if the robot is able to run through the maze
     * and if it is possible the time spent and moves made
     * is displayed.
     * @param r - Robot to test.
     * @throws IOException
     */
    public void runTroughTest(Robot r) throws IOException{
        int moves=0;
        System.out.println("\nCurrent maze:");
        r.m.printMaze(new Position(999,999));
        long startTime = System.currentTimeMillis();
        while(!r.hasReachedGoal()) {
            r.move();
            moves++;
        }
        long stopTime = System.currentTimeMillis();
        long time =(stopTime-startTime);
        System.out.println("Reached goal in: " + moves +" moves.");
        System.out.println("Time: " + time + "ms");
        System.out.println("Press enter to continue.. ");
        System.in.read();
    }


    public static void main(String[] argv) throws IllegalArgumentException
            ,IOException {
        new RobotTest(argv);
    }
}
