package me.alextur.rgraph.logger;

@FunctionalInterface
public interface GraphLoggerFactory {

    FileGraphLogger createLogger(String pName);

}
