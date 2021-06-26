package rajat.ml.plantdiseaseprediction.classifier;

import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.tensorflow.engine.TfModel;
import ai.djl.translate.TranslateException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import rajat.ml.plantdiseaseprediction.config.ModelConfig;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

@Component
public class Classifier {
    private Criteria<Image, Classifications> criteria;
    private ZooModel<Image,Classifications> model;
    private final CustomTranslator customTranslator;

    public Classifier(CustomTranslator customTranslator, ModelConfig modelConfig) {
        this.customTranslator = customTranslator;
        this.modelConfig = modelConfig;
    }

    private final ModelConfig modelConfig;

    public void init(){
        try {
            criteria = Criteria.builder().setTypes(Image.class,Classifications.class)
                    .optModelPath(Path.of(modelConfig.getModelPath()))
                    .optTranslator(customTranslator)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void loadModel(){
        Assert.notNull(customTranslator,"translator can't be null");
        Assert.notNull(criteria,"criteria can't be null");

        try {
            model = ModelZoo.loadModel(criteria);
        } catch (IOException | MalformedModelException | ModelNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Classifications.Classification> predict(Image inputImage){
        Assert.notNull(model,"Model can't be null");
        Assert.notNull(inputImage,"Input image can't be null");
        List<Classifications.Classification> predictions=null;
        Predictor<Image,Classifications> predictor = model.newPredictor(customTranslator);
        try {
            predictions = predictor.predict(inputImage).items();
        } catch (TranslateException e) {
            e.printStackTrace();
        }
        predictor.close();
        return predictions;
    }
    @PreDestroy
    public void destroy(){
        model.close();
    }
}
