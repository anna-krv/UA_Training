package ua.finalproject.periodicals.entity;

import com.google.common.collect.ImmutableList;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    @Column(columnDefinition = "varchar(10) default 'UA'")
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
    @Transient
    private List<Role> authorities = ImmutableList.of(Role.USER);

    @Override
    public String getUsername() {
        return username;
    }

}
