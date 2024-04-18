package inf112.skeleton.app.world;

/**
 * The flag enum is a number of bit-flags.
 * Any time a tile or entity needs to interact with other entities in some unique way, a flag is added to this enum.
 * Tiles and entities may then use these flags to describe themselves to the other entities and objects.
 * Most of the actual interaction between tiles/entities is then implemented in the World.moveEntity method.
 */
public enum Flag {
    Player,
    Solid,
    Movable,
    Damaging,
    Breaking,
    Goal,
    PowerUp,
    Breakable,
    ;

    Flag() {
        if (ordinal() > 63)
            throw new IllegalStateException("You can not have that many flags.");
    }

    private long val() {
        return 1L << ordinal();
    }

    /**
     * Join the given flags into a single Group containing all flags.
     * @param flags the flags to join
     * @return Flag.Group containing all flags
     */
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

        /**
         * Create a new Group containing all flags from this, other or both.
         * @param other the other group to join
         * @return the resulting Group
         */
        public Group union(Group other) {
            return new Group(this.flags | other.flags);
        }

        /**
         * Check if the given flag is present within this Group.
         * @param flag the flag to check for
         * @return true if the flag is present in the Group, false otherwise
         */
        public boolean is(Flag flag) {
            return (this.flags & flag.val()) != 0;
        }

        /**
         * Check if every single given flag is present within this Group.
         * @param flags the flags to check for
         * @return true if all flags are present, false otherwise
         */
        public boolean isAllOf(Flag... flags) {
            for (var f : flags) {
                if (!is(f)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Check if only the given flags are present within this group.
         * @param flags the flags to check for
         * @return true if only the given flags are present, false if any other flags are present
         */
        public boolean isOnly(Flag... flags) {
            long seen = 0;

            long v = this.flags;
            for (var flag : flags) {
                var val = flag.val();

                if ((seen & val) != 0) {
                    // The xor below will be undone if the flag has already been seen.
                    continue;
                }

                v ^= val;

                seen |= val;
            }
            return v == 0L;
        }

        /**
         * Check if any of the given flags are present within this group.
         * @param flags the flags to check for
         * @return true if any of the given flags are present, false if none are
         */
        public boolean isAnyOf(Flag... flags) {
            for (var f : flags) {
                if (is(f)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Check that none of the given flags are present within this group.
         * @param flags the flags to check for
         * @return true if none of the given flags are present, false otherwise
         */
        public boolean isNoneOf(Flag... flags) {
            return !isAnyOf(flags);
        }

    }
}
