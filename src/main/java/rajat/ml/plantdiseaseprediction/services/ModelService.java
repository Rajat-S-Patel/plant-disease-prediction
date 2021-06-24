package rajat.ml.plantdiseaseprediction.services;

import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import org.springframework.stereotype.Service;
import rajat.ml.plantdiseaseprediction.classifier.Classifier;
import rajat.ml.plantdiseaseprediction.config.ModelConfig;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {

    private final Classifier classifier;
    private final ModelConfig modelConfig;

    public ModelService(Classifier classifier, ModelConfig modelConfig) {
        this.classifier = classifier;
        this.modelConfig = modelConfig;
        classifier.init();
        classifier.loadModel();
    }

    public List<String> predict(Image image){
        return processRawOutput(classifier.predict(image));
    }
    private List<String> processRawOutput(List<Classifications.Classification> output){
        List<String> outputLabels = new ArrayList<>();
        for(Classifications.Classification op:output){
            float threshold = modelConfig.getThresholds().get(op.getClassName());
            if(op.getProbability() >= threshold){
                outputLabels.add(op.getClassName());
            }
        }
        return outputLabels;
    }

}
