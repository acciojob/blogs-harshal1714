package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;


    public Image addImage(Integer blogId, String description, String dimensions){

        Blog blog = blogRepository2.findById(blogId).get();

        Image image = new Image(description, dimensions,blog);

        blog.getImageList().add(image);

        blogRepository2.save(blog);

        return image;

        //add an image to the blog

    }

    public void deleteImage(Integer id){

        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();
        String dimensionOfImage = image.getDimensions();

        //dimension = Length x Breadth
        String[] totalScreen = screenDimensions.split("X");
        String[] givenImage = dimensionOfImage.split("X");
        int lengthByLength = Integer.parseInt(totalScreen[0]) / Integer.parseInt(givenImage[0]);

        int breadthByBreadth = Integer.parseInt(totalScreen[1]) / Integer.parseInt(givenImage[1]);

        return lengthByLength * breadthByBreadth;
    }
}