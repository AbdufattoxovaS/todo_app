package uz.pdp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoDTO {

    private String title;
    private String description;
    private boolean completed;
}