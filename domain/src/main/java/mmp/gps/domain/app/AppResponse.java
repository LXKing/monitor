package mmp.gps.domain.app;

/**
 * 应用响应类
 */
public class AppResponse {
    private int code;
    private String error = "";
    private Object result;

    /**
     * 获取错误代码
     *
     * @return 0：成功 1：失败
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置错误代码
     *
     * @param code 0：成功 1：失败
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    public String getError() {
        return error;
    }

    /**
     * 设置错误信息
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * 设置错误信息
     */
    public void setErrorMessage(String message) {
        this.code = 1;
        this.error = message;
    }

    /**
     * 获取结果对象
     */
    public Object getResult() {
        return result;
    }

    /**
     * 设置结果对象
     */
    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AppResponse{" + "code=" + code + ", error='" + error + '\'' + ", result=" + result + '}';
    }
}
