package com.mc.resourcesshareback.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StockpileUtils {

    /**
     *
     * @param file     光影
     * @param path     地址
     * @param fileName 自定义命名
     * @return 1=成功、-1=文件为空、-2文件创建失败、-3=储存失败
     */
    public static int stockpileFile(MultipartFile file, String path, String fileName) {
        if (file.isEmpty()) {
            return -1;
        }

        // 确保目录存在
        File dir = new File(path);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("文件创建成功");
            } else {
                return -2;
            }
        }

        File destFile = new File(dir, fileName);
        try {
            file.transferTo(destFile);
            return 1;
        } catch (IOException e) {
            return -3;
        }
    }

    public static boolean stockpileImage(MultipartFile image, String imageDir, String imageName) {

        // 确保目录存在
        File dir = new File(imageDir);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("文件创建成功");
            } else {
                return false;
            }
        }

        try {
            // 获取图片输入流
            InputStream inputStream = image.getInputStream();

            // 读取图片为 BufferedImage 对象
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            // 确保图片被转换为 PNG 格式
            File outputFile = new File(imageDir, imageName); // 生成目标文件

            // 保存为 PNG 格式
            boolean isWritten = ImageIO.write(bufferedImage, "PNG", outputFile); // 使用 PNG 格式保存图片

            if (isWritten) {
                return true; // 保存成功
            } else {
                return false; // 保存失败
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void deleteDirectoryAndContents(File directory) {
        // 检查目录是否存在且是一个目录
        if (directory.exists() && directory.isDirectory()) {
            // 获取目录下的所有文件和子目录
            File[] files = directory.listFiles();

            if (files != null) {
                // 遍历目录中的每个文件和子目录
                for (File file : files) {
                    // 如果是子目录，则递归调用deleteDirectoryAndContents删除子目录及其中内容
                    if (file.isDirectory()) {
                        deleteDirectoryAndContents(file);
                    } else {
                        // 如果是文件，则直接删除
                        file.delete();
                    }
                }
            }

            // 删除当前目录
            directory.delete();
            System.out.println("目录 " + directory.getAbsolutePath() + " 删除成功");
        } else {
            System.out.println("指定的路径不是一个有效的目录或不存在");
        }
    }

}
