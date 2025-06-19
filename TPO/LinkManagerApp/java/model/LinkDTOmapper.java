package org.example.s31087tpo10.model;

import org.example.s31087tpo10.model.*;

import org.springframework.stereotype.Component;

@Component
public class LinkDTOmapper {

    public Link map(CreateDTO dto) {
        Link link = new Link();
        link.setName(dto.getName());
        link.setTargetUrl(dto.getTargetUrl());
        link.setPassword(dto.getPassword());
        return link;
    }

    public ResponseDTO map(Link link) {
        return new ResponseDTO(
                link.getId(),
                link.getName(),
                link.getTargetUrl(),
                "/red/" + link.getId(),
                link.getVisits()
        );
    }
}
