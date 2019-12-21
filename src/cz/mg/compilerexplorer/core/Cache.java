package cz.mg.compilerexplorer.core;

import cz.mg.compilerexplorer.core.collections.CacheMap;


public class Cache extends CacheMap<Node, Node> {
    private static final int DEFAULT_LIMIT = 100;

    public Cache() {
        super(DEFAULT_LIMIT);
    }

    public Cache(int limit) {
        super(limit);
    }
}
