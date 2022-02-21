package com.example.wikidesign.filter;

import java.util.List;

public class FilterModel {

    private String group;
    private List<String> lstGroup;

    public FilterModel(String group, List<String> lstGroup) {
        this.group = group;
        this.lstGroup = lstGroup;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getLstGroup() {
        return lstGroup;
    }

    public void setLstGroup(List<String> lstGroup) {
        this.lstGroup = lstGroup;
    }
}
