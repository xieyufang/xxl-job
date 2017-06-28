package com.xxl.job.admin.core.model;

import java.util.List;

/**
 * Created by xieyufang on 17/6/28.
 */
public class XxlJobKettleDirectory {

    private int id;
    private XxlJobKettleDirectory parent;
    private List<XxlJobKettleDirectory> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public XxlJobKettleDirectory getParent() {
        return parent;
    }

    public void setParent(XxlJobKettleDirectory parent) {
        this.parent = parent;
    }

    public List<XxlJobKettleDirectory> getChildren() {
        return children;
    }

    public void setChildren(List<XxlJobKettleDirectory> children) {
        this.children = children;
    }
}
