package me.alextur.rgraph.nodemap;

/**
 * @author Alex Turchynovich
 */
public class SimpleHashNodeIdGenerator implements NodeIdGenerator {

    @Override
    public  int generate(String pNodeName) {
        int h = 0;
        for (char aVal : pNodeName.toCharArray()) {
            h = 31 * h + aVal;
            if (h < 0) {
                h = Math.abs(h);
            }
        }
        return h;
    }

}
