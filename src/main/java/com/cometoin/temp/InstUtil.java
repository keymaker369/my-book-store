package com.cometoin.temp;

public class InstUtil {

    public static String getFileId(String instPagePicUrl){
        String fileId = instPagePicUrl.split("/")[4];
        return fileId;
    }
}
