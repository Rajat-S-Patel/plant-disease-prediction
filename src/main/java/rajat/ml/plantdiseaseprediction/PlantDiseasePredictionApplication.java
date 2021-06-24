package rajat.ml.plantdiseaseprediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rajat.ml.plantdiseaseprediction.services.ImageProcessingService;

@SpringBootApplication
public class PlantDiseasePredictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlantDiseasePredictionApplication.class, args);
    }
}
