package me.alextur.rgraph.nodemap;

import me.alextur.rgraph.nodemap.NodeMapException;

/**
 * @author Alex Turchynovich
 */
public interface NodeMapper {

    public int getNodeId(String nodeName) throws NodeMapException;

}
