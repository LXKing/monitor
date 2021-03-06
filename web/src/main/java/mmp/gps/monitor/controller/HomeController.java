package mmp.gps.monitor.controller;

import mmp.gps.domain.security.IdentityDto;
import mmp.gps.domain.security.MyInfo;
import mmp.gps.domain.security.SaveMyKeyRequest;
import mmp.gps.monitor.model.Login;
import mmp.gps.monitor.service.SecurityService;
import mmp.gps.monitor.util.ResponseEntityWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
    @Autowired
    private SecurityService securityService;

    @GetMapping(value = "/")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {


        Subject subject = SecurityUtils.getSubject();


        if (subject.isAuthenticated() || subject.isRemembered()) {

            subject.logout();
        }
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("login");


        return modelAndView;

    }


    @PostMapping(value = "/")
    @ResponseBody
    public Object login(@ModelAttribute("login") @Valid Login model, BindingResult bindings, HttpSession session, HttpServletResponse response) throws Exception {

        // ModelAndView modelAndView = new ModelAndView();
        Map<String, String> modelAndView = new HashMap();
        if (bindings.hasErrors()) {
            model.setVerify("");
            List<FieldError> errors = bindings.getFieldErrors();

            errors.forEach(fieldError -> modelAndView.put(fieldError.getField(), fieldError.getDefaultMessage()));

            modelAndView.put("ok", "false");


            return modelAndView;
        }


        // 检查验证码
        String code = model.getVerify();
        String var = (String) session.getAttribute("verify");
        // String var = CookieUtil.decoding(verify);
        // cookie 验证码匹配
        if (!code.equals(var)) {
            // binding.addError(new ObjectError("", "验证码错误!"));
            model.setVerify("");
            modelAndView.put("error", "验证码错误");

            // modelAndView.setViewName("login");
            modelAndView.put("ok", "false");
            return modelAndView;
        }
        // cookie加token
        // Identity dto = securityService.login(model.getAccount(), model.getPwd());


        // clearCookies(request, response);
        // Cookie tokenCookie = new Cookie(CookieUtil.token, CookieUtil.makeToken(dto));
        // response.addCookie(tokenCookie);

        // 添加登录日志

        // List<OperateLog> logs = new ArrayList<OperateLog>(1);
        // OperateLog log = new OperateLog();
        // log.setAction("用户录登:" + request.getRemoteAddr());
        // log.setCompanyId(dto.getCompanyId());
        // log.setTime(new Date());
        // log.setUser(dto.getName());
        // log.setUserId(dto.getId());
        // logs.add(log);
        //
        // securityService.saveOperateLogs(logs);


        // Shiro Test
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {


            UsernamePasswordToken token = new UsernamePasswordToken(model.getAccount(), model.getPwd());
            // 默认，懒得搞了
            // TODO
            token.setRememberMe(true);

            subject.login(token);

            token.clear();


        }
        response.setStatus(1024);
        // modelAndView.setViewName("redirect:home");
        modelAndView.put("ok", "true");


        return modelAndView;

    }



    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // clearCookies(request, response);

        // Shiro Test
        Subject subject = SecurityUtils.getSubject();
        // Session session = subject.getSession();

        // isAuthenticated false 证明不是登录过的
        // isRemembered true 证明是没登陆直接通过记住我功能进来的

        if (subject.isAuthenticated() || subject.isRemembered()) {

            subject.logout();
        }

        return "redirect:/";
    }


    @GetMapping(value = "/home")
    public ModelAndView index(Model model, HttpServletRequest request) throws Exception {


        IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("index");

        //        System.out.println(identity.getName());

        modelAndView.addObject("name", identity.getName());
        modelAndView.addObject("com", identity.getFULLNAME());
        System.out.println(identity.getFULLNAME());
        return modelAndView;

    }


    @GetMapping("/error")
    public ModelAndView error(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", model);
        return modelAndView;

    }


    @GetMapping(value = "/code.verify")
    public void verify(HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 在内存中创建图象
        int width = 75, height = 32;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        // 画边框
        g.setColor(getRandColor(160, 200));
        g.drawRect(0, 0, width - 1, height - 1);
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(4位数字)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 14, 20);
        }

        // 将认证码存入SESSION
        // exo me?
        session.setAttribute("verify", sRand);
        // response.addCookie(new Cookie("verify", CookieUtil.encoding(sRand)));
        // 图象生效
        g.dispose();
        // 输出图象到页面
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    /* 该方法主要作用是获得随机生成的颜色 */
    private Color getRandColor(int s, int e) {
        Random random = new Random();
        if (s > 255)
            s = 255;
        if (e > 255)
            e = 255;
        int r, g, b;
        r = s + random.nextInt(e - s); // 随机生成RGB颜色中的r值
        g = s + random.nextInt(e - s); // 随机生成RGB颜色中的g值
        b = s + random.nextInt(e - s); // 随机生成RGB颜色中的b值
        return new Color(r, g, b);
    }
}
