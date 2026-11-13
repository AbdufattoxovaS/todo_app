package uz.pdp.entity;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.enums.Role;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    private String username;
    private String email;


    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy="user" , cascade = CascadeType.ALL)
    private List<ToDo> toDos = new ArrayList<>();

}
