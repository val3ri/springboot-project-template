package springboot.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 2, message = "Title must be at least 2 characters")
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    @Size(min = 2, message = "Title must be at least 2 characters")
    private String author;

}
