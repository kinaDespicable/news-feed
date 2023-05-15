package dev.dani.strong.newsfeed.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CreatedResponse extends BaseResponse {

    @JsonProperty("created")
    private Object data;

}
