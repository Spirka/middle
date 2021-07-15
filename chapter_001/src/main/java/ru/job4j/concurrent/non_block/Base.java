package ru.job4j.concurrent.non_block;

import java.util.Objects;

/**
 * Class Base
 *
 * @author Kseniya Dergunova
 * @since 01.06.2021
 */
public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Base)) return false;
        Base base = (Base) o;
        return getId() == base.getId() && getVersion() == base.getVersion() && Objects.equals(getName(), base.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVersion(), getName());
    }
}
