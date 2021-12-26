package cn.weixiaochen.coyote;

/**
 * @author 魏小宸 2021/12/11
 */
public class Request {

    /**
     * 请求路径
     */
    private String url;

    /**
     * 请求方法
     */
    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
