package com.ongroa.szokereso;

/**
 * Cell representation of the cell of the table.
 */
public class Cell {

    /**
     * This cell is already used in the word.
     */
    boolean used;
    
    /**
     * Character of the given cell.
     */
    char letter;

    public Cell() {
        used = false;
    }

}
