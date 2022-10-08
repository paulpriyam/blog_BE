package com.example.blog.exemptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    Long fieldType;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldType) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldType));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }
}
