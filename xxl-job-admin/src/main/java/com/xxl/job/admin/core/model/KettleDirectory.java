package com.xxl.job.admin.core.model;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by xieyufang on 17/6/28.
 */
public class KettleDirectory {

    private int id;
    private KettleDirectory parent;
    private List<KettleDirectory> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KettleDirectory getParent() {
        return parent;
    }

    public void setParent(KettleDirectory parent) {
        this.parent = parent;
    }

    public List<KettleDirectory> getChildren() {
        return children;
    }

    public void setChildren(List<KettleDirectory> children) {
        this.children = children;
    }

}
