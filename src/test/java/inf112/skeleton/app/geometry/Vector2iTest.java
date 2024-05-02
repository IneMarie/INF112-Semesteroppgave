package inf112.skeleton.app.geometry;

import org.junit.jupiter.api.Test;

class Vector2iTest {

    final int aX = 5;
    final int aY = 6;
    final int bX = 7;
    final int bY = 8;
    final Vector2i a = new Vector2i(aX, aY);
    final Vector2i b = new Vector2i(bX, bY);

    @Test
    void add() {
        assert a.add(b).equals(new Vector2i(aX + bX, aY + bY));
    }

    @Test
    void sub() {
        assert a.sub(b).equals(new Vector2i(aX - bX, aY - bY));
    }

    @Test
    void mul() {
        assert a.mul(b).equals(new Vector2i(aX * bX, aY * bY));
    }

    @Test
    void div() {
        assert a.div(b).equals(new Vector2i(aX / bX, aY / bY));
    }
}