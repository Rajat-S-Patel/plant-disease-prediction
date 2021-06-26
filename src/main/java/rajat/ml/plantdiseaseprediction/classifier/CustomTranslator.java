package rajat.ml.plantdiseaseprediction.classifier;

import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.translate.Batchifier;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import org.springframework.stereotype.Component;
import rajat.ml.plantdiseaseprediction.config.ModelConfig;

@Component
public class CustomTranslator implements Translator<Image, Classifications> {
    private final ModelConfig modelConfig;

    public CustomTranslator(ModelConfig modelConfig) {
        this.modelConfig = modelConfig;
    }

    @Override
    public Batchifier getBatchifier() {
        return null;
    }

    @Override
    public Classifications processOutput(TranslatorContext translatorContext, NDList ndList) throws Exception {
        NDArray prob = ndList.singletonOrThrow();
        return new Classifications(modelConfig.getLabels(),prob);
    }

    @Override
    public NDList processInput(TranslatorContext translatorContext, Image image) throws Exception {
        NDManager childManager = translatorContext.getNDManager().newSubManager();
        NDArray arr = NDImageUtils.resize(image.toNDArray(childManager),600,600);
        arr = arr.expandDims(0);
        System.out.println("shape:= "+arr.getShape());
        return new NDList(arr);
    }
}
