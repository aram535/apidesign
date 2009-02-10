package org.apidesign.javamonitorflaws;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public abstract class Cache<From,To> {
    private Map<From,To> cache;

    public final To get(From f) {
        for (;;) {
            synchronized (this) {
                if (cache == null) {
                    cache = new HashMap<From, To>();
                }
                To t = cache.get(f);
                if (t != null) {
                    return t;
                }
            }

            To newT = createItem(f);

            synchronized (this) {
                To t = cache.get(f);
                if (t == null) {
                    cache.put(f, newT);
                    return newT;
                } else {
                    return t;
                }
            }
        }
    }

    protected abstract To createItem(From f);
}
