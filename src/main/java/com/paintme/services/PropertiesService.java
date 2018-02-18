package com.paintme.services;

import com.paintme.PaintMEException;

import java.util.Properties;

public interface PropertiesService {
    String getProperty(String propertyName) throws PaintMEException;
    void deleteProperty(String propertyName) throws  PaintMEException;
    void updateProperty(String propertyName, String newValue) throws PaintMEException;
    void addProperty(String propertyName, String value) throws PaintMEException;

    Properties loadProperties() throws PaintMEException;
    void storeProperties(Properties props) throws PaintMEException;

    void setPropertiesFileName(String propertiesFileName);
}
