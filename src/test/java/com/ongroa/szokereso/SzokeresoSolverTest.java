package com.ongroa.szokereso;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;

public class SzokeresoSolverTest {

    SzokeresoSolver solver;
    int size;

    @Before
    public void setup() {
    }

   @Test(expected = IllegalArgumentException.class)
    public void test_invalidInput1() {
        solver = new SzokeresoSolver("");
    }
   
   @Test(expected = IllegalArgumentException.class)
    public void test_invalidInput2() {
        solver = new SzokeresoSolver("AA");
    }
   
   @Test(expected = IllegalArgumentException.class)
    public void test_invalidInput3() {
        solver = new SzokeresoSolver("aaaa");
    }
   
   @Test(expected = IllegalArgumentException.class)
    public void test_invalidInput4() {
        solver = new SzokeresoSolver("A");
    }
   
    @Test
    public void test_validInput() {
        solver = new SzokeresoSolver("AAAA");
        solver = new SzokeresoSolver("ABCD");
        solver = new SzokeresoSolver("ABCDEFGHI");
        solver = new SzokeresoSolver("ABCDEFGHIJKLMNOP");
        solver = new SzokeresoSolver("ABCDEFGHIJKLMNOPQRSTUXYWV");
        solver = new SzokeresoSolver("ABCDEFGHIJKLMNOPQRSTUXYWVXABCDEFGHIJ");
    }

    @Test
    public void test_initCells() {
        solver = new SzokeresoSolver("ABCD");
        assertEquals(solver.cells[0][0].letter, 'A');
        assertEquals(solver.cells[0][1].letter, 'B');
        assertEquals(solver.cells[1][0].letter, 'C');
        assertEquals(solver.cells[1][1].letter, 'D');
        assertFalse(solver.cells[0][0].used);
        assertFalse(solver.cells[0][1].used);
        assertFalse(solver.cells[1][0].used);
        assertFalse(solver.cells[1][1].used);
    }

    @Test
    public void test_inTable() {
        solver = new SzokeresoSolver("ABCD");
        assertTrue(solver.inTable(0,0));
        assertTrue(solver.inTable(0,1));
        assertTrue(solver.inTable(1,0));
        assertTrue(solver.inTable(1,1));
        assertFalse(solver.inTable(-1,0));
        assertFalse(solver.inTable(-1,0));
        assertFalse(solver.inTable(-2,-1));
        assertFalse(solver.inTable(-1,2));
        assertFalse(solver.inTable(2,0));
        assertFalse(solver.inTable(3,3));
    }
    @Test
    public void test_output() {
        System.out.println();
        Set<String> result;
        solver = new SzokeresoSolver("ABCDEFGHI");
        result = solver.getWords();
        assertEquals(10256, result.size());
        solver = new SzokeresoSolver("ABCDEFGHA");
        result = solver.getWords();
        assertEquals(9947, result.size());
        solver = new SzokeresoSolver("ABCD");
        result = solver.getWords();
        assertEquals(48, result.size());
    }
}
