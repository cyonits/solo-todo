package codestate.solotodo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResponseDto {
    private long id;

    private String title;

    private int todoOrder;

    private boolean completed;

    private String url;
}
