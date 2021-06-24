package rajat.ml.plantdiseaseprediction.classifier;

import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.repository.zoo.Criteria;
import ai.djl.translate.Translator;

public class Classifier {
    private Translator translator;
    private Criteria<Image, Classifications> criteria;

    public void init(){

    }
    public void loadModel(){

    }
    public Classifications predict(Image inputImage){
        return  null;
    }

}
