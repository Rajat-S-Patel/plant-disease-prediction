package rajat.ml.plantdiseaseprediction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ModelConfig {

    @Value("${ml.model.inputWidth}")
    private Integer inputWidth;

    @Value("${ml.model.inputHeight}")
    private Integer inputHeight;

    @Value("${ml.model.labels}")
    private List<String> labels;

    @Value("#{${ml.model.id2label}}")
    private Map<Integer,String> id2Label;

    @Value("#{${ml.model.thresholds}}")
    private Map<String,Float> thresholds;

    @Value("${ml.model.name}")
    private String modelName;

    @Value("${ml.model.path}")
    private String modelPath;

    public String getModelPath() {
        return modelPath;
    }

    public String getModelName() {
        return modelName;
    }

    public Integer getInputWidth() {
        return inputWidth;
    }

    public Integer getInputHeight() {
        return inputHeight;
    }

    public List<String> getLabels() {
        return labels;
    }

    public Map<Integer, String> getId2Label() {
        return id2Label;
    }

    public Map<String, Float> getThresholds() {
        return thresholds;
    }

}
