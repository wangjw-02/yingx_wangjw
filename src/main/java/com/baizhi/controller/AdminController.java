package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("getImageCode")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response){

        try {
            HttpSession session = request.getSession();

            //获得随机字符
            String securityCode = ImageCodeUtil.getSecurityCode();
            session.setAttribute("code",securityCode);
            //打印随机字符
            System.out.println("===="+securityCode);
            response.setContentType("image/png");
            response.setHeader("cache", "no-cache");
            ServletOutputStream outputStream = response.getOutputStream();
            //生成图片
            BufferedImage image = ImageCodeUtil.createImage(securityCode);
            //将生成的验证码图片以png(1.png)的格式输出到D盘        "D:\\1.png"   ==  "D:/1.png"
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @ResponseBody
    @RequestMapping("login")
    public String queryAdmin(Admin admin, HttpSession session) {
        String code = (String) session.getAttribute("code");
        String message = "";
        if(code.equals(admin.getEnCode())){
            Admin loginAdmin = adminService.queryByNameAndPwd(admin.getName());
            if(loginAdmin!=null){
                if(loginAdmin.getPassword().equals(admin.getPassword())){
                    session.setAttribute("admin",loginAdmin);
                    System.out.println("success");
                    return "success";
                }else{
                    message="密码错误，请重新登录";
                    return message;
                }

            }else{
                message="该用户不存在,请重新登录";
                System.out.println(message);
                return message;
            }
        }else {
            message="验证码不正确";
            System.out.println(message);
            return message;
        }
    }

    @RequestMapping("exit")
    public String exit(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }

}
