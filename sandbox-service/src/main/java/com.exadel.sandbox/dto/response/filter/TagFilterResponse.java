package com.exadel.sandbox.dto.response.filter;

import com.exadel.sandbox.dto.request.tag.TagResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagFilterResponse extends TagResponse {

    private boolean isChecked;
}
