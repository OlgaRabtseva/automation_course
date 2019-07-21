package model;

public class Country
{
    private int id;
    private String code;
    private String name;
    private String description;
    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Country{" + "code='" + this.code + '\'' + ", name='" + this.name + '\'' + ", description='" + this.description + '\'' + '}';
    }
}
