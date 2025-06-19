import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkDTOmapper linkDTOmapper;

    public LinkService(LinkRepository linkRepository, LinkDTOmapper linkDTOmapper, Validator validator) {
        this.linkRepository = linkRepository;
        this.linkDTOmapper = linkDTOmapper;
    }

    private String generateId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public ResponseDTO addLink(CreateDTO dto) {
        Link link = linkDTOmapper.map(dto);
        link.setId(generateId());
        link.setVisits(0);
        linkRepository.save(link);
        return linkDTOmapper.map(link);
    }

    public Optional<ResponseDTO> getById(String id) {
        return linkRepository.findById(id)
                .map(linkDTOmapper::map);
    }

    public Optional<String> getRedirectUrl(String id) {
        return linkRepository.findById(id).map(link -> {
            link.setVisits(link.getVisits() + 1);
            linkRepository.save(link);
            return link.getTargetUrl();
        });
    }

    public Optional<ResponseDTO> update(String id, UpdateDTO dto) {
        return linkRepository.findById(id).flatMap(existing -> {
            if (existing.getPassword() == null || !existing.getPassword().equals(dto.getPassword())) {
                throw new SecurityException("wrong password");
            }

            if (dto.getName() != null) {
                existing.setName(dto.getName());
            }
            if (dto.getTargetUrl() != null) {
                existing.setTargetUrl(dto.getTargetUrl());
            }

            Link saved = linkRepository.save(existing);
            return Optional.of(linkDTOmapper.map(saved));
        });
    }

    public boolean delete(String id, String password) {
        Optional<Link> optional = linkRepository.findById(id);
        if (optional.isPresent()) {
            Link link = optional.get();
            if (link.getPassword() == null || !link.getPassword().equals(password)) {
                throw new SecurityException("wrong password");
            }
            linkRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ResponseDTO> getByName(String name, String password) {
        return linkRepository.findByName(name)
                .map(link -> {
                    if (link.getPassword() != null && !link.getPassword().isEmpty()) {
                        if (!Objects.equals(link.getPassword(), password)) {
                            throw new SecurityException("Wrong password");
                        }
                    }
                    return linkDTOmapper.map(link);
                });
    }

    public boolean isUrlUnique(String url) {
        return !linkRepository.existsByTargetUrl(url);
    }




}
