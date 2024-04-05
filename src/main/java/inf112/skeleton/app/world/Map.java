package inf112.skeleton.app.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;


public class Map {

    private ArrayList<ArrayList<Tile>> tiles;

    public Map() {

        tiles = new ArrayList<>();

        // Adds rows to the map
        tiles.add(createRow("FFFWWFFBFFWWFBFB"));
        tiles.add(createRow("FFBFWFFBFBFWBFBW"));
        tiles.add(createRow("BWFWFBFFWBFFFBFB"));
        tiles.add(createRow("FFFFFWWWWFFFBFBW"));
        tiles.add(createRow("FWWWWWWWWFWWWWFF"));
        tiles.add(createRow("FFFFWFFFFFFFWFFF"));
        tiles.add(createRow("WWWWWFWWWWFFFBFF"));
        tiles.add(createRow("WFFFFFWFWWWWWWWW"));
        tiles.add(createRow("WFBFWWWFFFFFFFFW"));
        tiles.add(createRow("WBFBWFBFWWFFFFFW"));
        tiles.add(createRow("WFBFWFWFFWFFFFFW"));
        tiles.add(createRow("WBFBWFWFFWFBFBFW"));
        tiles.add(createRow("WFBFWFWBBWBFBFBW"));
        tiles.add(createRow("WBFBWFWFFWFBBBFW"));
        tiles.add(createRow("FBFFFBFFFWBBFBBW"));
        tiles.add(createRow("FFFWWWWWWWWFFFWW"));


    }

    private ArrayList<Tile> createRow(String row) {
        ArrayList<Tile> tileRow = new ArrayList<>();
        for (char c : row.toCharArray()) {
            Tile tile = switch (c) {
                case 'W' -> Tile.Wall;
                case 'F' -> Tile.Floor;
                case 'B' -> Tile.Boulder;
                default -> throw new IllegalArgumentException("Invalid character in map: " + c);
            };
            tileRow.add(tile);
        }
        return tileRow;
    }

    public int getHeight() {
        return tiles.size();
    }

    public int getWidth() {
        return tiles.get(0).size();
    }

    public Tile getBlock(int x, int y) throws ExecutionControl.NotImplementedException {
        if (x >= 0 && x < tiles.size() && y >= 0 && y < tiles.get(x).size()) {
            return tiles.get(x).get(y);
        }
        throw new ExecutionControl.NotImplementedException("Invalid block coordinates");
    }

    public void draw(SpriteBatch sb) {
        for (int y = 0; y < tiles.size(); y++) {
            for (int x = 0; x < tiles.get(y).size(); x++) {
                Tile tile = tiles.get(y).get(x);
                if (tile == Tile.Boulder) {
                    // Draws Floor under Boulder
                    sb.draw(Tile.Floor.texture, x, y, 1, 1);
                }
                sb.draw(tile.texture, x, y, 1, 1);
            }
        }
    }

}
