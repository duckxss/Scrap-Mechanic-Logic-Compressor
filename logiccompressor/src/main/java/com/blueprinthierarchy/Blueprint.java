package com.blueprinthierarchy;

import java.util.List;

public class Blueprint {
    public List<Body> bodies;
    public List<Dependency> dependencies;
    public int version;
    public Object getBodies;

    public List<Body> getBodies() {
        return bodies;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public int getVersion() {
        return version;
    }

}
