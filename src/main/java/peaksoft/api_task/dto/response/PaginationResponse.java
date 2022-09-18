package peaksoft.api_task.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PaginationResponse {
    private List<AuthorResponse> authorResponseList;
    private int currentPage;
    private int totalPages;


}
