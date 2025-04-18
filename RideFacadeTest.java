import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RideFacadeTest {
    RideFacade rf;

    @Before
    public void createRider(){
        rf = new RideFacade();
    }

    @Test
    public void getPriceTest(){
        assertEquals("Invalid service type", rf.getPrice("carXXL",0));
        assertEquals(5.5, rf.getPrice("carPOOL",0));
        assertEquals(16.2, rf.getPrice("carBlack",0));
        assertEquals(8.8, rf.getPrice("carX",0));
    }
}
