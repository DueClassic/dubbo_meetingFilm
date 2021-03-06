package com.stylefeng.guns.rest.common.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "ftp")
public class FTPUtil {

    private String hostName;
    private Integer port;
    private String userName;
    private String password;
    private String uploadPath;

    private FTPClient ftpClient=null;

    private void initFtpClient(){
        try {
            ftpClient=new FTPClient();
            ftpClient.setControlEncoding("utf-8");
            ftpClient.connect(hostName,port);
            ftpClient.login(userName,password);
        }catch (Exception e){
            log.error("初始化FTP失败",e);
        }
    }

    public boolean uploadFle(String fileName, File file){
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=new FileInputStream(file);
            initFtpClient();
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(this.getUploadPath());
            ftpClient.storeFile(fileName,fileInputStream);
            return true;
        }catch (Exception e){
            log.error("上传失败",e);
            return false;
        }finally {
            try {
                fileInputStream.close();
                ftpClient.logout();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String getFieldStrByAddress(String fileAddress){
        BufferedReader bufferedReader=null;
        try {
            initFtpClient();
            bufferedReader=new BufferedReader(
                    new InputStreamReader(
                            ftpClient.retrieveFileStream(fileAddress)
                    )
            );
            StringBuffer stringBuffer=new StringBuffer();
            while (true){
                String lineStr=bufferedReader.readLine();
                if (lineStr==null){
                    break;
                }
                stringBuffer.append(lineStr);
            }
            ftpClient.logout();
            return stringBuffer.toString();
        }catch (Exception e){
            log.error("获取文件信息失败",e);
        }finally {
            try {
                bufferedReader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
