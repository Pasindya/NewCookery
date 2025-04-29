package backend.repository;

import backend.model.MyRecipeModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyRecipeRepository extends MongoRepository<MyRecipeModel,Long> {
}
