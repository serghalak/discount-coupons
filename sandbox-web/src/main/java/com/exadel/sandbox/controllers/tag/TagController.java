package com.exadel.sandbox.controllers.tag;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.tag.TagResponse;
import com.exadel.sandbox.service.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService service;

    @GetMapping
    public PageList<TagResponse> getAll(@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                        @RequestParam(name = "pageSize", required = false) Integer pageSize){
        return service.findAll(pageNumber, pageSize);
    }

    @DeleteMapping(path = {"/{tagId}"})
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("tagId") Long tagId) {

        log.debug(">>>>>>>>>>controller delete tag by Id");

        service.deleteTagById(tagId);
    }

}
