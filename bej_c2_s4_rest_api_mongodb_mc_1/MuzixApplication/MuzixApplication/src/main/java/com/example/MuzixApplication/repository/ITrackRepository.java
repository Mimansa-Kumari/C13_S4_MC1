package com.example.MuzixApplication.repository;

import com.example.MuzixApplication.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITrackRepository extends MongoRepository<Track,Integer> {

    @Query("{'trackRating' : { $gt : 4 }}")
    public List<Track> getTrackByTrackRating();
    @Query("{'trackArtist.artistName':{$in:[?0]}}")
    public List<Track> getTrackByArtistName(String artistName);
}
