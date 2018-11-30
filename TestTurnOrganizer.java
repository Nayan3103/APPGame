/**
 * This is a test class and it is for TurnOrganizer
 * @author Nayan Girase
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.risk.model.GameBoard;
import com.risk.model.Player;
import com.risk.model.TurnOrganizer;
import com.risk.utility.TurnPhases;

public class TestTurnOrganizer {
	GameBoard game;
	
	@Before
	public void testBefore() {
		System.out.println("@BeforeClass");
		game = new GameBoard();
	}
		
		

		@Test
		public void testGetNextPhase() {
			TurnPhases p= game.turnOrganizer.GetNextPhase();
			
			assertEquals(p, TurnPhases.Reinforcement);
			
		}
		@Test
	public void testGetCurrentPhase() {
			TurnPhases p = game.turnOrganizer.GetCurrentPhase();
				
			assertEquals(p, TurnPhases.Startup);
	
	
	}
	
}


