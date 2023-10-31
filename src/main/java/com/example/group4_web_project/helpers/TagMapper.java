package com.example.group4_web_project.helpers;

import com.example.group4_web_project.models.Tag;
import com.example.group4_web_project.models.TagDto;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public Tag fromDto(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName().toLowerCase()); // Ensure tag name is lowercase

        return tag;
    }
}
