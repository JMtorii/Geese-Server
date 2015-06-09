package flock;

public class ImageLogo {

    private final String src;
    private final String name;

    public ImageLogo(String src, String name) {
        this.name = name;
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public String getSrc() {
        return src;
    }
}
