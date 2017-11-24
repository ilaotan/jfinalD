package com.jfinalD.application.system.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRole<M extends BaseRole<M>> extends Model<M> implements IBean {

    public java.lang.Long getId() {
        return get("id");
    }

    public void setId(java.lang.Long id) {
        set("id", id);
    }

    public java.lang.String getName() {
        return get("name");
    }

    public void setName(java.lang.String name) {
        set("name", name);
    }

    public java.lang.String getValue() {
        return get("value");
    }

    public void setValue(java.lang.String value) {
        set("value", value);
    }

    public java.lang.String getIntro() {
        return get("intro");
    }

    public void setIntro(java.lang.String intro) {
        set("intro", intro);
    }

    public java.lang.Long getPid() {
        return get("pid");
    }

    public void setPid(java.lang.Long pid) {
        set("pid", pid);
    }

    public java.util.Date getCreatedAt() {
        return get("created_at");
    }

    public void setCreatedAt(java.util.Date createdAt) {
        set("created_at", createdAt);
    }

    public java.util.Date getUpdatedAt() {
        return get("updated_at");
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        set("updated_at", updatedAt);
    }

    public java.util.Date getDeletedAt() {
        return get("deleted_at");
    }

    public void setDeletedAt(java.util.Date deletedAt) {
        set("deleted_at", deletedAt);
    }

}