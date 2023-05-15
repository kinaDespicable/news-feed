package dev.dani.strong.newsfeed.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class DeletedResponse extends BaseResponse {

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("deleted_object")
    private Object data;

}