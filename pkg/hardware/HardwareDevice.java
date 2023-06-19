package pkg.hardware;

public interface HardwareDevice {
    public void close();
    public String getConnectionInfo();
    public String getDeviceName();
    public int getVersion();
    public void resetDeviceConfigurationForOpMode();
}
