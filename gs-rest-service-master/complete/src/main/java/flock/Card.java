package flock;

public class Card {

    private final long id;
    private final long templateId;
    private final User user;
    private final ImageLogo imageLogo;
    private final Company company;

    public Card(long id, long templateId, User user, ImageLogo logo, Company company) {
        this.id = id;
        this.templateId = templateId;
        this.user = user;
        this.imageLogo = logo;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public long getTemplateId() {
        return templateId;
    }

    public User getUser() {
        return user;
    }

    public ImageLogo getImageLogo() {
        return imageLogo;
    }

    public Company getCompany() {
        return company;
    }
}
