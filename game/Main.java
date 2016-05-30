package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Oksana on 12/2/2015.
 */
class Main {

    public static void main(String[] args) {
        new Main().start();

    }

    private void start() {
        System.out.print(Messages.GAME_START_SET);

        DominoSet dominos = new DominoSet();
        Chain chain = new Chain();
        int userInputNumber = getNumberFromConsole();

        ArrayList<Tile> userTiles = dominos.getTilesFromDomino(userInputNumber);

        System.out.print(Messages.USER_PICKED + userInputNumber + Messages.TILES + Messages.SEMICOLON);
        printTiles(userTiles);

        ArrayList<Tile> longestChain = chain.getLongestChain(userTiles);
        ArrayList<Tile> outOfChainTiles = chain.getOutOfChainTiles(userTiles, longestChain);

        System.out.print(Messages.LONGEST_CHAIN + longestChain.size() + Messages.SEMICOLON);
        printTiles(longestChain);

        printOutOfChainTiles(outOfChainTiles);
    }

    private int getNumberFromConsole() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = 0, userInput = -1;

        try {
            System.out.print(Messages.USER_INPUT);
            userInput = Integer.parseInt(reader.readLine());
            if (userInput > 0 && userInput < 29) {
                number = userInput;
            } else {
                System.out.println(Messages.WRONG_INPUT);
                number = getNumberFromConsole();
            }
        } catch (Exception e) {
            System.out.println(Messages.WRONG_INPUT);
            number = getNumberFromConsole();
        }
        return number;
    }

    private void printTiles(ArrayList<Tile> tiles) {
        for (Tile t : tiles) {
            t.printTile();
        }
    }

    private void printOutOfChainTiles(ArrayList<Tile> outOfChainTiles) {

        if (outOfChainTiles.size() != 0) {
            System.out.print(Messages.OUT_OF_CHAIN + outOfChainTiles.size() + Messages.SEMICOLON);
            printTiles(outOfChainTiles);
        } else
            System.out.println(Messages.NO_OUT_OF_CHAIN);
    }
}
