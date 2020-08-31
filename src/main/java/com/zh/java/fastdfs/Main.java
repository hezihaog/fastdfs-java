package com.zh.java.fastdfs;

/**
 * @author hezihao
 * @version 1.0
 * <p>
 * @date 2020/8/31 4:06 下午
 * <p>
 */
public class Main {
    public static void main(String[] args) {
        //upload();
        //download();
        delete();
    }

    /**
     * 上传文件
     */
    private static void upload() {
        //这里的路径，改为你的本次的磁盘中文件的绝对路径！
        String[] results = FastDFSUtil.upload("/Users/wally/Desktop/Code/Java/fastdfs-java/src/main/resources/FastDFS命名解析.png");
        if (results != null && results.length == 2) {
            String group = results[0];
            String remoteFileName = results[1];
            System.out.println("group：" +group);
            System.out.println("remoteFileName：" + remoteFileName);
            System.out.println("链接地址：http://192.168.211.131/" + group + "/" + remoteFileName);
        }
    }

    /**
     * 下载文件
     */
    private static void download() {
        //下载文件到项目的根目录
        boolean result = FastDFSUtil.download("group1",
                "M00/00/00/wKjTg19Ms0iAFnpDAAAmdb_zhmw098.png",
                "a.png");
        if (result) {
            System.out.println("文件下载成功！");
        } else {
            System.out.println("文件下载失败！");
        }
    }

    /**
     * 删除文件
     */
    public static void delete() {
        boolean result = FastDFSUtil.delete("group1",
                "M00/00/00/wKjTg19Ms0iAFnpDAAAmdb_zhmw098.png");
        if (result) {
            System.out.println("文件删除成功！");
        } else {
            System.out.println("文件删除失败！");
        }
    }
}