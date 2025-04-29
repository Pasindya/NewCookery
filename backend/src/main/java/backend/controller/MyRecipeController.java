package backend.controller;


import backend.exception.MyRecipeNotFoundException;
import backend.model.MyRecipeModel;
import backend.repository.MyRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

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
    public String recipeImage(@RequestParam("file")MultipartFile file) {
        String folder = "uploads/";
        String recipeImage = file.getOriginalFilename();

        try {
            File uploadDir = new File(folder);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            //Save the file  to specified directory
            file.transferTo(Paths.get(folder + recipeImage));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file:" + e.getMessage();
        }
        return recipeImage; //Return the image name
       }

        //Data display
        @GetMapping("/myrecipies")
        List<MyRecipeModel>getAllRecipies(){return myRecipeRepository.findAll();}

        @GetMapping("/myrecipies/{id}")
        MyRecipeModel getRecipeId(@PathVariable Long id){
            return myRecipeRepository.findById(id).orElseThrow(() -> new MyRecipeNotFoundException(id));
        }



        //display image
            private final String UPLOAD_DIR = "uploads/";
        @GetMapping("uploads/{filename}")
        public ResponseEntity<FileSystemResource> getImage(@PathVariable String filename){
            File file = new File(UPLOAD_DIR + filename);
            if(!file.exists()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new FileSystemResource(file));
        }
    }

