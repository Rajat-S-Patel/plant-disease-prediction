package rajat.ml.plantdiseaseprediction.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import rajat.ml.plantdiseaseprediction.services.ImageProcessingService;

@Controller
public class IndexController {

    private final ImageProcessingService imageProcessingService;
    public IndexController(ImageProcessingService imageProcessingService) {
        this.imageProcessingService = imageProcessingService;
    }

    @GetMapping("/")
    public String getLabel(){
        return "index";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(value = "file")MultipartFile file,Model model){
        model.addAttribute("labels",imageProcessingService.getLabels(file));
        return "result";
    }
    @GetMapping("/uploadFile")
    public String seeResult(Model model){
        return "result";
    }

}
