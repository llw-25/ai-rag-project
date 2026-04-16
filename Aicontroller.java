import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aicontroller")
public class Aicontroller {

    @PostMapping
    public ResponseEntity<Object> handlePostRequest(@RequestBody YourRequestType request) {
        try {
            // Process the request and generate a response
            YourResponseType response = processRequest(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error processing request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Example processing method
    private YourResponseType processRequest(YourRequestType request) {
        // Implementation of request processing logic
        return new YourResponseType();
    }
}