package net.sweet333.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import net.sweet333.linkshortener.dto.ShowLinkDto;
import net.sweet333.linkshortener.model.Link;
import net.sweet333.linkshortener.repository.LinkRepository;
import net.sweet333.linkshortener.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LinkRedirectController {

    private final LinkService linkService;

    @GetMapping("/{link}")
    public ModelAndView redirect(@PathVariable String link) {
        return new ModelAndView("redirect:" + linkService.getUrl(link));
    }
}

