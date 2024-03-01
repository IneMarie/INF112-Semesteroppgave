package inf112.skeleton.app.geometry;

public record Vector2i(int x, int y) {

    public Vector2i add(Vector2i other) {
        return new Vector2i(x + other.x, y + other.y);
    }
    public Vector2i sub(Vector2i other) {
        return new Vector2i(x - other.x, y - other.y);
    }
    public Vector2i mul(Vector2i other) {
        return new Vector2i(x * other.x, y * other.y);
    }
    public Vector2i div(Vector2i other) {
        return new Vector2i(x / other.x, y / other.y);
    }
}
