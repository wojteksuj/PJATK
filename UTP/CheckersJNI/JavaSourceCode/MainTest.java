import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Board board;

    @BeforeEach
    public void resetBoardState() {
        board = new Board();
        int[][] initialBoard = {
                {0, 2, 0, 2, 0, 2, 0, 2},
                {2, 0, 2, 0, 2, 0, 2, 0},
                {0, 2, 0, 2, 0, 2, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0}
        };
        board.setBoard(initialBoard);
        board.setTour(1);
    }

    @Test
    public void testDrawingBoard() {
        int[][] myBoard = board.getBoard();
        int[][] expectedBoard =
                {{0, 2, 0, 2, 0, 2, 0, 2},
                        {2, 0, 2, 0, 2, 0, 2, 0},
                        {0, 2, 0, 2, 0, 2, 0, 2},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1, 0, 1, 0},
                        {0, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 0, 1, 0}};
        assertArrayEquals(expectedBoard, myBoard);
    }

    @Test
    public void testCheckingThePiece() {
        board.updateBoard(7, 0);
        assertEquals(3, board.getBoard()[7][0]);
    }

    @Test
    public void testValidMove() {
        int[][] testBoard2 = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};
        board.setBoard(testBoard2);
        board.updateBoard(5, 2);
        assertEquals(3, board.getBoard()[5][2]);
        board.updateBoard(4, 1);
        assertEquals(1, board.getBoard()[4][1]);
        assertEquals(0, board.getBoard()[5][2]);
    }

    @Test
    public void testCaptureMove() {
        int[][] testBoard3 = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};
        board.setBoard(testBoard3);
        board.updateBoard(5, 2);
        assertEquals(3, board.getBoard()[5][2]);
        board.updateBoard(3, 4);
        assertEquals(0, board.getBoard()[5][2]);
        assertEquals(0, board.getBoard()[4][3]);
        assertEquals(1, board.getBoard()[3][4]);
    }

    @Test
    public void testPromotion() {
        int[][] testBoard4 = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};
        board.setBoard(testBoard4);
        board.updateBoard(1,1);
        assertEquals(3,board.getBoard()[1][1]);
        board.updateBoard(0,0);
        assertEquals(0,board.getBoard()[1][1]);
        assertEquals(-1,board.getBoard()[0][0]);
    }

    @Test
    public void testMultiCapturing(){
        int[][] testBoard5 = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 2, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}};
        board.setBoard(testBoard5);
        board.updateBoard(7,0);
        assertEquals(3,board.getBoard()[7][0]);
        board.updateBoard(5,2);
        assertEquals(3,board.getBoard()[5][2]);
    }

    @Test
    public void testDameMovement(){
        int[][] testBoard6 = {
                {-1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};
        board.setBoard(testBoard6);
        board.updateBoard(0,0);
        assertEquals(-3,board.getBoard()[0][0]);
        board.updateBoard(1,1);
        assertEquals(-1,board.getBoard()[1][1]);
    }

}


