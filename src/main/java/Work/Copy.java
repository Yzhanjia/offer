package Work;

import java.io.*;
import java.nio.channels.FileChannel;

public class Copy {
    public static void main(String[] args) {
        File file1 = new File("D://java");
        File file2 = new File("D://test");
//        copyFileUsingFileChannels(file1,file2);
        //    copyFileUsingFileStreams(file1, file2);
//        copyFolder(file1, file2);
        copyDirectory(file1, file2);
    }

    private static void copyDirectory(File sourceFile, File targetFile) {
        if (sourceFile.isFile()) {// 如果是文件,则直接复制
            copyFile(sourceFile, new File(targetFile, sourceFile.getName()));
            System.out.println(sourceFile.getName() + "拷贝完成");
        } else {//如果是目录,则遍历
            File file = new File(targetFile, sourceFile.getName());//创建子文件夹
            file.mkdirs();
            System.out.println(file.getName() + "目录创建完成!");
            File[] files = sourceFile.listFiles();
            for (File file2 : files) {
                copyDirectory(file2, file);
            }
        }
    }

    private static void copyFile(File sourceFile, File targetDir) {
        // TODO Auto-generated method stub
        RandomAccessFile rafSource = null;
        RandomAccessFile rafTarget = null;
        try {
            rafSource = new RandomAccessFile(sourceFile, "r");
            rafTarget = new RandomAccessFile(targetDir, "rw");
            rafTarget.setLength(sourceFile.length());
            byte[] buff = new byte[1024 * 4];
            int length = 0;
            while ((length = rafSource.read(buff)) != -1) {
                rafTarget.write(buff, 0, length);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }

    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }
}
