package me.alextur.rgraph.nodemap;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Alex Turchynovich
 */
public class JsonNodeMapper implements NodeMapper {

    private static final String NODE_MAP_OBJECT_NAME = "nodemap";

    private NodeIdGenerator nodeIdGenerator;
    private Map<String, Integer> map;
    private String nodeMapFile;

    public JsonNodeMapper(String pNodeMapFile) {
        this(pNodeMapFile, new SimpleHashNodeIdGenerator());
    }

    public JsonNodeMapper(String pNodeMapFile, NodeIdGenerator pNodeIdGenerator) {
        nodeMapFile = pNodeMapFile;
        nodeIdGenerator = pNodeIdGenerator;
    }

    public void readNodeMap() throws NodeMapException {
        map = new HashMap<>();

        if (!Files.exists(Paths.get(getNodeMapFile()))) {
            return;
        }

        try (JsonReader reader = new JsonReader(new FileReader(getNodeMapFile()))){
            reader.beginObject();
            while(reader.hasNext()) {
                final String name = reader.nextName();
                if (name.equals(NODE_MAP_OBJECT_NAME)) {
                    reader.beginObject();
                    while(reader.hasNext()) {
                        final String nodeName = reader.nextName();
                        final Integer nodeId = reader.nextInt();
                        map.put(nodeName, nodeId);
                    }
                    reader.endObject();
                }
            }
            reader.endObject();
        } catch (IOException e) {
            throw new NodeMapException(e);
        }
    }

    public void writeNodeMap() throws NodeMapException {
        if (map == null) {
            map = new HashMap<>();
        }
        try (JsonWriter writer = new JsonWriter(new FileWriter(getNodeMapFile()))) {
            writer.setIndent("    ");
            writer.beginObject();
            writer.name(NODE_MAP_OBJECT_NAME);
            writer.beginObject();
            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.name(entry.getKey());
                writer.value(entry.getValue());
            }
            writer.endObject();
            writer.endObject();
        } catch (IOException e) {
            throw new NodeMapException(e);
        }
    }

    @Override
    public int getNodeId(String nodeName) throws NodeMapException {
        if (getMap() == null) {
            readNodeMap();
        }
        Integer nodeId = getMap().get(nodeName);
        if (nodeId == null) {
            readNodeMap();
            nodeId = getMap().get(nodeName);
            if (nodeId == null) {
                nodeId = getNodeIdGenerator().generate(nodeName);
                map.put(nodeName, nodeId);
                writeNodeMap();
            }
        }
        return nodeId;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> pMap) {
        map = pMap;
    }

    public String getNodeMapFile() {
        return nodeMapFile;
    }

    public void setNodeMapFile(String pNodeMapFile) {
        nodeMapFile = pNodeMapFile;
    }

    public NodeIdGenerator getNodeIdGenerator() {
        return nodeIdGenerator;
    }

    public void setNodeIdGenerator(NodeIdGenerator pNodeIdGenerator) {
        nodeIdGenerator = pNodeIdGenerator;
    }

}
