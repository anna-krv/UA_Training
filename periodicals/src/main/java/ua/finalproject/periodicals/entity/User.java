package ua.finalproject.periodicals.entity;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 30)
    @Size(min = 2, max = 30)
    private String name;

    @Column(nullable = false, length = 30)
    @Size(min = 2, max = 30)
    private String surname;

    @Column(nullable = false, unique = true)
    @Email(regexp = "[^_\\W][\\w!#$%&'*+-/=?^_`{|}]{0,63}@[A-Za-z0-9-]{1,253}(\\.[A-Za-z0-9-]{1,253})+")
    private String email;

    @Column(nullable = false, length = 30, unique = true)
    @Size(min = 4, max = 30)
    private String username;

    @Column(nullable = false)
    @Size(min = 4, max = 255)
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

    private Role authority;

    @Transient
    private List<Role> authorities;
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
