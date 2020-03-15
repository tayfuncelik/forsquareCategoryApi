package com.example.fs.entity;

import java.util.Arrays;

/**
 * Class representing Category entity
 *
 * @author Antti Leppä / Blake Dy
 */
public class Category implements FoursquareEntity {

    private static final long serialVersionUID = -4573082152802069375L;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPluralName() {
        return pluralName;
    }

    public Icon getIcon() {
        return icon;
    }

    public String[] getParents() {
        return parents;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public Category[] getCategories() {
        return categories;
    }

    private String id;
    private String name;
    private String pluralName;
    private Icon icon;
    private String[] parents;
    private Boolean primary;
    private Category[] categories;

    // TODO
    private String shortName;

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pluralName='" + pluralName + '\'' +
                ", icon='" + icon + '\'' +
                ", parents=" + Arrays.toString(parents) +
                ", primary=" + primary +
                ", categories=" + Arrays.toString(categories) +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}
