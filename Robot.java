import java.io.FileNotFoundException;

/**
 * Assignment 2 in the course "Object oriented programming"
 *
 * An abstract class for Robots,
 * Needs to be inherited and customized with at least a move-method.
 *
 * @author Thomas Sarlin <thsa0043/id15tsn>
 * @version 1.0 5/4-2016
 *
 *
 */

public abstract class Robot {
    private Position p;
    protected Maze m;

    /**
     * Constructor for Robot
     * @param m - file with maze instructions
     * @throws Exception if no start-point is found.
     */
    public Robot(Maze m) throws IllegalStateException,FileNotFoundException {
        this.m=m;
        this.setCurrentPosition(this.m.getStartPosition());
    }
    /**
     * Method to move the robot.
     * Must be set for each inheritance.
     */
    public abstract void move() throws IllegalStateException;
    /**
     * Method to return current position.
     * @return current position
     * */

    public Position getCurrentPosition(){return p;}

    /**
     * @return position north of current position
     */
    protected Position getNorth(){return getCurrentPosition().getPosToNorth();}

    /**
     * @return position south of current position
     */
    protected Position getSouth(){return getCurrentPosition().getPosToSouth();}

    /**
     * @return position east of current position
     */
    protected Position getEast(){return getCurrentPosition().getPosToEast();}

    /**
     * @return position west of current position
     */
    protected Position getWest(){return getCurrentPosition().getPosToWest();}


    /**
     * Method to set current position.
     * @return current position
     * */
    protected void setCurrentPosition(Position p){this.p=p;}
    /**
     * Method to check if Robot has reached its goal.
     * @return boolean
     * */
    public boolean hasReachedGoal(){return m.isGoal(getCurrentPosition());}
}
