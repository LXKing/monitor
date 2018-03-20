package com.edata.monitor.util;

import java.util.List;

import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class WebUtil {
	public static String join(List<ObjectError> errors) {
		StringBuilder builder = new StringBuilder();
		for (ObjectError objectError : errors) {
			builder.append(objectError.getDefaultMessage());
			builder.append(System.getProperty("line.separator"));
		}
		return builder.toString();
	}

	public static void success(RedirectAttributes r) {
		r.addAttribute("code", 0);
		r.addAttribute("error", "");
	}

	public static void error(RedirectAttributes r, String message) {
		r.addAttribute("code", 1);
		r.addAttribute("error", message == null ? "操作失败！" : message);
	}
}
