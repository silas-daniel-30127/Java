package projects.safehome_with_GUI;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author daniel.silas
 */

public class AccessLog implements Serializable {
    private String tenantName;
    private LocalDateTime dateTime;
    private String operation;
    private DoorStatus doorStatus;
    private String errorMessage;

    public AccessLog(String tenantName, LocalDateTime dateTime, String operation, DoorStatus doorStatus, String errorMessage) {
        this.tenantName = tenantName;
        this.dateTime = dateTime;
        this.operation = operation;
        this.doorStatus = doorStatus;
        this.errorMessage = errorMessage;
    }

    public AccessLog() {

    }

    @Override
    public String toString() {
        return "AccessLog{" +
                "tenantName='" + tenantName + '\'' +
                ", dateTime=" + dateTime +
                ", operation='" + operation + '\'' +
                ", doorStatus=" + doorStatus +
                ", errorMessage='" + errorMessage + '\'' +
                '}' + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessLog accessLog = (AccessLog) o;
        return Objects.equals(tenantName, accessLog.tenantName) &&
                Objects.equals(dateTime, accessLog.dateTime) &&
                Objects.equals(operation, accessLog.operation) &&
                doorStatus == accessLog.doorStatus &&
                Objects.equals(errorMessage, accessLog.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantName, dateTime, operation, doorStatus, errorMessage);
    }
}
