package com.edata.monitor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edata.godp.domain.security.SaveMyKeyRequest;
import com.edata.monitor.domain.overview.CompanyServiceOverview;
import com.edata.monitor.domain.overview.VehicleCheckOverview;
import com.edata.monitor.domain.overview.VehicleMaintainOverview;
import com.edata.monitor.domain.overview.VehicleServiceOverview;
import com.edata.monitor.domain.overview.VehicleStatusOverview;
import com.edata.monitor.domain.security.Identify;
import com.edata.monitor.domain.security.MyInfo;
import com.edata.monitor.domain.security.OperateLog;
import com.edata.monitor.model.Login;
import com.edata.monitor.service.OverviewService;
import com.edata.monitor.service.SecurityService;
import com.edata.monitor.util.CookieUtil;
import com.edata.monitor.util.WebUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private SecurityService securityService;
	@Autowired
	private OverviewService overviewService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String logon(Model model, HttpServletRequest request, HttpServletResponse response) {
		clearCookies(request, response);

		model.addAttribute("login", new Login());
		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String logon(@ModelAttribute("login") @Valid Login model, BindingResult binding, HttpServletRequest request, HttpServletResponse response,
			@CookieValue String verify) {
		if (binding.hasErrors()) {
			model.setVerify("");
			return "login";
		}

		try {
			// 检查验证码
			String code = model.getVerify();
			String var = CookieUtil.decoding(verify);
			if (!code.equals(var)) {
				binding.addError(new ObjectError("", "验证码错误!"));
				model.setVerify("");
				return "login";
			}

			Identify dto = securityService.login(model.getAccount(), model.getPwd());
			clearCookies(request, response);
			Cookie tokenCookie = new Cookie(CookieUtil.token, CookieUtil.makeToken(dto));
			response.addCookie(tokenCookie);

			// 添加登录日志

			List<OperateLog> logs = new ArrayList<OperateLog>(1);
			OperateLog log = new OperateLog();
			log.setAction("用户录登:" + request.getRemoteAddr());
			log.setCompanyId(dto.getCompanyId());
			log.setTime(new Date());
			log.setUser(dto.getName());
			log.setUserId(dto.getId());
			logs.add(log);

			securityService.saveOperateLogs(logs);

		} catch (Exception ex) {
			String error = ex.getMessage();
			binding.addError(new ObjectError("", error));
			model.setVerify("");
			return "login";
		}

		return "redirect:/home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		clearCookies(request, response);
		return "redirect:/";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) throws Exception {
		Identify identify = (Identify) request.getAttribute("user");

		CompanyServiceOverview companyServiceOverview = overviewService.companyServiceOverview(identify.getId());
		model.addAttribute("companyServiceOverview", companyServiceOverview);

		VehicleServiceOverview vehicleServiceOverview = overviewService.vehicleServiceOverview(identify.getId());
		model.addAttribute("vehicleServiceOverview", vehicleServiceOverview);

		VehicleMaintainOverview vehicleMaintainOverview = overviewService.vehicleMaintainOverview(identify.getId());
		model.addAttribute("vehicleMaintainOverview", vehicleMaintainOverview);

		VehicleCheckOverview vehicleCheckOverview = overviewService.vehicleCheckOverview(identify.getId());
		model.addAttribute("vehicleCheckOverview", vehicleCheckOverview);

		VehicleStatusOverview vehicleStatusOverview = overviewService.vehicleStatusOverview(identify.getId());
		model.addAttribute("vehicleStatusOverview", vehicleStatusOverview);

		return "/home/index";
	}

	@RequestMapping("/result")
	@ResponseBody
	public Object result(@RequestParam int code, @RequestParam String error) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("error", error);

		return map;
	}

	@RequestMapping("/error")
	@ResponseBody
	public Object error(HttpServletRequest request) {
		Exception ex = (Exception) request.getAttribute("exception");
		String message = ex.getMessage();
		if (ex.getClass().equals(UndeclaredThrowableException.class)) {
			UndeclaredThrowableException e = (UndeclaredThrowableException) ex;
			message = e.getUndeclaredThrowable().getMessage();
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		map.put("error", message);

		return map;
	}

	@RequestMapping(value = "/myinfo.form", method = RequestMethod.GET)
	public String myinfo(Model model, HttpServletRequest request) throws Exception {
		Identify user = (Identify) request.getAttribute("user");
		MyInfo info = securityService.getMyInfo(user.getId());
		model.addAttribute("myinfo", info);

		return "/myinfo.form";
	}

	@RequestMapping(value = "/myinfo.form", method = RequestMethod.POST)
	public String myinfo(@ModelAttribute("myinfo") @Valid MyInfo info, BindingResult binding, Model model, RedirectAttributes r) {
		if (binding.hasErrors())
			return "/myinfo.form";

		try {
			securityService.saveMyinfo(info);
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	@RequestMapping(value = "/mykey.form", method = RequestMethod.GET)
	public String mykey(Model model) {
		model.addAttribute("mykey", new SaveMyKeyRequest());
		return "/mykey.form";
	}

	@RequestMapping(value = "/mykey.form", method = RequestMethod.POST)
	public String mykey(@ModelAttribute("mykey") @Valid SaveMyKeyRequest mykey, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes r) {
		try {
			Identify user = (Identify) request.getAttribute("user");
			securityService.saveMyKey(user.getId(), mykey.getOldKey(), mykey.getNewKey(), mykey.getConfirmKey());
			WebUtil.success(r);
		} catch (Exception ex) {
			WebUtil.error(r, ex.getMessage());
		}

		return "redirect:/result";
	}

	private void clearCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return;
		for (int i = 0, len = cookies.length; i < len; i++) {
			Cookie cookie = new Cookie(cookies[i].getName(), "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	@RequestMapping(value = "/memory.form", method = RequestMethod.GET)
	public String memory() {
		return "/memory.form";
	}

	@RequestMapping(value = "/code.verify", method = RequestMethod.GET)
	public void verify(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

		response.addCookie(new Cookie("verify", CookieUtil.encoding(sRand)));
		// 图象生效
		g.dispose();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/* 该方法主要作用是获得随机生成的颜色 */
	public Color getRandColor(int s, int e) {
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
