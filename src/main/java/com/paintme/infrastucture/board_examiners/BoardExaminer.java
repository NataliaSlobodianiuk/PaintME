package com.paintme.infrastucture.board_examiners;

import java.util.List;
import java.util.Map;

public interface BoardExaminer {
    char findWinningSymbol(String cells);

    List<Integer> findFreeCells(String cells);
    List<Integer> findCellsByColor(String cells, char color);

    Map<Integer[], Character> findCombinationOfTwo(String cells);
    Map<Integer[], Character> findCombinationOfThree(String cells);
    Map<Integer[], Character> findCombinationOfFour(String cells);

    boolean isFinished(String cells);
}
