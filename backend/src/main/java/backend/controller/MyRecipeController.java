package backend.controller;


import backend.model.MyRecipeModel;
import backend.repository.MyRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*")

public class MyRecipeController {

    @Autowired
    private MyRecipeRepository myRecipeRepository;

    //Add Recipe
    @PostMapping("/myrecipes")
    public MyRecipeModel newMyRecipeModel(@RequestBody MyRecipeModel newMyRecipeModel){
        return myRecipeRepository.save(newMyRecipeModel);
    }
    //Handle    Image Upload
    @PostMapping("myrecipes/recipeImage")
    public String recipeImage(@RequestParam("file")MultipartFile file){
        String folder = "uploads/";
        String recipeImage = file.getOriginalFilename();

        try {
            File uploadDir = new File(folder);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            //Save the file  to specified directory
            file.transferTo(Paths.get(folder + recipeImage));
        }catch (IOException e){
            e.printStackTrace();
            return "Error uploading file:" + e.getMessage();
        }
        return recipeImage; //Return the image name


        
    }

}
