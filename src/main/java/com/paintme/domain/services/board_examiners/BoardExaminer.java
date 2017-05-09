package com.paintme.domain.services.board_examiners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface BoardExaminer {
    char findWinningSymbol(String cells);

    default Integer[] findFreeCells(String cells) {
        List<Integer> freeCells = new ArrayList<Integer>();

        for (int i = 0; i < cells.length(); i++)
        {
            if (cells.charAt(i) == '-')
            {
                freeCells.add(i);
            }
        }

        return (Integer[]) freeCells.toArray();
    }

    Map<Integer[], Character> findCombinationOfTwo(String cells);
    Map<Integer[], Character> findCombinationOfThree(String cells);

    default boolean isFinished(String cells){
        return (this.findWinningSymbol(cells) != '\u0000');
    }
}
