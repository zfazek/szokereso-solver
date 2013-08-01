package com.ongroa.szokereso;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Locale;
import java.text.Collator;

/**
 * Solver program of the Szokereso Android app.
 * It generates all the possible words for the given input.
 * Words must be at least 3 character long.
 * Valid table could be 2x2, 3x3, 4x4, 5x5, 6x6 big.
 * Eg. A L
 *     M O
 *  solution: ALMA, LOM, etc.
 */ 
public class SzokeresoSolver {

    /**
     * Result
     */
    Set<String> words;

    /**
     * Size of the table (eg. 2x2, 3x3)
     */
    private int size;
    
   /**
    * Characters in the words
    */
    private String letters;
    
    /**
     * Table itself.
     * Each cell in the table is a Cell object.
     */
    Cell[][] cells;

    /**
     * Constructor.
     * Initializes the result, validates the input.
     * Initializes the table.
     * @param letters input string
     * @throws IllegalArgumentException
     */
    public SzokeresoSolver(String letters) throws IllegalArgumentException {
        Collator collator = Collator.getInstance(new Locale("hu","HU"));
        words = new TreeSet<String>(collator);
        if (! isValidInput(letters))
            throw new IllegalArgumentException("Not valid input");
        this.size = (int)Math.sqrt(letters.length());
        this.letters = letters;
        initCells();
    }

    /**
     * Returns the set of words.
     * @return set of words as the solution
     */
    public Set<String> getWords() {
        initCells();
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                generateWord(x, y, "");
        return words;
    }

    /**
     * Initializes the table.
     * Each cell in the table is a Cell object.
     */
    private void initCells() {
        cells = new Cell[size][size];
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++) {
                cells[x][y] = new Cell();
                cells[x][y].letter = letters.charAt(x*size+y);
            }
    }

    /**
     * Returns true if the x, y coordinate in the table.
     * @param x x coordinate
     * @param y y coordinate
     * @return true if x,y in the table, false otherwise
     */
    public boolean inTable(int x, int y) {
        return x >= 0 &&
            x < size &&
            y >= 0 &&
            y < size;
    }

    /**
     * Generates words for the particular cell as the first letter of the word
     * @param x x coordinate
     * @param y y coordinate
     * @param word word where new character could be appended
     */
    public void generateWord(int x, int y, String word) {
        for (int dx = -1; dx <= 1; dx++)
            for (int dy = -1; dy <= 1; dy++)
                if (inTable(x + dx, y +dy))
                    if (! cells[x+dx][y+dy].used) {
                        word += cells[x+dx][y+dy].letter;
                        //System.out.printf("%d %d %s\n", dx, dy, word);
                        cells[x+dx][y+dy].used = true;
                        if (word.length() > 2) {
                            words.add(word);
                            //System.out.println(words);
                        }
                        generateWord(x + dx, y + dy, word);
                        word = word.substring(0, word.length() - 1);
                        //System.out.printf("vissza utan: %s\n", word);
                        cells[x+dx][y+dy].used = false;
                    }
    }

    /**
     * Returns true if the input string is valid.
     * It checks the valid characters and the size of the string.
     *
     * @param letters charachters of the input string
     * @return true if the input is valid, false otherwise
     */
    private boolean isValidInput(String letters) {
        String validLetters = "ABCDEFGHIJKLMNOPQRSTUXYVWZÁÉÍÓÖŐÚÜŰ";
        for (int i = 0; i < letters.length(); i++) {
            if (validLetters.indexOf(letters.charAt(i)) == -1) 
                return false;
        }
        List<Integer> list = Arrays.asList(4, 9, 16, 25, 36);
        Set<Integer> set = new HashSet<Integer>();
        for (Integer i : list) 
            set.add(i);
        return set.contains(letters.length());
    }

    public static void main(String[] args) {
        SzokeresoSolver solver = new SzokeresoSolver(args[0]);
        Set<String> result = solver.getWords();
        for (String w : result)
            System.out.println(w);
    }

}

