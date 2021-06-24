package rajat.ml.plantdiseaseprediction.services;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageProcessingService {
    private final ModelService modelService;

    public ImageProcessingService(ModelService modelService) {
        this.modelService = modelService;
    }

    public List<String> getLabels(MultipartFile file){
        Image image = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            image = ImageFactory.getInstance().fromInputStream(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelService.predict(image);
    }
}
