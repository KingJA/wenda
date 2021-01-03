package com.kingja.wenda.cotroller;

import com.kingja.wenda.dto.FileDTO;
import com.kingja.wenda.threepart.qiniuyun.QiniuResultEnum;
import com.kingja.wenda.threepart.qiniuyun.QiniuUpload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:TODO
 * Create Time:2021/1/3 0003 3:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
public class FileController {

    /**
     * 七牛云图片上传
     * @param file
     * @return
     */
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO uploadFile(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {
        FileDTO fileDTO = new FileDTO();
        String imgUrl;
        try {
            imgUrl = QiniuUpload.updateFile(file);
            fileDTO.setUrl(imgUrl);
            fileDTO.setSuccess(QiniuResultEnum.SUCCESS.getCode());
            fileDTO.setMessage(QiniuResultEnum.SUCCESS.getMessage());
        } catch (Exception e) {
            fileDTO.setSuccess(QiniuResultEnum.ERROR.getCode());
            fileDTO.setMessage(QiniuResultEnum.ERROR.getMessage());
        }
        return fileDTO;
    }
}
