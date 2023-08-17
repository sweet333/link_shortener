package net.sweet333.linkshortener.service.impl;

import net.sweet333.linkshortener.dto.CreateLinkDto;
import net.sweet333.linkshortener.dto.ShowLinkDto;
import net.sweet333.linkshortener.model.Link;
import net.sweet333.linkshortener.repository.LinkRepository;
import net.sweet333.linkshortener.service.LinkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;
    private final String address;

    public LinkServiceImpl(LinkRepository linkRepository,
                           @Value("${link.address}") String address) {
        this.linkRepository = linkRepository;
        this.address = address;
    }

    @Override
    public ShowLinkDto createLink(CreateLinkDto createLinkDto) {
        String url = createLinkDto.getUrl();
        Link link = new Link();
        link.setUrl(url);
        link.setLinkUrl(hashString(url, 12));

        linkRepository.save(link);

        return new ShowLinkDto(getUrlByLink(link));
    }

    @Override
    public String getUrl(String urlLink) {
        return linkRepository.findByLinkUrl(urlLink).orElseThrow().getUrl();
    }

    private String getUrlByLink(Link link) {
        return address + link.getLinkUrl();
    }

    public boolean isUrl(String input) {
        String urlRegex = "^(https?|ftp)://([a-zA-Z0-9.-]+(/[a-zA-Z0-9-._?,'+%&=:@\\[\\]]*)?)?$";
        return input.matches(urlRegex);
    }

    public String hashString(String input, int n) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.substring(0, n);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
