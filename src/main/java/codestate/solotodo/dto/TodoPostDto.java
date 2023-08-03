package codestate.solotodo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoPostDto {
    private String title;

    private int todoOrder;

    private boolean completed;
}
