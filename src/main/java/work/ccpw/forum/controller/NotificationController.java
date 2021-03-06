package work.ccpw.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import work.ccpw.forum.dto.NotificationDTO;
import work.ccpw.forum.enums.NotificationTypeEnum;
import work.ccpw.forum.model.User;
import work.ccpw.forum.service.NotificationService;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @description: 通知
 * @author: cone
 * @create: 2020-03-22 16:04
 **/
@Controller
public class NotificationController {


    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id") Long id) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);

        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }
    }
}
