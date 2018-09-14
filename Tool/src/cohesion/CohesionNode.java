package cohesion;

public class CohesionNode {
    private String name;
    private Boolean isMethod;
    private String groupId;

    public CohesionNode(String name, Boolean isMethod, String groupId) {
        this.name = name;
        this.isMethod = isMethod;
        this.groupId = groupId;
    }

    public CohesionNode(String name, Boolean isMethod) {
        this.name = name;
        this.isMethod = isMethod;
        groupId = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMethod() {
        return isMethod;
    }

    public void setMethod(Boolean method) {
        isMethod = method;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
