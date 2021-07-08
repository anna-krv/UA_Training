package app.model.entity;

public class Note {
    public enum Group {
        COWORKERS,
        FAMILY,
        FRIENDS,
        OTHER
    }

    ;
    protected String surname;
    protected String name;
    protected String middleName;
    protected String compoundName;
    protected String login;
    protected String comment;
    protected String homeNumber;
    protected String mobileNumber;
    protected String secondMobileNumber;
    protected String email;
    protected String skype;
    protected String addressFull;
    protected Group group;
    Address address;

    public Note(String surname, String name, String middleName, String login, String comment,
                String groupName, String homeNumber, String mobileNumber, String secondMobileNumber,
                String email, String skype, Address address) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.compoundName = getCompoundName(surname, name);
        this.login = login;
        this.comment = comment;
        this.group = Group.valueOf(groupName.toUpperCase());
        this.homeNumber = homeNumber;
        this.mobileNumber = mobileNumber;
        this.secondMobileNumber = secondMobileNumber;
        this.email = email;
        this.skype = skype;
        this.address = address;
        this.addressFull = address.toString();
    }

    public static String getCompoundName(String surname, String name) {
        StringBuilder compoundName = new StringBuilder();
        compoundName.append(surname);
        compoundName.append(" ");
        compoundName.append(name.charAt(0));
        compoundName.append(".");
        return compoundName.toString();
    }

    public String getLogin(){
        return login;
    }
}
