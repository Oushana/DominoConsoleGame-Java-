package game;

import java.util.ArrayList;

/**
 * Created by Oksana on 12/2/2015.
 */
 class Chain {

     Chain() {

    }

    /**
     * @param currentChain    - consist of one element at beginning - element from which we start building longest chain
     * @param outOfChainTiles - all of tiles chosen by "user"
     * @return method returns longest chain calculated beginning from first element in currentChain list
     */
    private ArrayList<Tile> buildLongestChainFromOneTile(ArrayList<Tile> currentChain, ArrayList<Tile> outOfChainTiles) {

        ArrayList<Tile> outOfChainTilesTemp = new ArrayList<>(outOfChainTiles);
        Tile domino; // = new Tile();

        for (Tile outOfChainTile : outOfChainTiles) {
            domino = outOfChainTile;
            outOfChainTilesTemp.remove(domino);
            currentChain.add(domino);
            //System.out.print("\r\n test chain =" + testChain(currentChain) + " ");
            //printChain(currentChain);
            if (testChain(currentChain)) {
                return buildLongestChainFromOneTile(currentChain, outOfChainTilesTemp); //recursion
            } else {
                currentChain.remove(domino);
                outOfChainTilesTemp.add(domino);
            }
        }
        return currentChain;
    }

    /**
     * method runs through all tiles and check whether they make a domino chain
     * @return true if chain is a domino chain and return false if not
     */
    private boolean testChain(ArrayList<Tile> tiles) {

        if (tiles.size() == 1) return true;

        boolean isOk = false;

        for (int i = 1; i < tiles.size(); i++) {
            if (tiles.get(i - 1).getRightSide() == tiles.get(i).getLeftSide()) {
                isOk = true;
            } else if (tiles.get(i - 1).getRightSide() == tiles.get(i).getRightSide()) {
                tiles.get(i).turnAround();
                isOk = true;
            } else {
                isOk = false;
                return isOk;
            }
        }
        return isOk;
    }

    /**
     * method compares longest chains among all tiles, going one by one through them
     * @return longest chain among all tiles chosen by "user"
     */
    public ArrayList<Tile> getLongestChain(ArrayList<Tile> userTiles) {

        ArrayList<Tile> userTilesTemp = new ArrayList<>(userTiles);
        ArrayList<Tile> longestChain = new ArrayList<>();
        ArrayList<Tile> longestChainTemp = new ArrayList<>();

        Tile tile0 = userTilesTemp.get(0);
        int userTilesSize = userTiles.size();

        longestChain.add(tile0);
        userTilesTemp.remove(tile0);

        longestChain = buildLongestChainFromOneTile(longestChain, userTilesTemp);
        userTilesTemp = new ArrayList<>(userTiles);
        //renew userTiles

        for (int i = 1; i < userTilesSize; i++) {
            Tile tile = userTilesTemp.get(i);
            longestChainTemp.add(tile);
            userTilesTemp.remove(tile);
            longestChainTemp = buildLongestChainFromOneTile(longestChainTemp, userTilesTemp);

            if (longestChainTemp.size() > longestChain.size() && testChain(longestChainTemp))
                longestChain = new ArrayList<>(longestChainTemp);

            userTilesTemp.add(i, tile);
            if (longestChainTemp.size() == userTilesSize) return longestChain;
            longestChainTemp.clear();
        }

        for (int i = 0; i < userTilesSize; i++) {
            Tile tile = userTilesTemp.get(i);
            tile.turnAround();
            longestChainTemp.add(tile);

            userTilesTemp.remove(tile);
            longestChainTemp = buildLongestChainFromOneTile(longestChainTemp, userTilesTemp);

            if (longestChainTemp.size() > longestChain.size() && testChain(longestChainTemp))
                longestChain = new ArrayList<>(longestChainTemp);

            userTilesTemp.add(i, tile);
            if (longestChainTemp.size() == userTilesSize) return longestChain;
            longestChainTemp.clear();

        }
        //System.out.print("\r\nFINAL1- ");
        // printTiles(longestChain);
        longestChain = makeEasyToSeeDominoChainLook(longestChain);
        //System.out.print("\r\nFINAL2- ");
        //printTiles(longestChain);
        return longestChain;
    }

    /**
     * method returns outOfChain tiles which are left after creating longest chain
     */
    public ArrayList<Tile> getOutOfChainTiles(ArrayList<Tile> userTiles, ArrayList<Tile> longestChain) {

        ArrayList<Tile> outOfChainTiles = new ArrayList<>(userTiles);

        for (Tile t : longestChain) {
            if (outOfChainTiles.contains(t))
                outOfChainTiles.remove(t);
        }
        return outOfChainTiles;
    }

    private ArrayList<Tile> makeEasyToSeeDominoChainLook(ArrayList<Tile> tiles) {

        for (int i = 1; i < tiles.size(); i++) {
            if (tiles.get(i - 1).getRightSide() == tiles.get(i).getLeftSide()) {
                //do nothing - means that tiles are in right position
            } else if (tiles.get(i - 1).getLeftSide() == tiles.get(i).getRightSide()) {
                tiles.get(i).turnAround();
                tiles.get(i - 1).turnAround();
            } else if (tiles.get(i - 1).getRightSide() == tiles.get(i).getRightSide()) {
                tiles.get(i).turnAround();
            } else if (tiles.get(i - 1).getLeftSide() == tiles.get(i).getLeftSide()) {
                tiles.get(i - 1).turnAround();
            }
        }
        return tiles;
    }

    public void printTiles(ArrayList<Tile> tiles) {
        for (Tile t : tiles) {
            t.printTile();
        }
    }

}
