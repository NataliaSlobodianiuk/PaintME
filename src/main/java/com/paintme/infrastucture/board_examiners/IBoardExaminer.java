package com.paintme.infrastucture.board_examiners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IBoardExaminer {
    char findWinningSymbol(String cells);

    default List<Integer> findFreeCells(String cells) {
        List<Integer> freeCells = new ArrayList<>();

        for (int i = 0; i < cells.length(); i++)
        {
            if (cells.charAt(i) == '-')
            {
                freeCells.add(i);
            }
        }

        return freeCells;
    }

    default List<Integer> findCellsByColor(String cells, char color) {
        List<Integer> cellsByColor = new ArrayList<>();

        for (int i = 0; i < cells.length(); i++)
        {
            if (cells.charAt(i) == color)
            {
                cellsByColor.add(i);
            }
        }

        return cellsByColor;
    }

    Map<Integer[], Character> findCombinationOfTwo(String cells);
    Map<Integer[], Character> findCombinationOfThree(String cells);
    Map<Integer[], Character> findCombinationOfFour(String cells);

    default boolean isFinished(String cells){
        return (this.findFreeCells(cells).size() == 0 || this.findWinningSymbol(cells) != '-');
    }
}
