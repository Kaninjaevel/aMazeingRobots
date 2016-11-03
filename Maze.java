import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by KaninJaevel on 07/04/2016.
 */

public class Maze {
    private char[][] mazeData;
    private int mazeWidth;
    private int mazeHeight;
    private static final char MOVABLE = ' ';
    private static final char START = 'S';
    private static final char GOAL = 'G';
    private static final char WALL = '*';

    /**
     * Constructor for maze, reads in a file and translates it
     * to a mazeData.
     * @parm argv, input argument in form of a string, used to supply
     * a maze. Walls are indicated with '*', movable spaces with blank-space ' '
     * , start with 'S' and goal with 'G'
     *
     * Example:
     *
     *  *S*******
     *  *  *    *
     *  * ** ****
     *  *    *  G
     *  *  ***  *
     *  *       *
     *  *********
     *  @throws IllegalStateException if maze-file is implemented wrong.
     */

    public Maze(File inputFile) throws IllegalStateException
            , FileNotFoundException {

        this.checkMaze(inputFile);
        this.calculateMaze(inputFile);
        this.setMaze(inputFile);
    }

    /**
     * Checks if the inputFile is of the
     * accepted format. ie. contains start,goal and no
     * other characters than  *, ' ', S, G.
     * @param inputFile - file to check
     * @throws Exception if file has invalid structure.
     * @throws FileNotFoundException if file is corrupt or invalid
     */
    private void checkMaze(File inputFile) throws FileNotFoundException
            ,IllegalStateException {
        boolean hasStart=false,hasGoal=false;
        Scanner readFile= new Scanner(inputFile);
        String line;
        char c;
        while(readFile.hasNextLine()){
            line= readFile.nextLine();
            for(int i=0;i<line.length();i++){
                c=line.charAt(i);

                if (c!='S'&&c!='G'&&c!='*'&&c!=' ')
                    throw new IllegalStateException(
                            "File holds invalid symbols");
                if(c==START)
                    hasStart=true;
                if(c==GOAL)
                    hasGoal=true;
            }
        }

        if(!hasStart||!hasGoal)
            throw new IllegalStateException("Maze missing necessary values");

        readFile.close();
    }

    /**
     * Calculates the size of the maze
     * @param inputFile - File to calculate
     * @throws FileNotFoundException if file is invalid.
     */
    private void calculateMaze(File inputFile) throws FileNotFoundException{
        Scanner readFile = new Scanner(inputFile);
        String line;

        while(readFile.hasNextLine()){
            line = readFile.nextLine();
            if(line.length()>mazeWidth)
                mazeWidth=line.length();
            mazeHeight++;
        }
        readFile.close();
    }

    /**
     * Load maze into a matrix.
     * @param inputFile - file to load.
     * @throws FileNotFoundException - if file is corrupt or invalid.
     */
    private void setMaze(File inputFile) throws FileNotFoundException{
        Scanner readFile= new Scanner(inputFile);
        int index=0;
        String line;
        mazeData= new char[mazeWidth][mazeHeight];

        while(readFile.hasNextLine()){
            line= readFile.nextLine();
            for(int i=0;i<line.length();i++)
                mazeData[i][index] = line.charAt(i);
            index++;
        }
        readFile.close();
    }


    /**
     * Check if a position is movable.
     * @param p - Position to be checked
     * @return boolean value representing if position is movable.
     */
    public boolean isMovable(Position p){
        try {
            if (mazeData[p.getX()][p.getY()] == MOVABLE)
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        return false;
    }

    /**
     * Method to check if a certain position is the goal.
     * @param p - Position to be checked
     * @return boolean value representing if the goal is reached
     */
    public boolean isGoal(Position p) {
        try {
            if (mazeData[p.getX()][p.getY()] == GOAL)
                return true;
        }catch(ArrayIndexOutOfBoundsException e){}
        return false;
    }

    /**
     * Method to check if a certain position is a wall.
     * @param p - Position to be checked
     * @return boolean value representing if the goal is reached
     */
    public boolean isWall(Position p) {
        try {
            if (mazeData[p.getX()][p.getY()] == WALL)
                return true;
        }catch (ArrayIndexOutOfBoundsException e ){}
        return false;
    }



    /**
     * Method to return start-position in maze
     * @return new Position representing start-position.
     * @throws Exception in case start-point does not exist.
     */
    public Position getStartPosition() throws IllegalStateException{
        for (int i = 0; i < mazeData.length; i++) {
            for (int j = 0; j < mazeData[i].length; j++) {
                if (mazeData[i][j] == START)
                    return new Position(i, j);
            }
        }
        throw new IllegalStateException("Could not find a start-point");
    }

    /**
     * Method to print the maze to the terminal.
     * Prints out an X as the current position of the robot.
     * @param p - Current position of robot.
     */
    public void printMaze(Position p){
        for (int i = 0; i < mazeData[i].length; i++) {
            for (int j = 0; j < mazeData.length; j++) {
                if(p.getX()==j&&p.getY()==i)
                    System.out.print("X");
                else
                    System.out.print(mazeData[j][i]);
            }
            System.out.print("\n");
        }
    }
}