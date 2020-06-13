package projects.safehome;

import java.util.Objects;

/**
 * @author daniel.silas
 */

public class Tenant {
    private String name;

    public Tenant(String name) {
        this.name = name;
    }

    public Tenant() {

    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(name, tenant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
