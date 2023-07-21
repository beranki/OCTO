package pkg.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import pkg.hardware.HardwareDevice;
import pkg.hardware.dcmotor.CRServo;
import pkg.hardware.dcmotor.DcMotor;
import pkg.hardware.servo.Servo;

public class HardwareMap implements Iterable {

    /* DEVICE MAPPING INNER CLASS FOR HWMAP */
    class DeviceMapping<DEVICE_TYPE extends HardwareDevice> implements Iterable {

        Class<DEVICE_TYPE> deviceTypeClass;
        HashMap<String, DEVICE_TYPE> devices;

        public DeviceMapping(Class<DEVICE_TYPE> deviceTypeClass) {
            this.deviceTypeClass = deviceTypeClass;
            devices = new HashMap<>();
        }

        public boolean contains(String deviceName) {
            return devices.containsKey(deviceName);
        }

        public Set<Map.Entry<String, DEVICE_TYPE>> entrySet() {
            return devices.entrySet();
        }

        public DEVICE_TYPE get(String deviceName) {
            return devices.get(deviceName);
        }

        public Class<DEVICE_TYPE> getDeviceTypeClass() {
            return deviceTypeClass;
        }

        public Iterator<DEVICE_TYPE> iterator() {
            ArrayList<DEVICE_TYPE> arr = new ArrayList<>(devices.values());
            return arr.iterator();
        }

        public void put(String deviceName, HardwareDevice device) {
            DEVICE_TYPE casted = (DEVICE_TYPE)device;
            devices.put(deviceName, casted);
        }

        public boolean remove(String deviceName) {
            return devices.remove(deviceName, devices.get(deviceName));
        }

        public int size() {
            return devices.size();
        }
    }

    //DeviceMapping<AccelerationSensor> accelerationSensor;
    protected List<DeviceMapping<? extends HardwareDevice>> allDeviceMappings;
    protected List<HardwareDevice> allDevicesList;
    protected Map<String, List<HardwareDevice>> allDevicesMap;
    //DeviceMapping<AnalogInput> analogInput;
    //DeviceMapping<AnalogOutput> analogOutput;
    //DeviceMapping<ColorSensor> colorSensor;
    //DeviceMapping<CompassSensor> compassSensor;
    DeviceMapping<CRServo> crservo;
    DeviceMapping<DcMotor> dcMotor;
    //DeviceMapping<DeviceInterfaceModule> deviceInterfaceModule;
    //DeviceMapping<DigitalChannel> digitalChannel;
    //DeviceMapping<GyroSensor> gyroSensor;
    //DeviceMapping<I2cDevice> i2cDevice;
    //DeviceMapping<I2cDeviceSynch> i2cDeviceSynch;
    //DeviceMapping<IrSeekerSensor> irSeekerSensor;
    //DeviceMapping<LED> led;
    //DeviceMapping<LightSensor> lightSensor;
    //Object lock;
    //DeviceMapping<OpticalDistanceSensor> opticalDistanceSensor;
    DeviceMapping<Servo> servo;
    //DeviceMapping<TouchSensor> touchSensor;
    //DeviceMapping<TouchSensorMultiplexer> touchSensorMultiplexer;
    //DeviceMapping<UltrasonicSensor> ultrasonicSensor;
    //DeviceMapping<VoltageSensor> voltageSensor;

    public HardwareMap() {
        //accelerationSensor = new DeviceMapping<>(AccelerationSensor.class);
        allDeviceMappings = new ArrayList<DeviceMapping<? extends HardwareDevice>>();
        allDevicesList = new ArrayList<HardwareDevice>();
        allDevicesMap = new HashMap<String, List<HardwareDevice>>();

        dcMotor = new DeviceMapping<DcMotor>(DcMotor.class);
        crservo = new DeviceMapping<CRServo>(CRServo.class);
        

        DeviceMapping<?> dms[] = {dcMotor, crservo};
        allDeviceMappings.addAll(Arrays.asList(dms));
        
        for (DeviceMapping<? extends HardwareDevice> deviceMapping: allDeviceMappings) {
            allDevicesList.addAll(deviceMapping.devices.values());
        }

        for (DeviceMapping<? extends HardwareDevice> deviceMapping: allDeviceMappings) {
            allDevicesMap.put(deviceMapping.getDeviceTypeClass().getSimpleName(), new ArrayList<>(deviceMapping.devices.values()));
        }
    }


    public <T extends HardwareDevice> T get(Class<T> classOrInterface, String deviceName) {
        for (DeviceMapping<? extends HardwareDevice> deviceMapping : allDeviceMappings) {
            System.out.println(deviceMapping.entrySet());
            if (deviceMapping.getDeviceTypeClass().equals(classOrInterface)) {
                return (T)(deviceMapping.get(deviceName));
            }
        }

        return null;
    }

    public HardwareDevice get(String deviceName) {
        for (DeviceMapping<? extends HardwareDevice> deviceMapping : allDeviceMappings) {
                if (deviceMapping.contains(deviceName)) return deviceMapping.get(deviceName);
        }

        return null;
    }

    public <T> List<T> getAll(Class <? extends T> classOrInterface) {
        for (DeviceMapping<? extends HardwareDevice> deviceMapping : allDeviceMappings) {
            if (deviceMapping.getDeviceTypeClass().equals(classOrInterface)) {
                return new ArrayList(deviceMapping.devices.values());
            }
        }

        return null;
    }

    public Set<String> getNamesOf(HardwareDevice device) {
        Set<String> names = new HashSet<String>();
        for (DeviceMapping<? extends HardwareDevice> deviceMapping: allDeviceMappings) {
            for (Entry<String, ? extends HardwareDevice> entry: deviceMapping.entrySet()) {
                if (device.equals(entry.getValue())) {
                    names.add(entry.getKey());
                }
            }
        }

        return names;
    }

    public Iterator<HardwareDevice> iterator() {
        return allDevicesList.iterator();
    }

    public void put(String deviceName, HardwareDevice device) {
        System.out.println(device.getDeviceName());        
        allDevicesMap.get(device.getDeviceName()).add(device);
        System.out.println(allDevicesMap.get(device.getDeviceName()));
        
        for (DeviceMapping<? extends HardwareDevice> dmp : allDeviceMappings) {
            if (dmp.getDeviceTypeClass().getSimpleName().equals(device.getDeviceName())) {
                dmp.put(deviceName, device);
            }
        }

        allDevicesList.add(device);
    }

    public boolean remove(String deviceName, HardwareDevice device) {
        return allDevicesMap.get(deviceName).remove(device);
    }

    public List<HardwareDevice> getAllDevices() {
        return allDevicesList;
    }

    public int size() {
        return allDevicesList.size();
    }

}