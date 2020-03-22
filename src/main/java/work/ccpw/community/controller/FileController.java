package work.ccpw.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.ccpw.community.dto.FileDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @description:
 * @author: cone
 * @create: 2020-03-22 20:06
 **/
@Controller
public class FileController {


    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        try {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl("/imgs/腾讯错误.png");
            return fileDTO;
        } catch (Exception e) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败");
            return fileDTO;
        }
    }
}
