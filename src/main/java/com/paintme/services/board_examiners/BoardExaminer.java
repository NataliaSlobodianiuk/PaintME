package com.paintme.services.board_examiners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BoardExaminer {
    char findWinningSymbol(String cells);

    default Integer[] findFreeCells(String cells) {
        List<Integer> freeCells = new ArrayList<>();

        for (int i = 0; i < cells.length(); i++)
        {
            if (cells.charAt(i) == '-')
            {
                freeCells.add(i);
            }
        }

        return (Integer[]) freeCells.toArray();
    }

    default Integer[] findCellsByColor(String cells, char color) {
        List<Integer> cellsByColor = new ArrayList<>();

        for (int i = 0; i < cells.length(); i++)
        {
            if (cells.charAt(i) == color)
            {
                cellsByColor.add(i);
            }
        }

        return (Integer[]) cellsByColor.toArray();
    }

    Map<Integer[], Character> findCombinationOfTwo(String cells);
    Map<Integer[], Character> findCombinationOfThree(String cells);

    default boolean isFinished(String cells){
        return (this.findFreeCells(cells).length == 0);
    }
}
