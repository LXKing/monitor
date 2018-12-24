package mmp.gps.domain.gateway;

/**
 * 指令应答
 */
public class InstructStatusRequest {
    private String id;
    private String number;
    private String result;

    public InstructStatusRequest() {
    }

    public InstructStatusRequest(String id, String number, String result) {
        this.id = id;
        this.number = number;
        this.result = result;
    }

    /**
     * 获取记录号
     *
     * @return 记录号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录号
     *
     * @param id 记录号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取设备号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置设备号
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取结果
     *
     * @return 结果
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置结果
     *
     * @param result 结果
     */
    public void setResult(String result) {
        this.result = result;
    }
}
