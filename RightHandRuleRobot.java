import java.io.FileNotFoundException;

/**
 *
 *
 *
 */

public class RightHandRuleRobot extends Robot {
    private char lastMove;
    private boolean startCheckStatus=false;
    public RightHandRuleRobot(Maze m) throws IllegalStateException
            ,FileNotFoundException {
        super(m);
    }
    /**
     * This method moves the robot one step according to the
     * righ hand rule. In other words, the robot keeps the
     * "right hand" on the wall and follows the wall until
     * it reaches it's goal.
     * If no walls are adjacent to the start position, the robot
     * moves north until it reaches a wall.
     * @throws Exception if no adjacent positions were movable.
     */
    @Override
    public void move() throws IllegalStateException {
        int whileCount= 0;
        boolean didMove=false;

        if(startCheck()) {
            while (!didMove && whileCount < 2) {
                switch (lastMove) {
                    case 'N':
                        if (tryGoingEast()) {
                            didMove = true;
                            break;
                        }
                    case 'W':
                        if (tryGoingNorth()) {
                            didMove = true;
                            break;
                        }
                    case 'S':
                        if (tryGoingWest()) {
                            didMove = true;
                            break;
                        }
                    case 'E':
                        if (tryGoingSouth()) {
                            didMove = true;
                            break;
                        }
                    default:
                        lastMove = 'N';
                        whileCount++;
                }
            }
            if (whileCount >= 2)
                throw new IllegalStateException("No movables adjacent to " +
                        "current position");
        }
        else{
            tryGoingNorth();
            setLastMoved('S');
        }
    }

    private boolean startCheck(){

        if(!startCheckStatus){
            try{
                if(m.isWall(getNorth()))
                    startCheckStatus=true;
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                if(m.isWall(getEast()))
                    startCheckStatus=true;
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                if(m.isWall(getSouth()))
                    startCheckStatus=true;
            }catch(ArrayIndexOutOfBoundsException e){}
            try{
                if(m.isWall(getWest()))
                    startCheckStatus = true;
            }catch(ArrayIndexOutOfBoundsException e){}
        }
        return startCheckStatus;
    }
    /**
     * method to try moving the robot north.
     * @return boolean representing if the robot was able to move.
     */

    private boolean tryGoingNorth(){
        boolean ableToMove = false;
        try {
            if (isAValidMovable(getNorth())) {
                setCurrentPosition(getNorth());
                setLastMoved('N');
                ableToMove = true;
            }
        } catch (ArrayIndexOutOfBoundsException Exception) {}
        return ableToMove;
    }

    /**
     * method to try moving the robot west.
     * @return boolean representing if the robot was able to move.
     */

    private boolean tryGoingWest(){
        boolean ableToMove = false;
        try {
            if (isAValidMovable(getWest())){
                setCurrentPosition(getWest());
                setLastMoved('W');
                ableToMove = true;
            }
        } catch (ArrayIndexOutOfBoundsException Exception) {}
        return ableToMove;
    }

    /**
     * method to try moving the robot south.
     * @return boolean representing if the robot was able to move.
     */
    private boolean tryGoingSouth(){
        boolean ableToMove = false;
        try {
            if (isAValidMovable(getSouth())) {
                setCurrentPosition(getSouth());
                setLastMoved('S');
                ableToMove=true;
            }
        } catch (ArrayIndexOutOfBoundsException Exception){}
        return ableToMove;
    }

    /**
     * method to try moving the robot east.
     * @return boolean representing if the robot was able to move.
     */
    private boolean tryGoingEast(){
        boolean ableToMove = false;
        try {
            if (isAValidMovable(getEast())) {
                setCurrentPosition(getEast());
                setLastMoved('E');
                ableToMove = true;
            }
        } catch (ArrayIndexOutOfBoundsException Exception) {}
        return ableToMove;
    }
    /**
     * Checks if position p is movable.
     * @param p - position to check
     * @return boolean representing result.
     */
    private boolean isAValidMovable(Position p){
        return m.isMovable(p)||m.isGoal(p);
    }

    /**
     * A Method to keep track of the last move of the robot
     * @param c - character representing which direction
     * the robot has moved, (S,E,W,N)
     */

    private void setLastMoved(char c){lastMove=c;}
}