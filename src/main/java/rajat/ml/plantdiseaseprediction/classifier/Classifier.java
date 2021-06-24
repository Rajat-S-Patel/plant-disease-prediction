package rajat.ml.plantdiseaseprediction.classifier;

import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.transform.Resize;
import ai.djl.modality.cv.translator.ImageClassificationTranslator;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import rajat.ml.plantdiseaseprediction.config.ModelConfig;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

@Component
public class Classifier {
    private Translator<Image,Classifications> translator;
    private Criteria<Image, Classifications> criteria;
    private ZooModel<Image,Classifications> model;

    public Classifier(ModelConfig modelConfig) {
        this.modelConfig = modelConfig;
    }

    private final ModelConfig modelConfig;

    public void init(){
        translator= ImageClassificationTranslator.builder()
                .addTransform(new Resize(modelConfig.getInputWidth(), modelConfig.getInputHeight()))
                .optSynset(modelConfig.getLabels())
                .build();

        criteria = Criteria.builder().setTypes(Image.class,Classifications.class)
                .optModelName(modelConfig.getModelName())
                .optTranslator(translator)
                .build();
    }
    public void loadModel(){
        Assert.notNull(translator,"translator can't be null");
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
        Predictor<Image,Classifications> predictor = model.newPredictor();
        try {
            predictions = predictor.predict(inputImage).items();
        } catch (TranslateException e) {
            e.printStackTrace();
        }
        return predictions;
    }
    @PreDestroy
    public void destroy(){
        model.close();
    }
}
