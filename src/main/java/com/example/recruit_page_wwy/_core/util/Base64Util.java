package com.example.recruit_page_wwy._core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base64Util {

    // image/jpeg 중에 jpeg만 return
    public static String getMimeType(String imgBase64) {
        int beginIndex = imgBase64.indexOf("/") + 1;
        int endIndex = imgBase64.indexOf(";");
        String mimeType = imgBase64.substring(beginIndex, endIndex);
        return mimeType;
    }

    public static String encodeAsString(byte[] imgBytes, String mimeType) {
        String imgBase64 = Base64.getEncoder().encodeToString(imgBytes);
        imgBase64 = "data:$mimeType;base64,$imgBase64".replace("$mimeType", mimeType).replace("$imgBase64", imgBase64);
        return imgBase64;
    }

    public static byte[] decodeAsBytes(String imgBase64) {
        // 1. mimetype parsing
        String mimeType = getMimeType(imgBase64);
        //System.out.println(mimeType);

        // 2. img parsing
        int prefixEndIndex = imgBase64.indexOf(",");
        String img = imgBase64.substring(prefixEndIndex + 1);
        //System.out.println(img);

        // 3. base64 decode to byte[]
        byte[] imgBytes = Base64.getDecoder().decode(img);
        return imgBytes;
    }

    public static byte[] readImageAsByteArray(String filenameInResources) {
        try (InputStream inputStream = Base64Util.class.getClassLoader().getResourceAsStream(filenameInResources);
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            if (inputStream == null) {
                throw new IllegalArgumentException("리소스를 찾을 수 없습니다: " + filenameInResources);
            }

            byte[] data = new byte[1024]; // 버퍼
            int bytesRead;
            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }

            return buffer.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("이미지 읽기 실패: " + filenameInResources, e);
        }
    }
}
