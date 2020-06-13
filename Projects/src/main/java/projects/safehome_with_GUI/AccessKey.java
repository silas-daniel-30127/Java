package projects.safehome_with_GUI;

import java.util.Objects;

/**
 * @author daniel.silas
 */

public class AccessKey {
    private String pin;

    public AccessKey(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessKey accessKey = (AccessKey) o;
        return Objects.equals(pin, accessKey.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
