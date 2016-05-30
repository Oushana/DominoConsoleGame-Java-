package game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Oksana on 12/2/2015.
 */
 class DominoSet {

   // public static final int standardNumOfTiles = 28;
    private ArrayList<Tile> dominos = new ArrayList<>();
    private Random gen = new Random();

     DominoSet() {
         createStandard28DominoSet();
    }


    private void createStandard28DominoSet() {
        //creating list of 28 tiles starting with 0|0 to  6|6
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                dominos.add(new Tile(i, j));
            }
        }
    }

    public ArrayList<Tile> getDominos() {
        return dominos;
    }

    public ArrayList<Tile> getTilesFromDomino(int neededNumOfTiles) {
        ArrayList<Tile> tiles = new ArrayList<>();
        int k;
        if (dominos.size() != 0) {
            for (int i = 0; i < neededNumOfTiles; i++) {
                k = gen.nextInt(dominos.size());
                tiles.add(dominos.get(k));
                dominos.remove(k);
            }
        }
        return tiles;
    }

}
