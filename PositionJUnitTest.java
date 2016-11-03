import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by KaninJaevel on 16/04/2016.
 */
public class PositionJUnitTest {

    /**
     * Tests if we able to create a new Position.
     * @throws Exception
     */
    @Test
    public void shouldBeAbleToCreate() throws Exception {
        new Position(1,2);
    }

    /**
     * Tests if getX works as intended.
     * @throws Exception
     */
    @Test
    public void shouldBeAbleToGetX() throws Exception {
        Position p = new Position(1,2);
        assertEquals(p.getX(),1);
    }

    /**
     * Tests if getY works as intended.
     * @throws Exception
     */
    @Test
    public void shouldBeAbleToGetY() throws Exception {
        Position p = new Position(1,2);
        assertEquals(p.getY(),2);
    }

    /**
     * Tests if getPosToNorth works as intended
     * @throws Exception
     */
    @Test
    public void shouldBeAbleToGetNorth() throws Exception {
        Position p = new Position(1,2);
        assertEquals(p.getPosToNorth().getX(),1);
        assertEquals(p.getPosToNorth().getY(),1);
    }

    /**
     * Tests if getPosToEast works as intended
     * @throws Exception
     */
    @Test
    public void shouldBeAbleToGetEast() throws Exception {
        Position p = new Position(1,2);
        assertEquals(p.getPosToEast().getX(),2);
        assertEquals(p.getPosToEast().getY(),2);
    }

    /**
     * Tests if getPosToSouth works as intended
     * @throws Exception
     */
    @Test
    public void shouldBeAbleToGetSouth() throws Exception {
        Position p = new Position(1,2);
        assertEquals(p.getPosToSouth().getX(),1);
        assertEquals(p.getPosToSouth().getY(),3);
    }
    /**
     * Tests if getPosToWest works as intended
     * @throws Exception
     */
    @Test
    public void shouldBeAbleToGetWest() throws Exception {
        Position p = new Position(1,2);
        assertEquals(p.getPosToWest().getX(),0);
        assertEquals(p.getPosToWest().getY(),2);
    }

    /**
     * Tests if isEqual works as intended
     * @throws Exception
     */
    @Test
    public void shouldBeEquals() throws Exception {
        Position p = new Position(1,2);
        Position q = new Position(1,2);
        p.equals(q);
    }

    /**
     * Tests if isEquals works as intended when
     * trying to compare two different Positions.
     * @throws Exception
     */
    @Test
    public void shouldNotBeEquals() throws Exception {
        Position p = new Position(1,2);
        Position q = new Position(12,22);
        if(p.equals(q))
            throw new Exception("returned true when expected false");
    }

    /**
     * Tests if hashCode works as intended
     * @throws Exception
     */
    @Test
    public void hashCodeTest(){
        Position p = new Position(2,2);
        assertEquals(p.hashCode(),37169);
    }
}
