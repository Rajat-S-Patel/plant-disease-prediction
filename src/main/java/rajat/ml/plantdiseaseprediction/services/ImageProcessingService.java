package rajat.ml.plantdiseaseprediction.services;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageProcessingService {
    private final ModelService modelService;

    public ImageProcessingService(ModelService modelService) {
        this.modelService = modelService;
    }

    public List<String> getLabels(){
        Image image = null;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("sample_images/ad8770db05586b59.jpg").getFile());
        try {
            image = ImageFactory.getInstance().fromFile(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelService.predict(image);
    }
}
