package inf112.skeleton.app.geometry;

// TODO maybe make this mutable and reuse them in the future.
public record Vector2i(int x, int y) {

    public static final Vector2i zero = new Vector2i(0, 0);

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

    public boolean equals(Vector2i other) {
        return (this.x == other.x) && (this.y == other.y);
    }
}
