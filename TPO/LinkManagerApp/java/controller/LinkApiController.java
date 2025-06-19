import jakarta.validation.Valid;
import org.example.s31087tpo10.model.CreateDTO;
import org.example.s31087tpo10.model.ResponseDTO;
import org.example.s31087tpo10.model.UpdateDTO;
import org.example.s31087tpo10.service.LinkService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LinkApiController {

    private final LinkService linkService;

    public LinkApiController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/api/links/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return linkService.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/api/links")
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody CreateDTO dto) {
        if (!linkService.isUrlUnique(dto.getTargetUrl())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        ResponseDTO created = linkService.addLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/links/" + created.getId())
                .body(created);
    }

    @PatchMapping("/api/links/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateDTO dto) {
        try {
            return linkService.update(id, dto)
                    .<ResponseEntity<?>>map(updated -> ResponseEntity.noContent().build())
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .header("reason", "wrong password")
                    .build();
        }
    }

    @DeleteMapping("/api/links/{id}")
    public ResponseEntity<?> delete(@PathVariable String id, @RequestParam(required = false) String pass) {
        try {
            boolean deleted = linkService.delete(id, pass);
            return deleted
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .header("reason", "wrong password")
                    .build();
        }
    }

    @GetMapping("/red/{id}")
    public ResponseEntity<?> redirect(@PathVariable String id) {
        return linkService.getRedirectUrl(id)
                .map(url -> ResponseEntity.status(302).header("Location", url).build())
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handle(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (e1, e2) -> e1));
    }


}
