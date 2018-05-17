package com.paintme.infrastucture.board_examiners;

import java.util.ArrayList;
import java.util.List;

public abstract class BoardExaminerBase implements BoardExaminer {
    protected int winAmount;

    @Override
    public List<Integer> findFreeCells(String cells) {
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

    @Override
    public List<Integer> findCellsByColor(String cells, char color) {
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

    @Override
    public boolean isFinished(String cells){
        return (this.findFreeCells(cells).size() == 0 || this.findWinningSymbol(cells) != '-');
    }
}
