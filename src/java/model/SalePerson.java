
package model;

public class SalePerson {
    private String id;
    private String name;
    private String bd;
    private String address;
    private String sex;

    public SalePerson(String id, String name, String bd, String address, String sex) {
        this.id = id;
        this.name = name;
        this.bd = bd;
        this.address = address;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
