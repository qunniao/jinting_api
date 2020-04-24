package com.zhancheng.commom;

import com.zhancheng.config.security.Verify;
import com.zhancheng.constant.R;
import com.zhancheng.util.FileUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @author BianShuHeng
 * @decription
 * @project FileController
 * @date 2019/9/29 18:04
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Verify
    @PostMapping("/addImage")
    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(name = "files", value = "上传文件集合", required = true)
    public R<String> addFile(List<MultipartFile> files) {
        String image = FileUtils.uploadImage(files);
        return R.ok(image);
    }
}
