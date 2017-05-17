package com.paintme.infrastucture;

import com.paintme.infrastucture.board_examiners.BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square3BoardExaminer;

public class BoardExaminerFactory{
    public BoardExaminer getBoardExaminer (String boardType, int cellsLength) {
        BoardExaminer boardExaminer = null;
        
        switch (boardType.toUpperCase()) {
            case "SQUARE":
                switch (cellsLength) {
                    case 9:
                        boardExaminer = new Square3BoardExaminer();
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "Unknown size of board.");
                }
                break;
            default:
                throw new IllegalArgumentException(
                        "Unknown type of board.");
        }

        return boardExaminer;
    }
}
