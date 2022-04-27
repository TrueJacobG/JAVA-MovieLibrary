package com.github.truejacobg.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepo movieRepo;

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("")
    public List<Movie> getAll(){
        return movieRepo.getAll();
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id") int id){
        return movieRepo.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Movie> movies){
        return movieRepo.save(movies);
    }

    // PutMapping - every field in request should set a value
    // for example - name and rating have to be set
    
    //

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Movie updatedMovie){
        Movie movie = movieRepo.getById(id);

        if(movie == null){
            return 404;
        }

        movie.setName(updatedMovie.getName());
        movie.setRating(updatedMovie.getRating());

        movieRepo.update(movie);

        return 1;

    }

    // PatchMapping - change only requested value
    @PatchMapping("/{id}")
    public int partUpdate(@PathVariable("id") int  id, @RequestBody Movie updatedMovie){
        Movie movie = movieRepo.getById(id);

        if(movie == null){
            return 404;
        }

        if(updatedMovie.getName() != null){
            movie.setName(updatedMovie.getName());
        }

        if(updatedMovie.getRating() > 0){
            movie.setRating(updatedMovie.getRating());
        }

        movieRepo.update(movie);

        return 1;
    }

    @DeleteMapping("/{id}")
    public int delte(@PathVariable("id") int id){
        return movieRepo.delete(id);
    }
}
