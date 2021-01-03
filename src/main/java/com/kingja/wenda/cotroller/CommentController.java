package com.kingja.wenda.cotroller;

import com.kingja.wenda.dto.CommentDTO;
import com.kingja.wenda.enums.CommentEnum;
import com.kingja.wenda.enums.ResultEnum;
import com.kingja.wenda.model.Comment;
import com.kingja.wenda.model.User;
import com.kingja.wenda.result.ApiResult;
import com.kingja.wenda.service.CommentService;
import com.kingja.wenda.vo.CommentVO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:TODO
 * Create Time:2020/12/30 0030 2:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
public class CommentController {
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    CommentService commentService;

    @PostMapping("comment")
    @ResponseBody
    public ApiResult comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ApiResult.error(ResultEnum.NOT_LOGIN);
        }
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setUserId(user.getId());
        commentService.insert(comment,user);
        return ApiResult.successMsg();
    }

    @GetMapping("/comment/{id}")
    @ResponseBody
    public ApiResult comment(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response,
                             ModelMap modelMap) {
        List<CommentVO> comments = commentService.listByTargetId(id, CommentEnum.COMMENT);
        modelMap.put("comments", comments);
        modelMap.put("commentId", id);
        WebContext ctx = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), modelMap);
        // template是Thymeleaf模板的名字，一般在Spring boot项目中resources/templates文件夹下有个template.html文件与之对应
        String content = thymeleafViewResolver.getTemplateEngine().process("subComment", ctx);
        return ApiResult.success(content);
    }
}
