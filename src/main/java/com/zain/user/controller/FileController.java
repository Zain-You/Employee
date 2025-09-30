package com.zain.user.controller;

import cn.hutool.core.io.FileUtil;
import com.zain.user.pojo.dto.ResponseMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("files")
public class FileController
{
    // 获取当前这个项目的根目录
    private static  final String filePath = System.getProperty("user.dir") + "/files/";

    @PostMapping("/upload")
    public ResponseMessage upload(MultipartFile file)
    {
        String originalFileName = file.getOriginalFilename();

        // 判断文件是否存在
        if(!FileUtil.isDirectory(filePath))
        {
            // 创建文件夹
            FileUtil.mkdir(filePath);
        }

        // 给文件名加一个唯一的标识
        String fileName = System.currentTimeMillis() + "_" + originalFileName;
        String realPath = filePath + fileName;
        try{
            //file.getInputStream().transferTo(realPath);
            FileUtil.writeBytes(file.getBytes(), realPath);
        }
        catch (IOException e)
        {
            // 打印异常
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // 返回路径
        String url = "http://localhost:8031/files/download/" + fileName;

        // 返回一个网络连接
        return ResponseMessage.success(url);
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response)
    {
        try {
            // 文件面设置一下编码格式，避免乱码
            response.addHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setContentType("application/octet-stream");

            OutputStream os = response.getOutputStream();
            // 获取文件字节数据
            String realPath = filePath + fileName;
            FileUtil.readBytes(realPath);

            byte[] bytes = FileUtil.readBytes(realPath);
            os.write(bytes);
            os.flush();
            os.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
