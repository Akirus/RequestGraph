package me.alextur.rgraph;

import me.alextur.rgraph.nodemap.JsonNodeMapper;
import me.alextur.rgraph.nodemap.NodeMapException;
import me.alextur.rgraph.nodemap.NodeMapper;
import me.alextur.rgraph.nodemap.SimpleHashNodeIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Alex Turchynovich
 */
public class JsonNodeMapperTests {


    @Test
    public void testNodeMapper() {
        JsonNodeMapper mapper = new JsonNodeMapper("nodemap.json");
        try {
            int nodeId = mapper.getNodeId("teshost:1920");
            Assertions.assertEquals(nodeId, mapper.getNodeId("teshost:1920"));
        } catch (NodeMapException e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void testSimpleHashNodeIdGenerator() {
        SimpleHashNodeIdGenerator idGenerator = new SimpleHashNodeIdGenerator();
        final int first = idGenerator.generate("1");
        final int second = idGenerator.generate("2");
        Assertions.assertNotEquals(first, second);
        Assertions.assertEquals(idGenerator.generate("abc"), idGenerator.generate("abc"));
    }

}
