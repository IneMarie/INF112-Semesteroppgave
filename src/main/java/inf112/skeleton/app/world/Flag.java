package inf112.skeleton.app.world;

public enum Flag {
    Player,
    Solid,
    Movable,
    Damaging,
    Explosive,
    Goal,
    ;

    Flag() {
        if (ordinal() > 63)
            throw new IllegalStateException("You can not have that many flags.");
    }

    private long val() {
        return 1L << ordinal();
    }

    static public Group join(Flag... flags) {
        long v = 0L;
        for (var flag : flags) {
            v |= flag.val();
        }
        return new Group(v);
    }

    public static class Group {

        public static final Group None = new Group(0);

        private final long flags;

        private Group(long flags) {
            this.flags = flags;
        }

        public Group union(Group other) {
            return new Group(this.flags | other.flags);
        }

        public boolean is(Flag flag) {
            return (this.flags & flag.val()) != 0;
        }

        public boolean isAllOf(Flag... flags) {
            for (var f : flags) {
                if (!is(f)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isOnly(Flag... flags) {
            long v = this.flags;
            for (var flag : flags) {
                v ^= flag.val();
            }
            return v == 0L;
        }

        public boolean isAnyOf(Flag... flags) {
            for (var f : flags) {
                if (is(f)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isNoneOf(Flag... flags) {
            return !isAnyOf(flags);
        }

    }
}
