package app.controller;

public class Note {
    public enum Group {
        COWORKERS,
        FAMILY,
        FRIENDS,
        OTHER
    }

    ;
    private String surname;
    private String name;
    private String middleName;
    private String compoundName;
    private String nickname;
    private String comment;
    private String homeNumber;
    private String mobileNumber;
    private String secondMobileNumber;
    private String email;
    private String skype;
    private String addressFull;
    private Group group;
    Address address;


    public Note(String surname, String name, String middleName, String nickname, String comment,
                String groupName, String homeNumber, String mobileNumber, String secondMobileNumber,
                String email, String skype, Address address) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.compoundName = getCompoundName(surname, name);
        this.nickname = nickname;
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
}
