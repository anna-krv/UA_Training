package app.model.entity;


public class NotUniqueLoginException extends Exception {
    private String login;

    public NotUniqueLoginException(String login) {
        super(String.format("The login: \"%s\" is already used. Please, select another.", login));
        this.login = login;
    }
}
