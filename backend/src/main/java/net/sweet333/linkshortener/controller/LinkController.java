package net.sweet333.linkshortener.controller;

import net.sweet333.linkshortener.dto.CreateLinkDto;
import net.sweet333.linkshortener.dto.ShowLinkDto;
import net.sweet333.linkshortener.service.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateLinkDto linkDto) {
        try {
            return ResponseEntity.ok(linkService.createLink(linkDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка: " + e.getMessage());
        }
    }
}
