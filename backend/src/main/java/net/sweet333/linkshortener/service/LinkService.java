package net.sweet333.linkshortener.service;

import net.sweet333.linkshortener.dto.CreateLinkDto;
import net.sweet333.linkshortener.dto.ShowLinkDto;
import net.sweet333.linkshortener.model.Link;

public interface LinkService {

    ShowLinkDto createLink(CreateLinkDto createLinkDto);

    String getUrl(String urlLink);

}
