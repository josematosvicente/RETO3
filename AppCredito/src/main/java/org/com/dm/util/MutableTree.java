package org.com.dm.util;

import java.io.Serializable;

public interface MutableTree <N extends Serializable> extends Tree<N> {
    public boolean add (N parent, N node);
    public boolean remove (N node, boolean cascade);
}
