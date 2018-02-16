package com.bulkprocess.bulkprocess.pojo;

public class RAndWFromDBPOJO {

    private final int id;
    private final String f_name;
    private final String l_name;
    private final long phone;

    public RAndWFromDBPOJO(int id, String f_name, String l_name, long phone) {
        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public long getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "RAndWFromDBPOJO{" +
                "id=" + id +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", phone=" + phone +
                '}';
    }
}
