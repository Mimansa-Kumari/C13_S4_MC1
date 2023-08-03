package com.example.MuzixApplication.controller;

import com.example.MuzixApplication.domain.Track;
import com.example.MuzixApplication.exception.ArtistNameNotFound;
import com.example.MuzixApplication.exception.InvalidTrackId;
import com.example.MuzixApplication.exception.NoTrackExistWithTheSpecifiedCondition;
import com.example.MuzixApplication.exception.TrackDetailsWithSpecifiedIdAlreadyExist;
import com.example.MuzixApplication.service.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrackController {
    @Autowired
    ITrackService iTrackService;

    public TrackController(ITrackService iTrackService) {
        this.iTrackService = iTrackService;
    }

    //http://localhost:63300/api/v1/addTrack
    @PostMapping("/addTrack")
    public ResponseEntity<?>saveTrackDetails(@RequestBody Track track) throws TrackDetailsWithSpecifiedIdAlreadyExist
    {
        Track track1 = iTrackService.saveTrackDetails(track);
        return new ResponseEntity<>(track1,HttpStatus.CREATED);
    }

    //http://localhost:63300/api/v1/delBy/3
    @DeleteMapping("/delBy/{trackId}")
    public ResponseEntity<?>deleteTrackDetails(@PathVariable int trackId) throws InvalidTrackId
    {
        boolean check = iTrackService.deleteTrackDetails(trackId);
        return new ResponseEntity<>(check,HttpStatus.OK);
    }

    //http://localhost:63300/api/v1/getTracks
    @GetMapping("/getTracks")
    public ResponseEntity<?>fetchAllTrackDetails()
    {
        List<Track> list = iTrackService.fetchAllTrackDetails();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //http://localhost:63300/api/v1/getByRating
    @GetMapping("/getByRating")
    public ResponseEntity<?> getTrackByTrackRating() throws NoTrackExistWithTheSpecifiedCondition
    {
        List<Track>list = iTrackService.getTrackByTrackRating();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    //http://localhost:63300/api/v1/getByArtist/Justin%20Bieber
    @GetMapping("/getByArtist/{artistName}")
    public ResponseEntity<?>getTrackByArtistName(@PathVariable String artistName) throws ArtistNameNotFound
    {
        List<Track> list = iTrackService.getTrackByArtistName(artistName);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
