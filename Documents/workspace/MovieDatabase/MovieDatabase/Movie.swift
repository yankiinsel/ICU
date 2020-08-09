//
//  Movie.swift
//  fo-training
//
//  Created by Yanki Insel on 13.03.2018.
//  Copyright © 2018 Fo. All rights reserved.
//

import UIKit
import ObjectMapper
import AlamofireObjectMapper

class SearchResponse: Mappable {
    var movies: [Movie]?
    var totalResults: String?
    var response: String!
    
    required init?(map: Map){
        
    }
    
    func mapping(map: Map) {
        totalResults <- map["totalResults"]
        movies <- map["Search"]
        response <- map["Response"]
    }
}

class Movie: Mappable {
    
    var poster: UIImage?
    var title: String?
    var plot: String?
    var director: String?
    var id: String?
    var year: String?
    var posterURL: String?
    
    init(poster: UIImage? = nil, title: String, plot: String, director: String, year: String) {
        self.poster = poster
        self.title = title
        self.plot = plot
        self.director = director
        self.year = year
    }
    
    required init?(map: Map){
        
    }
    
    func mapping(map: Map) {
        title <- map["Title"]
        director <- map["Director"]
        plot <- map["Plot"]
        id <- map["imdbID"]
        year <- map["Year"]
        posterURL <- map["Poster"]
    }
    
}
