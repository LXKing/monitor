package mmp.gps.logic.util;

import java.util.Iterator;
import java.util.List;

import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class WebUtil {

    public static String join(List errors) {
        StringBuilder builder = new StringBuilder();
        Iterator var2 = errors.iterator();

        while (var2.hasNext()) {
            ObjectError objectError = (ObjectError) var2.next();
            builder.append(objectError.getDefaultMessage());
            builder.append(System.getProperty("line.separator"));
        }

        return builder.toString();
    }

    public static void success(RedirectAttributes r) {
        r.addAttribute("code", Integer.valueOf(0));
        r.addAttribute("error", "");
    }

    public static void error(RedirectAttributes r, String message) {
        r.addAttribute("code", Integer.valueOf(1));
        r.addAttribute("error", message == null ? "操作失败！" : message);
    }
}
