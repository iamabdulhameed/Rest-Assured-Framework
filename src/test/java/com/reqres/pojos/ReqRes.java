package com.reqres.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode // It overrides the Object's default equal and hashCode method so we can compare the values from POJO and Deserialized Response.
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // While comparing response with Pojo it will ignore those properties coming extra in response that is not present in POJO/while request.

public class ReqRes {
    private String name;
    private String job;
}
