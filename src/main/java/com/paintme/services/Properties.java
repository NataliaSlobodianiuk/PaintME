package com.paintme.services;

public class Properties {
    public enum Files {
        APPLICATION("application.properties"),
        SESSION("session.properties");

        private String name;

        Files(String fileName) {
            this.name = fileName;
        }

        public String fileName() {
            return name;
        }
    }

    public enum UserProperties {
        LOGIN("session.user.name"),
        PASSWORD("session.user.password");

        private String name;

        UserProperties(String propertyName) {
            this.name = propertyName;
        }

        public String propertyName() {
            return name;
        }
    }
}
