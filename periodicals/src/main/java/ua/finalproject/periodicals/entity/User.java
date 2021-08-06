package ua.finalproject.periodicals.entity;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, columnDefinition = "varchar(200)")
    private String password;
    @Column(columnDefinition = "varchar(3) default 'UA'")
    private String language = "UA";

    @Column(columnDefinition = "boolean default true")
    private boolean accountNonLocked = true;
    @Column(columnDefinition = "boolean default true")
    private boolean accountNonExpired = true;
    @Column(columnDefinition = "boolean default true")
    private boolean credentialsNonExpired = true;
    @Column(columnDefinition = "boolean default true")
    private boolean enabled = true;
    //@Column(columnDefinition="varchar(30) default 'USER'")

    private Role authority;
    @Transient
    private List<Role> authorities;//= Arrays.asList(authority);
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Account account;
    @OneToMany(mappedBy = "user")
    Set<Subscription> subscriptions;

    public List<Role> getAuthorities() {
        return Arrays.asList(authority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String toString() {
        return "";
    }
}
