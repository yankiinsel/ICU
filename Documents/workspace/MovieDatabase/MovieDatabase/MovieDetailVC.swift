//
//  MovieDetailVC
//  fo-training
//
//  Created by Yanki Insel on 13.03.2018.
//  Copyright © 2018 Fo. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireObjectMapper

class MovieDetailVC: UIViewController {
    
    @IBOutlet weak var posterImageView: UIImageView!
    @IBOutlet weak var detailView: MovieDetailView!
    
    var movie: Movie!
    var apiKey = "b0edb4e7"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        prepareMovie()
        prepareViews()
    }
    
    fileprivate func prepareViews() {
        prepareImageView()
        prepareDetailView()
        view.backgroundColor = .black
        view.bringSubview(toFront: detailView)
    }
    
    fileprivate func prepareImageView() {
        posterImageView.contentMode = .scaleAspectFill
        if let image = movie.poster {
            posterImageView.image = image
        }
        posterImageView.clipsToBounds = true
    }
    
    fileprivate func prepareDetailView() {
        detailView.titleLabel.text = movie.title
        detailView.descriptionLabel.text = movie.plot
        detailView.directorLabel.text = movie.director
        detailView.titleLabel.font = Font.title
        detailView.descriptionLabel.font = Font.body
        detailView.directorLabel.font = Font.body

    }
    
    fileprivate func prepareMovie() {
        guard let id = movie.id else {
            return
        }
        Alamofire.request("https://www.omdbapi.com/?apikey=\(apiKey)&i=\(id)").responseObject { (response: DataResponse<Movie>) in
            
            print("Request: \(String(describing: response.request))")   // original url request
            print("Response: \(String(describing: response.response))") // http url response
            print("Result: \(response.result)")                         // response serialization result
            
            if let movie = response.result.value {
                self.movie = movie
            }
            self.prepareDetailView()
        }
    }
}

