package PA12;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class FileIO {
    private final int N = 100000;
    private int[] nums;
    long startTime;
    long endTime;

    public FileIO() {
        Random rd = new Random();
        nums = new int[N];
        for (int i = 0; i < N; i++)
            nums[i] = rd.nextInt(100);
        for (int i = 0; i < 5; i++)
            System.out.println(nums[i]);
    }


    public void output1() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("D:\\CSCI-240\\PA12\\output1.txt");
            System.out.println("Output: text format with one value at a time");
            startTime = System.currentTimeMillis();
            for (int i = 0; i < N; i++)
                fileWriter.write(nums[i]);
            fileWriter.close();
            endTime = System.currentTimeMillis();
            System.out.println("run time: " + (endTime - startTime) + "ms\n");
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public void input1() throws IOException {
        try {
            FileInputStream in = new FileInputStream("D:\\CSCI-240\\PA12\\output1.txt");
            int limit = 5;
            System.out.println("Input: text format with one value at a time");
            startTime = System.currentTimeMillis();
            while (true) {
                if (in.read() == -1)
                    break;
                if (limit-- > 0)
                    System.out.print(in.read() + " ");
            }

            Scanner s = new Scanner(in);
            in.close();
            endTime = System.currentTimeMillis();
            System.out.println();
            System.out.println("run time: " + (endTime - startTime) + "ms\n");
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public void output2() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("D:\\CSCI-240\\PA12\\output2.bin");
            System.out.println("Output: binary format with one value at a time");
            byte[] buffer = new byte[4];    // one integer is 4 bytes
            int length = 0;
            startTime = System.currentTimeMillis();
            for (int i = 0; i < N; i++)
                fileWriter.write(nums[i]);
            fileWriter.close();
            endTime = System.currentTimeMillis();
            System.out.println("run time: " + (endTime - startTime) + "ms\n");
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public void input2() throws IOException {
        try {
            FileInputStream in = new FileInputStream("D:\\CSCI-240\\PA12\\output2.bin");
            int limit = 5;
            byte[] buffer = new byte[4];
            int length = 0;
            System.out.println("Input: binary format with one value at a time");
            startTime = System.currentTimeMillis();
            while (true) {
                if ((length = in.read(buffer)) == -1)
                    break;
                if (limit-- > 0)
                    System.out.print(in.read() + " ");
            }
            in.close();
            endTime = System.currentTimeMillis();
            System.out.println();
            System.out.println("run time: " + (endTime - startTime) + "ms\n");
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public void output3() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("D:\\CSCI-240\\PA12\\output3.bin");
            System.out.println("Output: binary format with 256 values (1024 bytes) at a time");
            byte[] buffer = new byte[1024];    // one integer is 4 bytes
            int length = 0;
            startTime = System.currentTimeMillis();
            for (int i = 0; i < N; i++)
                fileWriter.write(nums[i]);
            fileWriter.close();
            endTime = System.currentTimeMillis();
            System.out.println("run time: " + (endTime - startTime) + "ms\n");
        } catch (Exception e) {
            throw new IOException();
        }
    }

    public void input3() throws IOException {
        try {
            FileInputStream in = new FileInputStream("D:\\CSCI-240\\PA12\\output3.bin");
            int limit = 5;
            byte[] buffer = new byte[1024];
            int length = 0;
            System.out.println("Input: binary format with 256 values (1024 bytes) at a time");
            startTime = System.currentTimeMillis();
            while (true) {
                if ((length = in.read(buffer)) == -1)
                    break;
                if (limit-- > 0)
                    System.out.print(in.read() + " ");
            }
            in.close();
            endTime = System.currentTimeMillis();
            System.out.println();
            System.out.println("run time: " + (endTime - startTime) + "ms\n");
        } catch (Exception e) {
            throw new IOException();
        }
    }
}
