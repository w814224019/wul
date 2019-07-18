package com.select.wuliu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.select.wuliu.code.Captcha;
import com.select.wuliu.code.GifCaptcha;
import com.select.wuliu.code.ValidateCodeProperties;

/**
 * 登录生成验证码类
 * @author Admin
 *
 */


@Controller
public class CodeController extends BaseController {

	
	 private static final String CODE_KEY = "_code";
	 private static Logger log=LoggerFactory.getLogger(CodeController.class);
	@GetMapping(value = "gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            ValidateCodeProperties febsProperties=new ValidateCodeProperties();
            Captcha captcha = new GifCaptcha(
                    febsProperties.getWidth(),
                    febsProperties.getHeight(),
                    febsProperties.getLength());
            HttpSession session = request.getSession(true);
           // System.err.println(session+"验证吗");
            captcha.out(response.getOutputStream());
            session.removeAttribute(CODE_KEY);
            session.setAttribute(CODE_KEY, captcha.text().toLowerCase());
        } catch (Exception e) {
            log.error("图形验证码生成失败", e);
        }
    }

	
	
}
