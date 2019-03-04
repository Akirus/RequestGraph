package me.alextur.rgraph.logger;

import java.io.*;
import java.util.Date;

/**
 * @author Alex Turchynovich
 */
public class FileGraphLogger implements GraphLogger, Closeable{

    private static final int VERSION = 1;
    private static final byte[] MAGIC = { 0xD, 0xF, 0xE };

    private String fileName;
    private RandomAccessFile randomAccessFile;

    public FileGraphLogger(final String pFileName) {
        fileName = pFileName;
    }

    private void writeMagic(RandomAccessFile pFile) throws IOException {
        pFile.write(MAGIC);
    }

    private void writeVersion(RandomAccessFile pFile) throws IOException {
        pFile.writeInt(VERSION);
    }

    private void writeFinal(RandomAccessFile pFile, boolean pIsFinal) throws IOException {
        writeFinal(pFile, pIsFinal, false);
    }
    private void writeFinal(RandomAccessFile pFile, boolean pIsFinal, boolean seek)  throws IOException{
        if (seek) {
            // a final flag offset, refer to file documentation for details.
            pFile.seek(0x7);
        }
        pFile.writeBoolean(pIsFinal);
    }

    public void createFile() throws GraphLoggerException {
        File file = new File(getFileName());
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new GraphLoggerException("File " + getFileName() + " could not be deleted!");
            }
        }

        try {
            boolean created = file.createNewFile();
            if (!created) {
                throw new GraphLoggerException("File " + getFileName() + " could not be created!");
            }
        } catch (IOException e) {
            throw new GraphLoggerException(e);
        }

        try {
            randomAccessFile = new RandomAccessFile(getFileName(), "rw");
            writeMagic(randomAccessFile);
            writeVersion(randomAccessFile);
            writeFinal(randomAccessFile, false);

            // padding
            randomAccessFile.write(new byte[10]);
        } catch (IOException e) {
            throw new GraphLoggerException(e);
        }
    }

    @Override
    public void initialize() throws GraphLoggerException{
        this.createFile();
    }

    @Override
    public void log(Date timestamp, int nodeId, int receiverId, int wideRequestId, byte[] payload) throws GraphLoggerException {
        File file = new File(getFileName());
        long length = file.length();

        try {
            randomAccessFile.seek(length);
            randomAccessFile.writeLong(timestamp.getTime());
            randomAccessFile.writeInt(nodeId);
            randomAccessFile.writeInt(receiverId);
            randomAccessFile.writeLong(wideRequestId);
            randomAccessFile.writeInt(payload.length);
            if (payload.length > 0) {
                randomAccessFile.write(payload);
            }
        } catch (IOException e) {
            throw new GraphLoggerException(e);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String pFileName) {
        fileName = pFileName;
    }

    @Override
    public void close() throws IOException {
        writeFinal(randomAccessFile, true, true);
        randomAccessFile.close();
    }
}
