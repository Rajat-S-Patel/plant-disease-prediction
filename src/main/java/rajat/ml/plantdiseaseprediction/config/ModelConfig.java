package rajat.ml.plantdiseaseprediction.config;

import ai.djl.util.Pair;
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

    public Integer getInputWidth() {
        return inputWidth;
    }

    public void setInputWidth(Integer inputWidth) {
        this.inputWidth = inputWidth;
    }

    public Integer getInputHeight() {
        return inputHeight;
    }

    public void setInputHeight(Integer inputHeight) {
        this.inputHeight = inputHeight;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Map<Integer, String> getId2Label() {
        return id2Label;
    }

    public void setId2Label(Map<Integer, String> id2Label) {
        this.id2Label = id2Label;
    }

    public Map<String, Float> getThresholds() {
        return thresholds;
    }

    public void setThresholds(Map<String, Float> thresholds) {
        this.thresholds = thresholds;
    }
}
