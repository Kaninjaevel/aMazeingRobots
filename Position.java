/**
 *
 *  Created by KaninJaevel on 07/04/2016.
 */

public class Position {
    private int x;
    private int y;

    /**
     * @param x - int representing x value
     * @param y - int representing y value
     */
    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * Method that returns current x value;
     * @return x - representad as int.
     */
    public int getX(){return x;}
    /**
     * Method that returns current y value;
     * @return y - representad as int.
     */
    public int getY(){return y;}
    /**
     * Method that returns position that is
     * south of current position.
     * @return Position
     */
    public Position getPosToSouth(){return new Position(x,y+1);}
    /**
     * Method that returns position that is
     * north of current position.
     * @return Position
     */
    public Position getPosToNorth(){
        return new Position(x,y-1);
    }
    /**
     * Method that returns position that is
     * east of current position.
     * @return Position
     */
    public Position getPosToEast(){
        return new Position(x+1,y);
    }
    /**
     * Method that returns position that is
     * west of current position.
     * @return Position
     */
    public Position getPosToWest(){return new Position(x-1,y);}

    /**
     * Method to compare objects.
     * @param o - Position to be compared.
     * @return boolean representing if they are equal
     */
    public boolean equals(Object o){
        if(o==this)
            return true;
        return false;
    }

    public int hashCode(){
        int hash = 1;
        hash = (hash + getX()) * 113;
        hash = (hash + getY()) * 109;
        return hash;
    }
}