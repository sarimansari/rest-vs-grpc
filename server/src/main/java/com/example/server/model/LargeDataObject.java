package com.example.server.model;

import java.util.List;

public class LargeDataObject {
    private long id;
    private String name;
    private String description;
    private List<String> tags;
    private NestedObject nested;

    // getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public NestedObject getNested() { return nested; }
    public void setNested(NestedObject nested) { this.nested = nested; }

    public static class NestedObject {
        private int level;
        private String info;
        public int getLevel() { return level; }
        public void setLevel(int level) { this.level = level; }
        public String getInfo() { return info; }
        public void setInfo(String info) { this.info = info; }
    }
}
