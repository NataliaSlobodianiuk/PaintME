package com.paintme.services;

import com.paintme.PaintMEException;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class PropertiesServiceImpl implements PropertiesService {
    private String propertiesFileName;

    public String getProperty(String propertyName) throws PaintMEException {
        Properties props = this.loadProperties();

        return props.getProperty(propertyName);
    }

    public void deleteProperty(String propertyName) throws  PaintMEException {
        Properties props = this.loadProperties();

        props.remove(propertyName);

        this.storeProperties(props);
    }

    public void updateProperty(String propertyName, String newValue) throws PaintMEException {
        Properties props = this.loadProperties();

        props.setProperty(propertyName, newValue);

        this.storeProperties(props);
    }

    public void addProperty(String propertyName, String value) throws PaintMEException {
        Properties props = this.loadProperties();

        props.put(propertyName, value);

        this.storeProperties(props);
    }

    public Properties loadProperties() throws PaintMEException {
        Properties props = new Properties();

        try {
            FileInputStream in = this.getFileForReading();
            props.load(in);
            in.close();
        }
        catch (IOException exception) {
            throw new PaintMEException(
                    "Project properties couldn`t be loaded. " +
                            exception.getMessage(), exception);
        }

        return props;
    }

    public void storeProperties(Properties props) throws PaintMEException {
        try {
            FileOutputStream out = this.getFileForWriting();
            props.store(out, null);
            out.close();
        }
        catch (IOException exception) {
            throw new PaintMEException(
                    "Project properties couldn`t be stored. " +
                            exception.getMessage(), exception);
        }
    }

    private FileOutputStream getFileForWriting() throws  IOException {
        FileOutputStream out;
        try {
            out = new FileOutputStream(
                    "src/main/resources/" + this.propertiesFileName);
        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException(
                    "Property file '" + this.propertiesFileName
                            + "' not found in the classpath");
        }

        return out;
    }
    private FileInputStream getFileForReading() throws  IOException {
        FileInputStream in;
        try {
            in = new FileInputStream(
                    "src/main/resources/" + this.propertiesFileName);
        } catch (FileNotFoundException exception) {
            throw new FileNotFoundException(
                    "Property file '" + this.propertiesFileName
                            + "' not found in the classpath");
        }

        return in;
    }

    public void setPropertiesFileName(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }
}
