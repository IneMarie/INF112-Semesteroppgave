package inf112.skeleton.app.geometry;

public record Vector2i(int x, int y) {

    public Vector2i copy() {
        return new Vector2i(x, y);
    }
}
