import org.junit.Test;

import java.io.File;
import java.io.FileReader;

import static junit.framework.TestCase.assertEquals;

/**
 *
 *  Created by KaninJaevel on 16/04/2016.
 */
public class MazeJUnitTest {

    /**
     * Tests if the maze is able to be created with a
     * correctly implemented maze-file.
     * @throws Exception
     */
    @Test
    public void shouldBeAbleToCreate() throws Exception{
        File inputFile= new File(System.getProperty("user.dir")
                +"/testMazes/correctMaze.txt");
        new Maze(inputFile);
    }

    /**
     * Tests if the maze correctly throws if maze-file is
     * implemented wrong.
     * @throws Exception
     */
    @Test (expected = Exception.class)
    public void createWithInvalidSymbols() throws Exception {
        File inputFile= new File(System.getProperty("user.dir")
                +"/testMazes/incorrectMaze.txt");
        new Maze(inputFile);
    }
    /**
     * Tests if the maze correctly throws if maze-file is
     * implemented wrong.
     * @throws Exception
     */
    @Test (expected = Exception.class)
    public void createWithoutGoalOrStart() throws Exception {
        File inputFile= new File(System.getProperty("user.dir")
                +"testMazes/incorrectMaze2.txt");
        new Maze(inputFile);
    }


    /**
     * Tests if the getStartPosition method works as intended.
     * @throws Exception
     */
    @Test
    public void testGetStartPosition() throws Exception{
        File inputFile= new File(System.getProperty("user.dir")
                +"/testMazes/correctMaze.txt");
        Maze m = new Maze(inputFile);
        assertEquals(2,m.getStartPosition().getX());
        assertEquals(0,m.getStartPosition().getY());
    }

    /**
     * Tests if isGoal works as intended.
     * @throws Exception
     */
    @Test
    public void testisGoal() throws Exception {
        File inputFile= new File(System.getProperty("user.dir")
                +"/testMazes/correctMaze.txt");
        Maze m = new Maze(inputFile);
        Position p = new Position(0,0);

        if(m.isGoal(p))
            throw new Exception("isGoal not working as expected.");

        p = new Position(4,2);
        if(!m.isGoal(p))
            throw new Exception("Goal is not at expected position");

    }

    /**
     * Tests if isMovable works as intended.
     * @throws Exception
     */
    @Test
    public void testIsMovable() throws Exception {
        File inputFile= new File(System.getProperty("user.dir")
                +"/testMazes/correctMaze.txt");
        Maze m = new Maze(inputFile);
        Position p = new Position(0,0);

        if(m.isMovable(p))
            throw new Exception("isMovable is not working as expected.");

        p = new Position(1,1);
        if(!m.isMovable(p))
            throw new Exception("isMovable is not working as expected.");
    }

    @Test
    public void shouldBeAbleToPrint() throws Exception{
        File inputFile= new File(System.getProperty("user.dir")
                +"/testMazes/correctMaze.txt");
        Maze m = new Maze(inputFile);
        m.printMaze(m.getStartPosition());
    }

}

