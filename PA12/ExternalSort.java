package PA12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class ExternalSort {

    final int n = 4048;
    byte[] block = new byte[n]; // can only use 4kB everytime

    public ExternalSort() throws IOException {
        Path path = Paths.get("/Volumes/D/CSCI-240/PA12/filetoSort.bin");
        byte[] bigFile = Files.readAllBytes(path); // big file
        int N = bigFile.length; // big file' size

        byte[] arr = { 0x00, 0x01 };
        ByteBuffer wrapped = ByteBuffer.wrap(arr); // big-endian by default
        short num = wrapped.getShort(); // 1

        ByteBuffer dbuf = ByteBuffer.allocate(2);
        dbuf.putShort(num);
        byte[] bytes = dbuf.array(); // { 0, 1 }

        
    }

}
