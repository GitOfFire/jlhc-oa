package com.jlhc.oa.dto.function;

import com.jlhc.oa.dto.role.Role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Right implements Serializable {
    private Integer rightId;

    private String rightGroupIdentity;

    private String rightName;

    private String rightData;

    private String rightDescription;

    private String rightIdentity;

    private Set<Role> roles = new HashSet<>();

    private static final long serialVersionUID = 1L;

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public String getRightGroupIdentity() {
        return rightGroupIdentity;
    }

    public void setRightGroupIdentity(String rightGroupIdentity) {
        this.rightGroupIdentity = rightGroupIdentity == null ? null : rightGroupIdentity.trim();
    }
    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName == null ? null : rightName.trim();
    }

    public String getRightData() {
        return rightData;
    }

    public void setRightData(String rightData) {
        this.rightData = rightData == null ? null : rightData.trim();
    }

    public String getRightDescription() {
        return rightDescription;
    }

    public void setRightDescription(String rightDescription) {
        this.rightDescription = rightDescription == null ? null : rightDescription.trim();
    }

    public String getRightIdentity() {
        return rightIdentity;
    }

    public void setRightIdentity(String rightIdentity) {
        this.rightIdentity = rightIdentity == null ? null : rightIdentity.trim();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Right right = (Right) o;
        return Objects.equals(rightId, right.rightId) &&
                Objects.equals(rightGroupIdentity, right.rightGroupIdentity) &&
                Objects.equals(rightName, right.rightName) &&
                Objects.equals(rightData, right.rightData) &&
                Objects.equals(rightDescription, right.rightDescription) &&
                Objects.equals(rightIdentity, right.rightIdentity) &&
                Objects.equals(roles, right.roles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rightId, rightGroupIdentity, rightName, rightData, rightDescription, rightIdentity, roles);
    }

    @Override
    public String toString() {
        return "Right{" +
                "rightId=" + rightId +
                ", rightGroupIdentity='" + rightGroupIdentity + '\'' +
                ", rightName='" + rightName + '\'' +
                ", rightData='" + rightData + '\'' +
                ", rightDescription='" + rightDescription + '\'' +
                ", rightIdentity='" + rightIdentity + '\'' +
                ", roles=" + roles +
                '}';
    }
}