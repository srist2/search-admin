package com.gxstnu.search.controller;

import com.gxstnu.search.utils.Result;
import com.gxstnu.search.utils.UploadUtils;
import com.gxstnu.search.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.UUID;

/**
 * 文件上传
 */
@RestController
public class UtilController {

    @PostMapping(value = "/fileUpload")
    public Result fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取当前时间
        Utils utils = new Utils();
        String dateName = utils.getDateString();
        // 为图片加上时间戳
        fileName = dateName + fileName;
        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        // 映射的本地图片路径
        String uri = "";
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + fileName);
            // 上传图片到 -> “绝对路径”
            file.transferTo(newFile);
            uri = "http://localhost:8085/uploadImg/" + fileName;
            System.out.println("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail(e);
        }
        return Result.success(uri);
    }
}
