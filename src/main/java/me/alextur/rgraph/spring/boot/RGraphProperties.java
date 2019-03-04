package me.alextur.rgraph.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix ="me.alextur.rgraph")
public class RGraphProperties {

    private String defaultLogsPath;
    private int rotationLimit = 1000;
    private String filePrefix = "log";


    public String getDefaultLogsPath() {
        return defaultLogsPath;
    }

    public void setDefaultLogsPath(String defaultLogsPath) {
        this.defaultLogsPath = defaultLogsPath;
    }

    public int getRotationLimit() {
        return rotationLimit;
    }

    public void setRotationLimit(int rotationLimit) {
        this.rotationLimit = rotationLimit;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }
}
