package cn.weixiaochen.coyote;

/**
 * @author 0914xc 2022/3/14
 */
public abstract class AbstractProccesor implements Processor {

    protected Adapter adapter;

    /**
     * Set the associated adapter.
     *
     * @param adapter the new adapter
     */
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }


    /**
     * Get the associated adapter.
     *
     * @return the associated adapter
     */
    public Adapter getAdapter() {
        return adapter;
    }
}
