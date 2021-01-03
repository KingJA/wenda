package com.kingja.wenda.cotroller;

import com.kingja.wenda.enums.TagEnum;
import com.kingja.wenda.exception.GlobalException;
import com.kingja.wenda.mapper.QuestionMapper;
import com.kingja.wenda.mapper.TagMapper;
import com.kingja.wenda.mapper.UserMapper;
import com.kingja.wenda.model.Question;
import com.kingja.wenda.model.Tag;
import com.kingja.wenda.model.TagExample;
import com.kingja.wenda.model.User;
import com.kingja.wenda.model.UserExample;
import com.kingja.wenda.service.QuestionService;
import com.kingja.wenda.vo.TagVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * Description:TODO
 * Create Time:2020/12/22 0022 22:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
@Validated
public class PublishController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    TagMapper tagMapper;


    @GetMapping("/publish")
    public String publish(Model model) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andTypeEqualTo(TagEnum.LEVEL_1.getType());
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        List<TagVO> tagVOS = new ArrayList<>();
        for (Tag tag : tags) {
            TagVO tagVO = new TagVO();
            BeanUtils.copyProperties(tag,tagVO);
            TagExample subTagExample = new TagExample();
            subTagExample.createCriteria().andTypeEqualTo(TagEnum.LEVEL_2.getType()).andFidEqualTo(tag.getId());
            List<Tag> subTags = tagMapper.selectByExample(subTagExample);
            tagVO.setSubTags(subTags);
            tagVOS.add(tagVO);
        }
        model.addAttribute("tags", tagVOS);
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Question question = questionMapper.selectByPrimaryKey(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "publish";
    }


    @GetMapping("/publish2")
    public String publish2() {
        if (true) {
            throw new GlobalException(1, "报错");
        }

        return "admin/sub";
    }


    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title") String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "id", required = false) Integer id,
                            Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("token")) {
//                String token = cookie.getValue();
//                UserExample userExample = new UserExample();
//                userExample.createCriteria().andTokenEqualTo(token);
//                user = userMapper.selectOneByExample(userExample);
//                request.getSession().setAttribute("user", user);
//                break;
//            }
//        }
        if (user == null) {
            model.addAttribute("error", "账号未登陆");
            return "publish";
        }
        Question question = new Question();
        question.setCreator(user.getId());
        question.setDescription(description);
        question.setTag(tag);
        question.setTitle(title);
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
