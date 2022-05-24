package com.letscode.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicDto {
    @Id
    private Long id;

    @NotBlank
    private String title;

    private String musicalGender;

    @JsonCreator
    public MusicDto(@JsonProperty final Long id,
                   @JsonProperty final String title,
                   @JsonProperty final String musicalGender) {
        this.id = id;
        this.title = title;
        this.musicalGender = musicalGender;
    }
}
