package rajat.ml.plantdiseaseprediction.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import rajat.ml.plantdiseaseprediction.services.ImageProcessingService;

@Controller
public class TestController {

    private final ImageProcessingService imageProcessingService;

    public TestController(ImageProcessingService imageProcessingService) {
        this.imageProcessingService = imageProcessingService;
    }

    @GetMapping("/")
    public String getLabel(){
        return "index";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(value = "input-file")MultipartFile file, Model model){
        model.addAttribute("labels",imageProcessingService.getLabels(file));
        return "result";
    }
}
