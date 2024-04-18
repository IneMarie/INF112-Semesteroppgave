package inf112.skeleton.app.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.AmateurInput;
import inf112.skeleton.app.geometry.Vector2i;

import java.util.ArrayList;


/**
 * The job of a Map object is to load in a map file, spawn movable entities into the World, and keep track of static
 * tiles, so the World can ask the Map for collision-data.
 */
public class Map {

    private final ArrayList<ArrayList<Tile>> tiles;
    private final World world;
    private int removedRowCount;

    /**
     * @param world is needed to produce entities
     */
    public Map(World world) {
        this.world = world;
        tiles = new ArrayList<>();
        removedRowCount = 0;
    }

    /**
     * Every time this is called, the given map will be loaded into the game at the next available 'north' position.
     * So you can cumulatively load small levels.
     * You might use the output of this function as input for removeBottomRows.
     *
     * @param path the path to the .txt file representing the level.
     * @return the number of rows loaded.
     */
    public int parseMapFile(String path) {
        var f = Gdx.files.internal(path);
        var lines = f.readString().lines().toList();
        for (int i = lines.size() - 1; i >= 0; i--) {
            parseRow(lines.get(i));
        }
        return lines.size();
    }

    /**
     * removeBottomRows removes count rows while keeping track how many rows have been removed from this map in total.
     * This is to make the removal of rows invisible to objects interacting with the Map.
     * May be used to unload rows as new rows are loaded in.
     *
     * @param count rows to remove
     * @return number of rows actually removed
     */
    public int removeBottomRows(int count) {
        var removed = Math.max(count, 0);
        var remaining = tiles.size() - removed;

        for (int i = 0; i < remaining; i++) {
            tiles.set(i, tiles.get(i + removed));
        }

        for (int i = 0; i < removed; i++) {
            tiles.remove(tiles.size() - 1);
        }

        removedRowCount += removed;
        return removed;
    }

    /**
     * Loads tiles into the this.tiles list, and spawns entities in this.world.
     * If called when the this.tiles list is populated; the new tiles will appear 'north' of existing tiles.
     *
     * @param row a row of text containing only valid characters representing tiles and entities.
     * @throws IllegalArgumentException if the characters in the map are not handled by this method.
     */
    private void parseRow(String row) throws IllegalArgumentException {
        ArrayList<Tile> tileRow = new ArrayList<>();
        var charArray = row.toCharArray();
        for (int x = 0; x < charArray.length; x++) {
            var c = charArray[x];

            // Control character to mark the end of a row.
            if (c == '|') continue;

            Tile tile = switch (c) {
                case ' ' -> Tile.None;
                case 'W' -> Tile.Wall;
                case 'B' -> Tile.Boulder;
                case 'G' -> Tile.Goal;
                case 'H' -> Tile.HammerPowerUp;
                default -> throw new IllegalArgumentException("Invalid character in map: " + c);
            };
            if (tile.is(Flag.Movable)) {
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
        tiles.add(tileRow);
    }

    public void setBlock(int x, int y, Tile tile) {
        y -= removedRowCount;

        if (
                !tiles.isEmpty()
                        && 0 <= y
                        && y < tiles.size()
                        && 0 <= x
                        && x < tiles.get(y).size()
        ) {
            tiles.get(y).set(x, tile);
        }
    }

    /**
     * @return the tile at the given x and y coordinates, or Tile.None if there is no tile there, or the coordinates
     * out of the range of the map.
     */
    public Tile getBlock(int x, int y) {

        y -= removedRowCount;

        if (
                !tiles.isEmpty()
                        && 0 <= y
                        && y < tiles.size()
                        && 0 <= x
                        && x < tiles.get(y).size()
        ) {
            return tiles.get(y).get(x);
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
        if (AmateurInput.isKeyJustPressed(Input.Keys.R)) { // TODO remove
            removeBottomRows(1);
        }
        for (int y = 0; y < tiles.size(); y++) {
            for (int x = 0; x < tiles.get(y).size(); x++) {

                int screenY = y + removedRowCount;

                Tile tile = tiles.get(y).get(x);
                temporary.set(x, screenY);
                Tile.Floor.draw(sb, temporary);
                tile.draw(sb, temporary);
            }
        }
    }

}
