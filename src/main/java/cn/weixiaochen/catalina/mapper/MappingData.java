package cn.weixiaochen.catalina.mapper;

import cn.weixiaochen.catalina.Context;
import cn.weixiaochen.catalina.Host;
import cn.weixiaochen.catalina.Wrapper;

/**
 * Mapping data.
 *
 * @author 0914xc 2022/3/6
 */
public class MappingData {

    public Host host = null;
    public Context context = null;
    public Context[] contexts = null;
    public Wrapper wrapper = null;

    public void recycle() {
        host = null;
        context = null;
        contexts = null;
        wrapper = null;
    }

}
