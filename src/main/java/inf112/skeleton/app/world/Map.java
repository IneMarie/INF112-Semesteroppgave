package inf112.skeleton.app.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.geometry.Vector2i;

import java.util.ArrayList;


/**
 * The job of this class is to parse a text file to produce a 2d list of static tiles, as well as a list of dynamic IEntity objects.
 * The size of a Map is always the same; defined by the width and height constants below.
 * Stack several Map objects to represent and arbitrarily sized world.
 */
public class Map { // TODO rename to MapChunk. Do later to avoid merge conflicts.

    private ArrayList<ArrayList<Tile>> staticTiles;
    private World world;

    public final int width = 16;
    public final int height = 16;

    /**
     * @param world is needed to produce entities
     */
    public Map(World world, String path) {

        this.world = world;
        staticTiles = new ArrayList<>();
        parseMapFile(path);

        if (staticTiles.size() != height || staticTiles.get(0).size() != width) {
            throw new IllegalArgumentException("the map file is not the correct size");
        }

    }

    private void parseMapFile(String path) {
        var f = Gdx.files.internal(path);
        f.readString().lines().forEach(this::parseRow);
    }

    /**
     * Loads tiles into the 2d staticTiles list.
     * Also spawns entities in this.world.
     *
     * @param row a row of text containing only valid characters representing tiles and entities.
     * @throws IllegalArgumentException if the characters in the map are not handled by this method.
     * @throws IllegalArgumentException if there aren't enough characters in a row.
     */
    private void parseRow(String row) throws IllegalArgumentException {
        ArrayList<Tile> tileRow = new ArrayList<>(width);
        int y = staticTiles.size();
        var charArray = row.toCharArray();
        for (int x = 0; x < charArray.length; x++) {
            var c = charArray[x];

            // Stupid editor removes trailing whitespace in the map files.
            // So this control character is used to denote the end of a row.
            if (c == '|') continue;

            Tile tile = switch (c) {
                case ' ' -> Tile.None;
                case 'W' -> Tile.Wall;
                case 'B' -> Tile.Boulder;
                default -> throw new IllegalArgumentException("Invalid character in map: " + c);
            };
            if (tile.is(Tile.Flag.Movable)) {
                world.spawnEntity(
                        new Entity(
                                tile,
                                new Vector2i(x, tiles.size() + removedRowCount)
                        )
                );
                tileRow.add(Tile.None);
            } else {
                tileRow.add(tile);
            }
        }
        if (tileRow.size() < width) {
            throw new IllegalArgumentException("map row is not the correct width");
        }
        staticTiles.add(0, tileRow);
    }

    /**
     * @return the tile at the given x and y coordinates, or Tile.None if there is no tile there, or the coordinates
     * out of the range of the map.
     */
    public Tile getBlock(int x, int y) {
        if (x >= 0 && x < staticTiles.size() && y >= 0 && y < staticTiles.get(x).size()) {
            return staticTiles.get(y).get(x);
        } else {
            return Tile.None;
        }
    }

    static Vector2 temporary = new Vector2();

    /**
     * Draws all tiles in the staticTiles list.
     * Also draws the floor tile under all tiles.
     */
    public void draw(SpriteBatch sb) {
        for (int y = 0; y < staticTiles.size(); y++) {
            for (int x = 0; x < staticTiles.get(y).size(); x++) {
                Tile tile = staticTiles.get(y).get(x);
                temporary.set(x, y);
                Tile.Floor.draw(sb, temporary);
                tile.draw(sb, temporary);
            }
        }
    }

}
