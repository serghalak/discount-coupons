package com.exadel.sandbox.dto.response.filter;

import com.exadel.sandbox.dto.response.category.CategoryShortResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryFilterResponse extends CategoryShortResponse {

   private boolean isChecked;
}
