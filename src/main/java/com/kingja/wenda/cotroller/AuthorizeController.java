package com.kingja.wenda.cotroller;

import com.kingja.wenda.dto.AccessTokenDTO;
import com.kingja.wenda.dto.GithubUser;
import com.kingja.wenda.mapper.UserMapper;
import com.kingja.wenda.model.User;
import com.kingja.wenda.provider.GithubProvider;
import com.kingja.wenda.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:Github认证
 * Create Time:2020/12/22 0022 23:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider githubProvider;

    @Value("${github.client.id}")
    String clientId;
    @Value("${github.client.secret}")
    String clientSecret;
    @Value("${github.redirect.uri}")
    String redirectUri;

//    @Autowired
//    UserMapper userMapper;

    @Autowired
    UserService userService;

    @GetMapping("callback")
    public String callback(@RequestParam("code") String code, @RequestParam("state") String state
            , HttpServletRequest request
            , HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String githubToken = githubProvider.getGithubToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(githubToken);
        if (githubUser != null) {
            System.out.println(githubUser);
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setBio(githubUser.getBio());
//            userMapper.insertSelective(user);
            userService.createOrUpdateUser(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("logout")
    public String callback(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
