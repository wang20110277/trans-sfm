package com.aibank.wm.zd.client.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class FileUtils {
    public static void copyWithSignalFile(File src, String directory, String signalFileSuffix) {
        copyWithSignalFile(src,FileUtil.file(directory,src.getName()),signalFileSuffix);
    }
    public static void copyWithSignalFile(File src, File dest, String signalFileSuffix) {
        FileUtil.copy(src, dest, true);
        FileUtil.touch(dest.getAbsolutePath() + signalFileSuffix);
    }

    public static void moveWithSignalFile(File src, File dest, String signalFileSuffix) {
        FileUtil.move(src, dest, true);
        if (!FileUtil.exist(src.getAbsolutePath() + signalFileSuffix)) {
            FileUtil.touch(src.getAbsolutePath() + signalFileSuffix);
        }
        FileUtil.move(FileUtil.file(src.getAbsolutePath() + signalFileSuffix), FileUtil.file(dest.getAbsolutePath() + signalFileSuffix), true);
    }

    public static void deleteWithSignalFile(File src, String signalFileSuffix) {
        FileUtil.del(src);
        if (FileUtil.exist(src.getAbsolutePath() + signalFileSuffix)) {
            FileUtil.del(src.getAbsolutePath() + signalFileSuffix);
        }
    }

    public static InputStream getResourceFileStream(String path) {
        // try the read file from the local file system
        Resource resource = new FileSystemResource(FileUtil.getAbsolutePath(path));
        if (resource.exists()) {
            try {
                log.debug("Resource File Path = " + resource.getURI().getPath());
                return resource.getInputStream();
            } catch (IOException e) {
                log.debug("read file from file system error: " + path);
            }
        }

        // try to find file in internal .jar package
        InputStream in = FileUtils.class.getClassLoader().getResourceAsStream(path);
        if (in != null) {
            log.debug("Resource File Use JAR Package Internal File: " + path);
            return in;
        }
        return null;
    }

    public static List<String> readLines(File file,Charset charset, int lineCount) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset))) {
            for (int i = 0; i < lineCount; i++) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                lines.add(line);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return lines;
    }

    public static void main(String[] args) {

        // 读取工程 resources 目录下文件
        // 如果项目工程 resources 目录不存在该文件，则尝试去改代码所在的class的jar包中的 resources 目录去查找
        final InputStream resourceFileStream = FileUtils.getResourceFileStream("cert/dev/051@LC百信银行测试专用证书@Z00000000900@1-enc.sm2");
        FileUtil.writeFromStream(resourceFileStream, new File("/Volumes/data/tmp/202205/051@LC百信银行测试专用证书@Z00000000900@1-enc.sm2"));

    }
}
