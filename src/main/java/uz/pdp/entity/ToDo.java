package uz.pdp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String title;

    private String description;

    private boolean completed=false;

    private LocalDateTime createdAt;

    @ManyToOne
    @Column(name="user_id")
    private User user;


}
