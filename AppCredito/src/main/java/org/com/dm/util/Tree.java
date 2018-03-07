package org.com.dm.util;

import java.io.Serializable;
import java.util.List;

public interface Tree <N extends Serializable> extends Serializable {
    public List<N> getRoots ();
    public N getParent (N node);
    public List<N> getChildren (N node);
}