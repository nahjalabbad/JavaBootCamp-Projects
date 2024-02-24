import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        char[][] board = {{'*', '*', '*'},
                {'*', '*', '*'},
                {'*', '*', '*'}};

        System.out.println("Welcome to Tic Tac Toe!");
        initialzeBoard();

        int playerWins = 0;
        int computerWins = 0;

        System.out.println("Do you want to play one round or Three rounds. \nChoose 1 for one round or 3 for Three rounds: ");
        int input = inp.nextInt();
        inp.nextLine();
        switch (input) {
            case 1:
                playTTT(board, inp);
                break;

            case 3:
                for (int i = 0; i < 3; i++) {
                    resetBoard(board);
                    playTTT(board, inp);
                    char winner = determineWinner(board);
                    if (winner == 'X') {
                        playerWins++;
                    } else if (winner == 'O') {
                        computerWins++;
                    }
                }
                System.out.println("\nPlayer Wins: " + playerWins);
                System.out.println("Computer Wins: " + computerWins);
                if (playerWins>=2){
                    System.out.println("The Player has won 2 or more rounds.\nCongratulations!!! ");
                }
                else {
                    System.out.println("The Computer has won 2 or more rounds.");
                }
                break;

            default:
                System.out.println("Invalid input!");
        }

    }

    public static void playTTT(char[][] board, Scanner inp) {
        while (true) {
            playerTurn(board, inp);
            if (isGameFinished(board)) {
                break;
            }
            printBoard(board);
            CompTurn(board);
            if (isGameFinished(board)) {
                break;
            }
            printBoard(board);
        }
    }

    public static void initialzeBoard(){
        char [][] board = {{'1','2','3'},
                {'4','5','6'},
                {'7','8','9'}};
        System.out.println("\nGame Board ");
        System.out.println("|"+ board[0][0]+"|"+ board[0][1]+"|"+ board[0][2]+"|");
        System.out.println("|-+-+-|");
        System.out.println("|"+ board[1][0]+"|"+ board[1][1]+"|"+ board[1][2]+"|");
        System.out.println("|-+-+-|");
        System.out.println("|"+ board[2][0]+"|"+ board[2][1]+"|"+ board[2][2]+"|");

    }

    public static void printBoard(char[][] board) {
        System.out.println("|"+ board[0][0]+"|"+ board[0][1]+"|"+ board[0][2]+"|");
        System.out.println("|-+-+-|");
        System.out.println("|"+ board[1][0]+"|"+ board[1][1]+"|"+ board[1][2]+"|");
        System.out.println("|-+-+-|");
        System.out.println("|"+ board[2][0]+"|"+ board[2][1]+"|"+ board[2][2]+"|");
    }

    public static void resetBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '*';
            }
        }
        initialzeBoard();
    }

    public static void CompTurn(char[][] board) {
        Random random=new Random();
        int compTurn;
        while (true){
            compTurn= random.nextInt(9)+1;
            if(checkValid(board,Integer.toString(compTurn))){
                break;
            }
        }
        System.out.println("\n-Computer Turn- ");
        placeMove(board,Integer.toString(compTurn),'O');
    }

    public static void playerTurn(char[][] board, Scanner inp) {
        String input;
            while (true) {
                System.out.println("\n-Player Turn- ");
                System.out.println("Enter a number from 1 to 9 to play: ");
                input = inp.nextLine();
                if (checkValid(board, input)) {
                    break;
                }
                else {

                    System.out.println("\""+input+"\" Position is not valid, try again!");
                }
            }
            placeMove(board, input, 'X');
    }

    public static void placeMove(char[][] board, String input, char xo) {
        switch (input){
            case "1":
                board[0][0]=xo;
                break;

            case "2":
                board[0][1]= xo;
                break;

            case "3":
                board[0][2]=xo;
                break;

            case "4":
                board[1][0]=xo;
                break;

            case "5":
                board[1][1]=xo;
                break;

            case "6":
                board[1][2]=xo;
                break;

            case "7":
                board[2][0]=xo;
                break;

            case "8":
                board[2][1]=xo;
                break;

            case "9":
                board[2][2]=xo;
                break;

            default:
                System.out.println("Please input a number from 1 to 9");
        }
    }

    public static boolean checkValid(char [][] board, String input){
        switch (input){
            case "1":
                return  (board[0][0]=='*');
            case "2":
                return  (board[0][1]=='*');
            case "3":
                return  (board[0][2]=='*');
            case "4":
                return  (board[1][0]=='*');
            case "5":
                return  (board[1][1]=='*');
            case "6":
                return  (board[1][2]=='*');
            case "7":
                return  (board[2][0]=='*');
            case "8":
                return  (board[2][1]=='*');
            case "9":
                return  (board[2][2]=='*');
            default:
                return false;
        }
    }

    public static boolean checkRow(char [][] board, char xo){
        if ((board[0][0] == xo && board[0][1] == xo && board[0][2] == xo) ||
                (board[1][0] ==xo && board[1][1] == xo && board[1][2] == xo) ||
                (board[2][0] ==xo && board[2][1] == xo && board[2][2] == xo)){
            return true;
        }
        return false;
    }

    public static boolean checkColumn(char [][] board, char xo){
        if ((board[0][0] ==xo && board[1][0] == xo && board[2][0] == xo) ||
                (board[0][1] ==xo && board[1][1] == xo && board[2][1] == xo) ||
                (board[0][2] ==xo && board[1][2] == xo && board[2][2] == xo)){
            return true;
        }
        return false;
    }

    public static boolean checkDiagonal(char [][] board, char xo){
        if ((board[0][0] ==xo && board[1][1] == xo && board[2][2] == xo) ||
                (board[0][2] ==xo && board[1][1] == xo && board[2][0] == xo)){
            return true;
        }
        return false;
    }
    public static boolean whoWon(char[][] board, char xo) {
        if (checkColumn(board, xo)==checkRow(board, xo)==checkDiagonal(board,xo)){
            return true;
        }
        return false;
    }

    public static char determineWinner(char[][] board) {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] != '*') {
                    return board[i][0];
                }
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                if (board[0][j] != '*') {
                    return board[0][j];
                }
            }
        }

        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            if (board[1][1] != '*') {
                return board[1][1];
            }
        }

        return '*';
    }

    public static boolean isGameFinished(char[][] board){

        if(whoWon(board,'X')) {
            printBoard(board);
            System.out.println("The Player has won!");
            return true;
        } else if(whoWon(board,'O')) {
            printBoard(board);
            System.out.println("The computer has won!");
            return true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length ; j++) {
                if(board[i][j]== '*'){
                    return false;
                }
            }
        }
        printBoard(board);
        System.out.println("The Game is done in a tie between player and computer.");
        return true;
    }




}