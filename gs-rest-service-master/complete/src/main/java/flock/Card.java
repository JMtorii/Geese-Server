package flock;

public class Card {

    private final long templateId;
    private final User user;
    private final ImageLogo imageLogo;
    private final Company company;

    public Card(long id, User user, ImageLogo logo, Company company) {
        this.templateId = id;
        this.user = user;
        this.imageLogo = logo;
        this.company = company;
    }

    public long getTemplateId() {
        return templateId;
    }
}
