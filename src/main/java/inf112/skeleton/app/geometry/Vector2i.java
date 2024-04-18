package inf112.skeleton.app.geometry;

/**
 * A Vector representing a grid position.
 * @param x coordinate
 * @param y coordinate
 */
public record Vector2i(int x, int y) {

    public static final Vector2i zero = new Vector2i(0, 0);

    /**
     * Pairwise addition
     * @param other the other vector to add
     * @return the resulting Vector2i after addition
     */
    public Vector2i add(Vector2i other) {
        return new Vector2i(x + other.x, y + other.y);
    }

    /**
     * Pairwise subtraction
     * @param other the other vector to subtract
     * @return the resulting Vector2i after subtraction
     */
    public Vector2i sub(Vector2i other) {
        return new Vector2i(x - other.x, y - other.y);
    }

    /**
     * Pairwise multiplication
     * @param other the other vector to multiply this vector with
     * @return the resulting Vector2i after multiplication
     */
    public Vector2i mul(Vector2i other) {
        return new Vector2i(x * other.x, y * other.y);
    }

    /**
     * Pairwise division
     * @param other the other vector to divide this vector by
     * @return the resulting Vector2i after division
     */
    public Vector2i div(Vector2i other) {
        return new Vector2i(x / other.x, y / other.y);
    }

    /**
     * @param other   the reference object with which to compare
     * @return whether this is equal to other
     */
    public boolean equals(Object other) {
        if (other instanceof Vector2i) {
            return equals((Vector2i) other);
        }
        return false;
    }

    /**
     * @param other the vector with which to compare
     * @return whether this is equal to other
     */
    public boolean equals(Vector2i other) {
        return (this.x == other.x) && (this.y == other.y);
    }
}
