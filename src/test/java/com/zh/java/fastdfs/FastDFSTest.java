package com.zh.java.fastdfs;

import com.alibaba.fastjson.JSON;
import com.sun.tools.classfile.ConstantPool;

import java.io.File;
import java.util.ArrayList;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/10/27 2:31 下午
 * <p>
 */
public class FastDFSTest {
    public static void main(String[] args) {
        File yourFile = new File("/Users/wally/Downloads/upload");
        upload(yourFile);
    }

    private static void upload(File yourFile) {
        File[] files = yourFile.listFiles();
        if (files == null) {
            return;
        }
        ArrayList<FileInfo> fileInfos = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                //是文件夹，递归
                upload(file);
            } else if (file.isFile()) {
                //是文件，上传文件
                String[] results = FastDFSUtil.upload(file.getAbsolutePath());
                if (results != null && results.length == 2) {
                    String group = results[0];
                    String remoteFileName = results[1];
                    String fileId = group + "/" + remoteFileName;
                    FileInfo fileInfo = new FileInfo(fileId, "/goods-img/" + file.getName());
                    fileInfos.add(fileInfo);
                    System.out.println("访问地址：" + "http://192.168.211.131/" + fileId);
                }
            }
        }
        String json = JSON.toJSONString(fileInfos);
        System.out.println(json);
    }

    private static class FileInfo {
        /**
         * 上传后的文件Id
         */
        private final String fileId;
        /**
         * 原始文件的文件名
         */
        private final String originFileName;

        public FileInfo(String fileId, String originFileName) {
            this.fileId = fileId;
            this.originFileName = originFileName;
        }

        public String getFileId() {
            return fileId;
        }

        public String getOriginFileName() {
            return originFileName;
        }
    }
}