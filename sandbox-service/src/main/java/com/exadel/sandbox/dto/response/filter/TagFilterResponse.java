package com.exadel.sandbox.dto.response.filter;


import com.exadel.sandbox.dto.response.tag.TagResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagFilterResponse extends TagResponse {

    private boolean isChecked;
}
