//
//  MovieListVC.swift
//  fo-training
//
//  Created by Yanki Insel on 13.03.2018.
//  Copyright © 2018 Fo. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireObjectMapper
import AlamofireImage

class MovieListVC: UIViewController {
    
    var moviesArray: [Movie] = []
    var apiKey = "b0edb4e7"
    
    
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        prepareNavigationItem()
        prepareTableView()

    }
    
    fileprivate func prepareNavigationItem() {
        navigationItem.title = "Movies List"
        navigationController?.navigationBar.tintColor = .white
        navigationController?.navigationBar.barStyle = .blackTranslucent
        navigationController?.navigationBar.barTintColor = UIColor.black
        let textAttributes = [NSAttributedStringKey.foregroundColor:UIColor.white, NSAttributedStringKey.font:Font.title!] as [NSAttributedStringKey : Any]
        navigationController?.navigationBar.titleTextAttributes = textAttributes
    }
    
    func prepareTableView() {
        self.tableView.register(UINib(nibName: "MovieListCell", bundle: nil), forCellReuseIdentifier: "MovieListCell")
        tableView.tableFooterView = UIView(frame: .zero)
        tableView.delegate = self
        tableView.dataSource = self
        searchBar.delegate = self
        searchBar.returnKeyType = .done
    }
}

extension MovieListVC: UITableViewDelegate, UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return moviesArray.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell:MovieListCell = tableView.dequeueReusableCell(withIdentifier: "MovieListCell") as? MovieListCell else {
            return UITableViewCell()
        }
        
        
        if moviesArray.count == 0 {
            return UITableViewCell()
        }
        
        let movie = moviesArray[indexPath.row]
        
        if let image = movie.poster {
            cell.posterImageView.image = image
        }
        cell.titleLabel.text = movie.title
        
        return cell
    }
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        guard let movieDetailVC = storyboard.instantiateViewController(withIdentifier: "MovieDetailVC") as? MovieDetailVC else {
            return
        }
        movieDetailVC.movie = moviesArray[indexPath.row]
        navigationController?.pushViewController(movieDetailVC, animated: true)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 128.0
    }
    
}

extension MovieListVC: UISearchBarDelegate {
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder()
    }
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        
        if let text = searchBar.text?.replacingOccurrences(of: " ", with: "+") {
            search(text)
        }
    }
    
    func search(_ text: String) {
        Alamofire.request("https://www.omdbapi.com/?apikey=\(apiKey)&s=\(text)").responseObject { (response: DataResponse<SearchResponse>) in
            
            print("Request: \(String(describing: response.request))")   // original url request
            print("Response: \(String(describing: response.response))") // http url response
            print("Result: \(response.result)")                         // response serialization result
            
            if let searchResponse = response.result.value {
                guard let movies = searchResponse.movies else {
                    self.moviesArray = []
                    self.tableView.reloadData()
                    return
                }
                if movies.count > 0 {
                    self.moviesArray = searchResponse.movies!
                    self.tableView.reloadData()
                    for movie in movies {
                        if movie.poster == nil {
                            movie.poster = UIImage()
                            self.tableView.reloadData()
                            if let imageURL = movie.posterURL {
                                Alamofire.request(imageURL).responseImage { response in
                                    if let image = response.result.value {
                                        movie.poster = image
                                        self.tableView.reloadData()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
