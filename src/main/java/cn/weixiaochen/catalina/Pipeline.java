package cn.weixiaochen.catalina;

/**
 * @author 0914xc 2021/12/12
 */
public interface Pipeline extends Contained {

    Valve getBasic();

    void setBasic(Valve valve);

    void addValue(Valve valve);

    void removeValue(Valve valve);

    Valve[] getValues();

    /**
     * @return the Valve instance that has been distinguished as the basic
     * Valve for this Pipeline (if any).
     */
    Valve getFirst();
}
