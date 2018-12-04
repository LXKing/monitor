package mmp.gps.logic.interceptor;


public class AuthorizationException extends Exception {

    private static final long serialVersionUID = 1L;


    public String getMessage() {
        return "此操作未授权！";
    }
}
