import java.io.FileNotFoundException;
import java.util.*;
/**
 * Created by KaninJaevel on 07/04/2016.
 *
 */
public class MemoryRobot extends Robot {
    private Stack<Position> movement = new Stack<>();
    private Hashtable<Integer, Position> visitedPositions = new Hashtable<>();

    /**
     *
     * @param m - file in maze format.
     * @throws IllegalStateException when no start-point is found
     */

    public MemoryRobot(Maze m) throws IllegalStateException
            ,FileNotFoundException{
        super(m);
        this.HashCurrentPosition();
    }

    /**
     * Moves the robot one step and keeps track of the robots movements.
     */
    @Override
    public void move(){
        if (!checkAdjacentForGoal()) {
            if (checkAdjacentForMovables()) {
                movement.push(getCurrentPosition());
                addMovables();

                if(!movement.isEmpty()) {
                    setCurrentPosition(movement.peek());
                    HashCurrentPosition();
                }
                else
                    System.out.println("Stack empty" +
                            ", Couldn't find end of maze.");
            }

            else{
                if(!movement.isEmpty())
                    setCurrentPosition(movement.pop());
                else
                    System.out.println("Stack empty" +
                            ", Couldn't find end of maze.");
            }
        }
    }

    /**
     * adds the current position to the hashtable.
     */
    private void HashCurrentPosition(){
            visitedPositions.putIfAbsent(getCurrentPosition().hashCode()
                    ,getCurrentPosition());
    }

    /**
     * Checks if there are any movables adjacent to current position.
     * @return boolean representing if there are any movables.
     */
    private boolean checkAdjacentForMovables(){
        boolean hasAdjacent=false;

        try {
            if (isAValidMovable(getNorth()))
                hasAdjacent = true;
        }catch(ArrayIndexOutOfBoundsException e){}

        try {
            if (isAValidMovable(getEast()))
                hasAdjacent = true;
        }catch(ArrayIndexOutOfBoundsException e){}

        try {
            if (isAValidMovable(getSouth()))
                hasAdjacent = true;
        }catch(ArrayIndexOutOfBoundsException e){}

        try {
            if (isAValidMovable(getWest()))
                hasAdjacent = true;
        }catch(ArrayIndexOutOfBoundsException e){}

        return hasAdjacent;
    }

    /**
     * Adds adjacent movables to the stack.
     * If there are multiple movables, current position is
     * added to the stack in between movables
     * so the robot avoids cutting corners.
     */

    private void addMovables(){
        boolean hasBeenPushed = false;
        try {
            if (isAValidMovable(getNorth())) {
                movement.push(getNorth());
                hasBeenPushed = true;
            }
        }catch(ArrayIndexOutOfBoundsException e){}

        try{
            if(isAValidMovable(getWest())) {
                if(hasBeenPushed)
                    movement.push(getCurrentPosition());
                movement.push(getWest());
                hasBeenPushed=true;
            }
        }catch(ArrayIndexOutOfBoundsException e){}

        try {
            if (isAValidMovable(getSouth())){
                if(hasBeenPushed)
                    movement.push(getCurrentPosition());
                movement.push(getSouth());
                hasBeenPushed=true;
            }
        }catch(ArrayIndexOutOfBoundsException e){}

        try{
            if(isAValidMovable(getEast())) {
                if (hasBeenPushed)
                    movement.push(getCurrentPosition());
                movement.push(getEast());
            }
        }catch(ArrayIndexOutOfBoundsException e){}

    }

    /**
     * Checks if adjacent positions contains the end of the maze
     * If end is adjacent, robot moves to that position.
     * @return boolean representing if end was found.
     */
    private boolean checkAdjacentForGoal(){
        boolean goal=false;
        try{
            if(m.isGoal(getNorth())) {
                setCurrentPosition(getNorth());
                goal = true;
            }
        }catch(ArrayIndexOutOfBoundsException e){}

        try{
            if(m.isGoal(getEast())){
                setCurrentPosition(getEast());
                goal = true;
            }
        }catch(ArrayIndexOutOfBoundsException e){}

        try{
            if(m.isGoal(getSouth())){
                setCurrentPosition(getSouth());
                goal = true;
            }
        }catch(ArrayIndexOutOfBoundsException e){}

        try{
            if(m.isGoal(getWest())){
                setCurrentPosition(getWest());
                goal = true;
            }
        }catch(ArrayIndexOutOfBoundsException e){}

        return goal;
    }

    /**
     * Checks if position p is a valid movable.
     * @param p - position to check
     * @return boolean representing if p is a valid movable.
     */
    private boolean isAValidMovable(Position p){
     return m.isMovable(p)&&!isVisited(p);
    }

    /**
     * checks if position p is visited
     * @param p - position to check
     * @return boolean representing if p is visited.
     */
    private boolean isVisited(Position p){
        return visitedPositions.containsKey(p.hashCode());
    }
}
