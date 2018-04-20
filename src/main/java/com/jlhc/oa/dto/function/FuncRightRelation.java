package com.jlhc.oa.dto.function;

import java.io.Serializable;

public class FuncRightRelation implements Serializable {
    private Integer funcRightId;

    private Integer funcId;

    private Integer rightId;

    private static final long serialVersionUID = 1L;

    public Integer getFuncRightId() {
        return funcRightId;
    }

    public void setFuncRightId(Integer funcRightId) {
        this.funcRightId = funcRightId;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", funcRightId=").append(funcRightId);
        sb.append(", funcId=").append(funcId);
        sb.append(", rightId=").append(rightId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FuncRightRelation other = (FuncRightRelation) that;
        return (this.getFuncRightId() == null ? other.getFuncRightId() == null : this.getFuncRightId().equals(other.getFuncRightId()))
            && (this.getFuncId() == null ? other.getFuncId() == null : this.getFuncId().equals(other.getFuncId()))
            && (this.getRightId() == null ? other.getRightId() == null : this.getRightId().equals(other.getRightId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFuncRightId() == null) ? 0 : getFuncRightId().hashCode());
        result = prime * result + ((getFuncId() == null) ? 0 : getFuncId().hashCode());
        result = prime * result + ((getRightId() == null) ? 0 : getRightId().hashCode());
        return result;
    }
}