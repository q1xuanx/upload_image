package learn;


public class pic {

    private String urlImage;
    private String dateCreated;


    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void display() {
        System.out.printf(String.format(urlImage + " " + dateCreated + "\n"));
    }
}
