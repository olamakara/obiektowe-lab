package agh.ics.oop;

public class OptionsParser {

    public MoveDirection[] parse(String[] moves) {

        MoveDirection[] resultMoves = new MoveDirection[moves.length];
        for (int i = 0; i < moves.length; i++) {
            switch (moves[i]) {
                case "f", "forward":
                    resultMoves[i] = MoveDirection.FORWARD;
                    break;
                case "b", "backward":
                    resultMoves[i] = MoveDirection.BACKWARD;
                    break;
                case "r", "right":
                    resultMoves[i] = MoveDirection.RIGHT;
                    break;
                case "l", "left":
                    resultMoves[i] = MoveDirection.LEFT;
                    break;
                default: throw new IllegalArgumentException(moves[i] +  " is not legal move specification");
            }
        }
        return resultMoves;
    }
}
