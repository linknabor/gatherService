package com.eshequ.gatherservice.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTP;

import java.io.*;

public class FtpUtil {
    // ftp服务器ip地址
    private static String FTP_ADDRESS;
    // 端口号
    private static int FTP_PORT;
    // 用户名
    private static String FTP_USERNAME;
    // 密码
    private static String FTP_PASSWORD;
    //切换的目录
    private static String BASE_PATH;

    /**
     * 构造
     * @param ftpAddress FTP地址
     * @param ftpPort FTP端口
     * @param ftpName FTP用户名
     * @param ftpPassWord FTP密码
     * @param basePath FTP切换目录
     */
    public FtpUtil(String ftpAddress, int ftpPort, String ftpName, String ftpPassWord, String basePath) {
        FTP_ADDRESS = ftpAddress;
        FTP_PORT = ftpPort;
        FTP_USERNAME = ftpName;
        FTP_PASSWORD = ftpPassWord;
        BASE_PATH = basePath;
    }

    /**
     * 上传文件
     * @param path 存放在FTP的路径
     * @param filename 文件名称
     * @param input 文件流
     * @return
     */
    public boolean fileUpload(String path, String filename, InputStream input) {
        FTPClient ftp=new FTPClient();
        try {
            ftp.connect(FTP_ADDRESS, FTP_PORT);
            ftp.login(FTP_USERNAME, FTP_PASSWORD);
            //设置文件编码格式
            ftp.setControlEncoding("UTF-8");
            ftp.enterLocalPassiveMode();
            //设置传输方式为流方式
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            //获取状态码，判断是否连接成功
            if(!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                throw new RuntimeException("FTP服务器拒绝连接");
            }
            if(!ftp.changeWorkingDirectory(BASE_PATH)) {
                throw new RuntimeException("根目录不存在，需要创建");
            }

            //判断是否存在目录
            if(!ftp.changeWorkingDirectory(path)) {
                String[] dirs=path.split("/");
                //创建目录
                for (String dir : dirs) {
                    if(null == dir || "".equals(dir)) continue;
                    //判断是否存在目录
                    if(!ftp.changeWorkingDirectory(dir)) {
                        //不存在则创建
                        if(!ftp.makeDirectory(dir)) {
                            throw new RuntimeException("子目录创建失败");
                        }
                        //进入新创建的目录
                        ftp.changeWorkingDirectory(dir);
                    }
                }
                //设置上传文件的类型为二进制类型
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                //上传文件
                if(!ftp.storeFile(filename, input)) {
                    return false;
                }
                input.close();
                ftp.logout();
                return true;
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }

    /**
     * 下载文件
     * @param filename 文件名
     * @param localPath 存放到本地路径
     */
    public boolean downloadFile(String filename, String localPath) {
        FTPClient ftp = new FTPClient();
        try {
            ftp.connect(FTP_ADDRESS, FTP_PORT);
            ftp.login(FTP_USERNAME, FTP_PASSWORD);
            //设置文件编码格式
            ftp.setControlEncoding("UTF-8");
            //设置传输方式为流方式
            ftp.enterLocalPassiveMode();
            //设置传输方式为流方式
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            //获取状态码，判断是否连接成功
            if(!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                throw new RuntimeException("FTP服务器拒绝连接");
            }
            //判断是否存在目录
            if(!ftp.changeWorkingDirectory(BASE_PATH)) {
                throw new RuntimeException("文件路径不存在：" + BASE_PATH);
            }
            //获取该目录所有文件
            FTPFile[] files=ftp.listFiles();
            for (FTPFile file : files) {
                //判断是否有目标文件
                //System.out.println("文件名"+file.getName()+"---"+name);
                if(file.getName().equals(filename)) {
                    //如果找到，将目标文件复制到本地
                    File localFile = new File(localPath+"/"+file.getName());
                    OutputStream out = new FileOutputStream(localFile);
                    ftp.retrieveFile(file.getName(), out);
                    out.close();
                }
            }
            ftp.logout();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
